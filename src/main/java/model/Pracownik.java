package model;

import java.util.Objects;

public class Pracownik {
    private String imie;
    private String nazwisko;
    private String nazwaFirmy;
    private String email;
    private int wynagrodzenie;
    private Stanowisko stanowisko;

    public Pracownik(String imie, String nazwisko, String nazwaFirmy, String email, Stanowisko stanowisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwaFirmy = nazwaFirmy;
        this.email = email;
        this.stanowisko = stanowisko;
        wynagrodzenie = stanowisko.getPensja();
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }

    public void setNazwaFirmy(String nazwaFirmy) {
        this.nazwaFirmy = nazwaFirmy;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWygnagrodzenie(int wygnagrodzenie) {
        this.wynagrodzenie = wygnagrodzenie;
    }

    public Stanowisko getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(Stanowisko stanowisko) {
        this.stanowisko = stanowisko;
    }

    @Override //porownywanie dwoch pracownikow
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pracownik pracownik = (Pracownik) o;
        return wynagrodzenie == pracownik.wynagrodzenie && Objects.equals(imie, pracownik.imie) && Objects.equals(nazwisko, pracownik.nazwisko) && Objects.equals(email, pracownik.email) && stanowisko == pracownik.stanowisko;
    }

    @Override //porownywanie hashCode
    public int hashCode() {
        return Objects.hash(imie, nazwisko, email, wynagrodzenie, stanowisko);
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", wygnagrodzenie=" + wynagrodzenie +
                ", stanowisko=" + stanowisko +
                '}';
    }




}
