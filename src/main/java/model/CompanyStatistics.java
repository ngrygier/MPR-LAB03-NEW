package model;

public class CompanyStatistics {
    public int liczbaPracownikow;
    public double srednieWynagrodzenie;
    public Pracownik osobazNajwyzszymWynagrodzeniem;

    public CompanyStatistics(int liczbaPracownikow, double srednieWynagrodzenie, Pracownik osobazNajwyzszymWynagrodzeniem) {
        this.liczbaPracownikow = liczbaPracownikow;
        this.srednieWynagrodzenie = srednieWynagrodzenie;
        this.osobazNajwyzszymWynagrodzeniem = osobazNajwyzszymWynagrodzeniem;
    }
}
