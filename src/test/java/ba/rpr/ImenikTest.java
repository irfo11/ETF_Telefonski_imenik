package ba.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ImenikTest {

    private static Imenik imenik;

    @BeforeAll
    static void kreirajTestniImenik() {
        imenik = new Imenik();
        imenik.dodaj("Mujo Mujic", new MobilniBroj(61, "111-111"));
        imenik.dodaj("Mujo Mujic", new FiksniBroj(Grad.TUZLA, "222-222"));
        imenik.dodaj("Hamdo Hamdovic", new MedunarodniBroj("+387", "333-333"));
        imenik.dodaj("Ensar Ensarovic", new FiksniBroj(Grad.TUZLA, "444-444"));
        imenik.dodaj("Maja Majovic", new FiksniBroj(Grad.SARAJEVO, "555-555"));
        imenik.dodaj("Pero Peric", new MedunarodniBroj("+387", "666-666"));
    }

    @Test
    void dajBroj() {
        String broj = imenik.dajBroj("Mujo Mujic");
        assertEquals("032/222-222\n061/111-111\n", broj);
    }

    @Test
    void dajIme() {
        String ime = imenik.dajIme(new MobilniBroj(61, "111-111"));
        assertEquals("Mujo Mujic", ime);
        ime = imenik.dajIme(new FiksniBroj(Grad.SARAJEVO, "555-555"));
        assertEquals("Maja Majovic", ime);
        ime = imenik.dajIme(new MedunarodniBroj("+387", "333-333"));
        assertEquals("Hamdo Hamdovic", ime);
    }

    @Test
    void dajImeIzuzetak() {
        assertThrows(ElementNijeUImeniku.class, () -> {imenik.dajIme(new MobilniBroj(66, "111-111"));});
    }

    @Test
    void naSlovo() {
        String imenaIBrojevi = imenik.naSlovo('M');
        //ne moze ovako test zato sto HashSet ne cuva redoslijed ubacivanja
        /*assertEquals("1. Mujo Mujic - 061/111-111\n" +
                             "2. Mujo Mujic - 032/222-222\n" +
                             "3. Maja Majovic - 033/555-555\n", imenaIBrojevi);*/
        assertEquals("1. Mujo Mujic - 032/222-222\n" +
                             "2. Maja Majovic - 033/555-555\n" +
                             "3. Mujo Mujic - 061/111-111\n", imenaIBrojevi);
    }

    @Test
    void izGrada() {
        Set<String> imena = imenik.izGrada(Grad.TUZLA);
        TreeSet<String> ocekivanaImena = new TreeSet<String>();
        ocekivanaImena.add("Mujo Mujic");
        ocekivanaImena.add("Ensar Ensarovic");;
        assertIterableEquals(imena, ocekivanaImena);
    }

    @Test
    void izGradaBrojevi() {
        Set<TelefonskiBroj> brojevi = imenik.izGradaBrojevi(Grad.SARAJEVO);
        TreeSet<TelefonskiBroj> ocekivaniBrojevi = new TreeSet<>();
        ocekivaniBrojevi.add(new FiksniBroj(Grad.SARAJEVO, "555-555"));
        assertIterableEquals(ocekivaniBrojevi, brojevi);
    }
}