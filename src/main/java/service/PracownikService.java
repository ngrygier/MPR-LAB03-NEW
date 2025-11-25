package service;

import model.CompanyStatistics;
import model.Pracownik;
import model.Stanowisko;

import java.util.*;

public class PracownikService {

    private ArrayList<Pracownik> listaPracownikow = new ArrayList<>();
    private ArrayList<Pracownik> szukaniePoFirmie = new ArrayList<>();
    private ArrayList<Pracownik> wyswietlaniePracownikow = new ArrayList<>();

    // gettery do testow
    public ArrayList<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

    public ArrayList<Pracownik> getSzukaniePoFirmie() {
        return szukaniePoFirmie;
    }

    public ArrayList<Pracownik> getWyswietlaniePracownikow() {
        return wyswietlaniePracownikow;
    }


    //dodawanie pracownika do listy
    public void dodajPracownika(Pracownik pracownik) {
        for (Pracownik p : listaPracownikow) {
            if (p.getEmail().equals(pracownik.getEmail())) {
                throw new IllegalArgumentException("Pracownik z podanym adresem email juz istnieje");
            }
        }
        listaPracownikow.add(pracownik);
    }

    //wyswietlanie pracownikow
    public void wyswietlPracownikow() {
        wyswietlaniePracownikow.clear();
        for (Pracownik p : listaPracownikow) {
            System.out.println(p + "\n");
            wyswietlaniePracownikow.add(p);
        }
    }

    //wyszukiwanie po firmie
    public void szukajPoFirmie(String nazwaFirmy) {
        szukaniePoFirmie.clear();
        for (Pracownik p : listaPracownikow) {
            if (p.getNazwaFirmy().equals(nazwaFirmy)) {
                szukaniePoFirmie.add(p);
                System.out.println(p.getImie() + " " + p.getNazwisko() + "\n");
            }
        }
    }

    //sortowanie alfabetyczne
    public void sortujAlfabetycznie() {
        listaPracownikow.sort(Comparator.comparing(Pracownik::getNazwisko, String.CASE_INSENSITIVE_ORDER));
    }

    //grupowanie po stanowisku
    public HashMap<Stanowisko, ArrayList<Pracownik>> grupujPoStanowisku() {
        HashMap<Stanowisko, ArrayList<Pracownik>> grupowanie = new HashMap<>();

        for (Stanowisko s : Stanowisko.values()) {
            grupowanie.put(s, new ArrayList<>());
        }

        for (Pracownik p : listaPracownikow) {
            grupowanie.get(p.getStanowisko()).add(p);
        }
        return grupowanie;
    }

    //zlicanie pracownikow
    public HashMap<Stanowisko, Integer> zliczPracownikow() {
        HashMap<Stanowisko, Integer> mapa = new HashMap<>();

        for (Stanowisko s : Stanowisko.values()) {
            mapa.put(s, 0);
        }

        for (Pracownik p : listaPracownikow) {
            mapa.put(p.getStanowisko(), mapa.get(p.getStanowisko()) + 1);
        }

        return mapa;
    }

    //liczenie sredniego wynagrodzenia
    public double statystykaWynagrodzenia() {
        if (listaPracownikow.isEmpty()) return 0;

        double suma = 0;
        for (Pracownik p : listaPracownikow) {
            suma += p.getWynagrodzenie();
        }
        return suma / listaPracownikow.size();
    }

    //szukanie pracownika z najwieksza pensja
    public ArrayList<Pracownik> najlepiejPlatny() {
        if (listaPracownikow.isEmpty()) return null;

        ArrayList<Pracownik> najlepiejPlatni = new ArrayList<>();
        double maxWynagrodzenie = listaPracownikow.get(0).getWynagrodzenie();

        for (Pracownik p : listaPracownikow) {
            if (p.getWynagrodzenie() > maxWynagrodzenie) {
                maxWynagrodzenie = p.getWynagrodzenie();
            }
        }

        for (Pracownik p : listaPracownikow) {
            if (p.getWynagrodzenie() == maxWynagrodzenie) {
                najlepiejPlatni.add(p);
            }
        }

        return najlepiejPlatni;
    }


        //pracownicy z wynagrodzeniem nizszym niz minimalne dla stanowiska
    public ArrayList<Pracownik> validateSalaryConsistency(){
        ArrayList<Pracownik> lista = new ArrayList<>();

        for(Pracownik p: listaPracownikow){
            if(p.getWynagrodzenie() < p.getStanowisko().getPensja()){
                lista.add(p);
            }
        }
        return lista;
    }

    //statystyki firm
    public Map<String, CompanyStatistics> getCompanyStatistics(){

        Map<String, ArrayList<Pracownik>> grupy = new HashMap<>();

        // grupowanie
        for(Pracownik p: listaPracownikow){
            grupy.computeIfAbsent(p.getNazwaFirmy(), f -> new ArrayList<>()).add(p);
        }

        Map<String, CompanyStatistics> statystyki = new HashMap<>();

        for (Map.Entry<String, ArrayList<Pracownik>> entry : grupy.entrySet()) {
            String nazwaFirmy = entry.getKey();
            ArrayList<Pracownik> pracownicy = entry.getValue();

            int liczba = pracownicy.size();

            double suma = 0;
            Pracownik najlepiejPlatny = pracownicy.get(0);

            for(Pracownik p : pracownicy){
                suma += p.getWynagrodzenie();
                if(p.getWynagrodzenie() > najlepiejPlatny.getWynagrodzenie()){
                    najlepiejPlatny = p;
                }
            }

            double avg = suma / liczba;

            statystyki.put(nazwaFirmy, new CompanyStatistics(liczba, avg, najlepiejPlatny));
        }
        return statystyki;
    }
}
