package service;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import exception.InvalidDataException;
import model.ImportSummary;
import model.Pracownik;
import model.Stanowisko;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class ImportService {
    public static ImportSummary ImportFromCSV(String sciezka)throws IOException , CsvException{
        int zaimportowani = 0;
        int bledy = 0;
        try(CSVReader reader = new CSVReader(new FileReader(sciezka))){
            //czyta caly plis csv
            List<String[]> allRows = reader.readAll();
            //indeksowanie od 1, bo bez naglowkow
            for(int i = 1; i<allRows.size(); i++){
                String[] row = allRows.get(i);
                String firstName = row[0];
                String lastName = row[1];
                String email = row[2];
                String stanowisko = row[3];
                String compamyName = row[4];
                int wynagrodzenie = Integer.parseInt(row[5]);

                if(Objects.equals(stanowisko, String.valueOf(Stanowisko.valueOf(stanowisko.toUpperCase()))) && (wynagrodzenie>0)){
                    //jezeli wszystko sie zgadza, importujemy pracownika
                    new Pracownik(firstName, lastName, compamyName, email, Stanowisko.valueOf(stanowisko.toUpperCase()));
                    zaimportowani++;
                }
                else{
                    System.out.println("bledne stanowisko lub wynagrodzenie w wierszu numer " + i);
                    bledy++;
                }

            }
        }
        return new ImportSummary(bledy, zaimportowani); //zwraca liste pracownikow blednych i prawidlowych
        }
}
