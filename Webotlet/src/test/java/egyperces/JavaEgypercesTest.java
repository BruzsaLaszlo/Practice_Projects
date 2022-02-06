package egyperces;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class JavaEgypercesTest {

    JavaEgyperces je = new JavaEgyperces();

    @Test
    void legnagyobbReszosszeg() {
        int[] a = {1, 4, 5, -8, 11, -7, -5};
        int[] b = {1, 4, 5, -8, 11, 7, -15, 10, -2, 10, 1, -2, 1};
        assertEquals(4, je.legnagyobbReszosszeg(a));
        assertEquals(10, je.legnagyobbReszosszeg(b));
    }

    @Test
    void szamjegyekOsszege() {
        assertEquals(2, je.szamjegyekOsszege(13637648));
        assertEquals(9, je.szamjegyekOsszege(111111111));
        assertEquals(9, je.szamjegyekOsszege(999999999));
    }

    @Test
    void nagyKezdobetusSzavak() {
        String text = "ez egy mondat lenne, melynek minden szavat nagy kezdobetusse kellene alakitani.";
        String expected = "Ez Egy Mondat Lenne, Melynek Minden Szavat Nagy Kezdobetusse Kellene Alakitani.";
        assertEquals(expected, je.nagyKezdobetusSzavak(text));
    }

    @Test
    void atlok() {
        int[][] matrix = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 6, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
        };

        assertEquals(8, je.atlokENyDK(matrix));
        assertEquals(10, je.atlokEKDNy(matrix));
    }

    @Test
    void idokulonbseg() {
        assertEquals(3600, je.idokulonseg("7:35:40", "8:35:40"));
        assertEquals(3665, je.idokulonseg("7:35:40", "8:36:45"));
    }

    @Test
    void torlesAListabol() {
        List<JavaEgyperces.Kutya> kutyak = new ArrayList<>();
        kutyak.add(new JavaEgyperces.Kutya("tacsko", 3, "fekete"));
        kutyak.add(new JavaEgyperces.Kutya("vizsla", 6, "arany"));
        kutyak.add(new JavaEgyperces.Kutya("vizsla", 3, "fekete"));
        kutyak.add(new JavaEgyperces.Kutya("labrador", 7, "zsemle"));
        kutyak.add(new JavaEgyperces.Kutya("labrador", 5, "arany"));
        kutyak.add(new JavaEgyperces.Kutya("vizsla", 3, "arany"));
        kutyak.add(new JavaEgyperces.Kutya("labrador", 7, "fekete"));
        kutyak.add(new JavaEgyperces.Kutya("vizsla", 3, "barna"));
        kutyak.add(new JavaEgyperces.Kutya("labrador", 7, "barna"));
        kutyak.add(new JavaEgyperces.Kutya("labrador", 1, "zsemle"));
        kutyak.add(new JavaEgyperces.Kutya("tacsko", 2, "fekete"));
        assertEquals(7, je.torlesAListabol(kutyak).size());
    }

    @Test
    void hanyszorVanBenneAzAdottString() {
        String hol = "KKNKKNDDKDKKNKKNKDEDNNENNNNNEKNEEEDKNK" +
                "EEEDKDDNDKDEKEDKNKKDEENKEKKDKEND" +
                "KNNDDDKNDNDDDNEKNEDENDEEDKDDDNDN" +
                "NEDEKNENNEEKDEKKDKKEEEDKDNNKKDNN" +
                "NEDEKKNEEENKKKDEKDKKKN";
        String mit = "KKN";
        assertEquals(6, je.hanyszorVanBenneAzAdottString(hol, mit));
    }

    @Test
    void tobbszorosMegszamlalasV2() {
        String[] napok = {"pentek", "szerda", "csutortok", "hetfo",
                "szerda", "hetfo", "szombat", "vasarnap", "szombat",
                "hetfo", "hetfo", "vasarnap", "szombat", "szombat",
                "szerda", "szombat", "hetfo", "vasarnap",
                "kedd", "szombat", "szombat"};
        String[] honapok = {"november", "november", "junius", "szeptember",
                "szeptember", "januar", "szeptember", "aprilis",
                "augusztus", "aprilis", "december", "oktober",
                "januar", "julius", "augusztus", "december",
                "januar", "majus", "aprilis", "januar", "marcius",
                "szeptember", "februar", "november"};
        assertEquals(7, je.tobbszorosMegszamlalas(napok).get("szombat"));
        assertEquals(4, je.tobbszorosMegszamlalas(honapok).get("szeptember"));
    }

    @Test
    void tobbszorosMegszamlalas() {
        int maxValue = 11;
        int[] tomb = {2, 5, 4, 0, 10, 1, 4, 8, 5, 2, 8, 1, 9, 10,
                7, 7, 3, 5, 2, 4, 3, 8, 0, 8, 10, 8, 1, 1, 8,
                10, 7, 7, 3, 8, 3, 6, 6, 7, 5, 8, 9, 3, 9, 3,
                5, 9, 9, 5, 2, 8, 10, 4, 1, 0, 4, 9, 2, 5, 5};
        assertEquals(5, je.tobbszorosMegszamlalas(tomb, 0, 10)[10]);

        int size = 100;
        final int max = -5;
        final int min = -35;
        Supplier genarateArray = () -> {
            int[] a = new int[size];
            Random r = new Random();
            a = new int[size];
            for (int i = 0; i < size; i++)
                a[i] = r.nextInt(min, max + 1);
            System.out.println(Arrays.toString(a));
            return a;
        };
        tomb = new int[]{-13, -26, -27, -18, -26, -31, -29, -11, -15, -21, -33, -21, -25, -30, -31, -34,
                -24, -31, -13, -22, -32, -30, -27, -34, -9, -9, -24, -27, -18, -10, -26, -27, -6, -24,
                -7, -9, -29, -32, -16, -20, -10, -15, -33, -21, -26, -23, -15, -18, -20, -31, -21, -16,
                -20, -9, -17, -34, -20, -14, -8, -12, -22, -19, -10, -32, -28, -10, -17, -21, -31, -25,
                -18, -6, -17, -26, -5, -20, -13, -23, -19, -24, -7, -11, -29, -9, -27, -22, -34, -11, -10,
                -22, -31, -14, -27, -24, -20, -12, -7, -33, -7, -26};

        assertEquals(1, je.tobbszorosMegszamlalas(tomb, min, max)[-5 - min]);
        assertEquals(6, je.tobbszorosMegszamlalas(tomb, min, max)[-31 - min]);
        assertEquals(0, je.tobbszorosMegszamlalas(tomb, min, max)[-35 - min]);
    }

    @Test
    void epitsunkFalat() {
        assertEquals(true, je.epitsunkFalat(3, 1, 8));
        assertEquals(true, je.epitsunkFalat(6, 1, 11));
        assertEquals(false, je.epitsunkFalat(6, 0, 11));
        assertEquals(true, je.epitsunkFalat(1_000_000, 1000, 1_000_100));
        assertEquals(true, je.epitsunkFalat(43, 1, 46));
        assertEquals(false, je.epitsunkFalat(2, 1_000_000, 100_003));
        assertEquals(false, je.epitsunkFalat(20, 0, 21));
        assertEquals(false, je.epitsunkFalat(20, 4, 51));
    }

    @Test
    void tobbszorosRendezes() {
        JavaEgyperces.Kutya[] kutyak = {
                new JavaEgyperces.Kutya("tacsko", 3),
                new JavaEgyperces.Kutya("labrador", 7),
                new JavaEgyperces.Kutya("labrador", 5),
                new JavaEgyperces.Kutya("vizsla", 1),
                new JavaEgyperces.Kutya("labrador", 2),
                new JavaEgyperces.Kutya("vizsla", 3),
                new JavaEgyperces.Kutya("labrador", 1),
                new JavaEgyperces.Kutya("tacsko", 2)
        };
        je.tobbszorosRendezes(kutyak);
        System.out.println(Arrays.toString(kutyak));
    }

    @Test
    void parseInt(){
        assertEquals(123456, je.parseInt("123456"));
    }

    @Test
    void parseDouble() {
        assertEquals(123.456d,je.parseDouble("123.456"));
    }

}