package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracownikTest {

    private Pracownik p1;
    private Pracownik p2;
    private Pracownik p3;

    @BeforeEach
    public void setUp() {
        p1 = new Pracownik("Anna", "Lobejo", "TechCorp",
                "lobejo1@sigma.nd", Stanowisko.STAZYSTA);

        p2 = new Pracownik("Michalinka", "Sin", "TechCorp",
                "michalinka67@gmail.com", Stanowisko.PROGRAMISTA);

        p3 = new Pracownik("Michalinka", "Sin", "TechCorp",
                "michalinka67@gmail.com", Stanowisko.PROGRAMISTA);
    }

    @Test // Sprawdzanie, czy pracownicy są sobie równi
    void shouldReturnFalseIfTwoEmployeesAreSameOrNull() {
        boolean result = p1.equals(p2);
        assertEquals(false, result);
    }

    @Test
    void shouldReturnTrueIfTwoEmployeesAreSame() {
        boolean result = p3.equals(p2);
        assertEquals(true, result);
    }

    @Test  // Weryfikacja poprawności enum’a ze stanowiskami
    void shouldReturnCorrectSalaries() {
        int pensjaStazysty = p1.getWynagrodzenie();
        int pensjaProgramisty = p2.getWynagrodzenie();

        assertEquals(3000, pensjaStazysty);
        assertEquals(8000, pensjaProgramisty);
    }
}
