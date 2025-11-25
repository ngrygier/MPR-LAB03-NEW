package service;

import model.Pracownik;
import model.Stanowisko;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class PracownikServiceTest {

    private Pracownik p1;
    private Pracownik p2;
    private Pracownik p3;
    private Pracownik p4;
    private Pracownik p5;
    private Pracownik p6;
    private Pracownik p7;


    PracownikService service = new PracownikService();

    @BeforeEach
    public void setUp() {
        service.getSzukaniePoFirmie().clear();
        service.getListaPracownikow().clear();
        service.getSzukaniePoFirmie().clear();

        p1 = new Pracownik("Anna", "Lobejo", "67Corp", "lobejo1@sigma.nd", Stanowisko.STAZYSTA);
        p2 = new Pracownik("Michalinka", "Sin", "67Corp", "michalinka@67gmail.com", Stanowisko.PROGRAMISTA);
        p3 = new Pracownik("Neon", "Cos", "67Corp", "kopeciuch@lampa.com", Stanowisko.PREZES);
        p4 = new Pracownik("Tibi", "Kabanos", "WojCorp", "snusnik@skibidi.com", Stanowisko.WICEPREZES);
        p5 = new Pracownik("Szymon", "Skibidi", "67Corp", "szymik@skibidi.com", Stanowisko.PROGRAMISTA);
        p6 = new Pracownik("Anna", "Lobejo", "67Corp", "lobejo1@sigma.nd", Stanowisko.STAZYSTA);
        p7 = new Pracownik("Julia", "Zugaj", "67Corp", "dupa@dupa.com", Stanowisko.PREZES);

        service.dodajPracownika(p2);
        service.dodajPracownika(p3);
        service.dodajPracownika(p4);
    }

    @Test
    void shouldOnlyAddEmployeesWithUniqueEmails() {
        service.dodajPracownika(p6);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.dodajPracownika(p1);
        });

        assertEquals("Pracownik z podanym adresem email juz istnieje", exception.getMessage());
    }

    @Test
    void shouldAddAllEmployees() {
        assertEquals(3, service.getListaPracownikow().size());
    }

    @Test
    void shouldDisplayAllEmployees() {
        service.dodajPracownika(p1);

        service.wyswietlPracownikow();

        assertEquals(service.getListaPracownikow().size(), service.getWyswietlaniePracownikow().size());

        for (Pracownik p : service.getListaPracownikow()) {
            assert(service.getWyswietlaniePracownikow().contains(p));
        }
    }
    @Test
    void shouldGroupEmployeesByStanowisko() {
        service.dodajPracownika(p1);
        service.dodajPracownika(p5);

        HashMap<Stanowisko, ArrayList<Pracownik>> grupy = service.grupujPoStanowisku();

        for (Stanowisko s : Stanowisko.values()) {
            assert(grupy.containsKey(s));
        }

        assertEquals(1, grupy.get(Stanowisko.STAZYSTA).size());
        assertEquals(2, grupy.get(Stanowisko.PROGRAMISTA).size());
        assertEquals(1, grupy.get(Stanowisko.PREZES).size());
        assertEquals(1, grupy.get(Stanowisko.WICEPREZES).size());

        assert(grupy.get(Stanowisko.STAZYSTA).contains(p1));
        assert(grupy.get(Stanowisko.PROGRAMISTA).contains(p2));
        assert(grupy.get(Stanowisko.PREZES).contains(p3));
        assert(grupy.get(Stanowisko.WICEPREZES).contains(p4));
    }

    @Test
    void shouldCalculateAverageSalary() {
        double expected = 17000;
        assertEquals(expected, service.statystykaWynagrodzenia());
    }
    @Test
    void shouldReturnHighestPaid(){
        ArrayList<Pracownik> results = new ArrayList<>();
        results.add(p3);
        assertEquals(results, service.najlepiejPlatny());
    }

    @Test
    void shouldReturnBothHighestPain(){
        service.dodajPracownika(p7);
        ArrayList<Pracownik> results = new ArrayList<>();
        results.add(p3);
        results.add(p7);
        assertEquals(results, service.najlepiejPlatny());
    }

    @Test
    void ShouldReturnNullIfListEmpty(){
        service.getListaPracownikow().clear();
        assertEquals(null, service.najlepiejPlatny());
    }

   





}
