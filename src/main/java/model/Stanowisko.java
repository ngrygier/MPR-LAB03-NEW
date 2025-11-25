package model;

public enum Stanowisko {
    PREZES(25000),

    WICEPREZES(18000),

    MANAGER(12000),

    PROGRAMISTA(8000),

    STAZYSTA(3000);

    private final int pensja;

    //przypisywanie konkrretnej pensji do stanowiska
    Stanowisko(int pensja) {
        this.pensja = pensja;
    }

    public int getPensja() {
        return pensja;
    }
}
