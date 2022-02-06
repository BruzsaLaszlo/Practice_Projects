package egyperces;


import java.util.*;

public class JavaEgyperces {

    /**
     * Adott egy egész számokból álló tetszőleges intervallumból feltöltött tömb.
     * Melyik az az elem, ameddig a legnagyobb a tömb elemeinek részösszege a tömb elejétől kezdődően?
     * Pl {1,4,5,-8,11,-7,-5} -> 11
     */
    public int legnagyobbReszosszeg(int[] a) {
        int index = 0;
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (maxSum < sum) {
                index = i;
                maxSum = sum;
            }
        }
        return index;
    }

    /**
     * Sorsolj ki egy pozitív egész számot, ami legfeljebb 1 milliárd lehet.
     * Add össze a szám számjegyeit, majd a kapott szám számjegyeit ismét add össze.
     * Addig ismételd, amíg egyetlen számjegy nem marad, majd írd ki a végeredményt.
     * Példa:
     * 13637648
     * 38
     * 11
     * 2
     */
    public int szamjegyekOsszege(int number) {

        int sum = number;
        char[] c;
        do {
            c = String.valueOf(sum).toCharArray();
            sum = 0;
            for (char cc : c)
                sum += cc - '0';
        }
        while (sum > 9);

        return sum;

    }

    /**
     * Alakíts át egy mondatot úgy, hogy minden szó kezdőbetűjét alakítsd nagybetűssé.
     * <p>
     * String s = "ez egy mondat lenne, melynek minden szavat nagy "
     * "kezdobetusse kellene alakitani.";
     * Eredmény:
     * <p>
     * Ez Egy Mondat Lenne, Melynek Minden Szavat Nagy Kezdobetusse Kellene Alakitani.
     */
    public String nagyKezdobetusSzavak(String text) {
        char[] c = text.toCharArray();
        c[0] = Character.toUpperCase(c[0]);
        for (int i = 0; i < c.length; i++) {
            if (Character.isWhitespace(c[i]))
                c[i + 1] = Character.toUpperCase(c[i + 1]);
        }
        return String.valueOf(c);
    }

    /**
     * Adott a növények feladat forrása. A feladat az, hogy válogasd szét a növényeket aszerint,
     * hogy melyiket milyen részéért gyűjtik. A feladat megoldása a következőképp nézzen ki:
     * Altermes:
     * Noveny1 neve, kezdes, befejezes
     * Gyoker:
     * Noveny2 neve, kezdes, befejezes;
     * Noveny3 neve, kezdes, befejezes;
     * Noveny4 neve, kezdes, befejezes
     * Gyokertorzs:
     * Noveny5 neve, kezdes, befejezes;
     * Noveny6 neve, kezdes, befejezes
     * Level:
     * ....
     * ....
     * // noveny.txt
     */
    public void ObjektumokValogatasa() {
        // TODO
    }

    /**
     * Adott egy N*N-es négyzetrács, amelybe számokat írunk.
     * A feladat az, hogy a négyzet északnyugat-délkeleti átlói közül melyikben legnagyobb a számok összege.
     *
     * @return a legnagyobb átló összege
     */
    public int atlokENyDK(int[][] m) {
        final int size = m.length;
        final int sumSize = 2 * size - (size % 2 == 0 ? 0 : 1);

        int[] sum = new int[sumSize];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum[i - j + size - 1] += m[i][j];
            }
        }
        return maximumKereses(sum);
    }

    public int atlokEKDNy(int[][] m) {
        final int size = m.length;
        final int sumSize = 2 * size - (size % 2 == 0 ? 0 : 1);

        int[] sum = new int[sumSize];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sum[i + j] += m[i][j];
            }
        }
        return maximumKereses(sum);
    }

    private int maximumKereses(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i])
                max = array[i];
        }
        return max;
    }

    /**
     * Kombinált megszámlálás
     */
    public void kombinaltMegszamlalas() {
        // TODO
    }

    /**
     * Adott két időpont, melyeknél óra:perc:másodperc formában vannak megadva az időpontok.
     * A kérdés az, hogy mennyi idő telt el a két időpont között.
     *
     * @return secundum
     */
    public int idokulonseg(String time, String anotherTime) {
        int l1 = time.length();
        int l2 = anotherTime.length();
        int h1 = Integer.parseInt(time.substring(0, l1 - 6));
        int h2 = Integer.parseInt(anotherTime.substring(0, l2 - 6));
        int m1 = Integer.parseInt(time.substring(l1 - 5, l1 - 3));
        int m2 = Integer.parseInt(anotherTime.substring(l2 - 5, l2 - 3));
        int s1 = Integer.parseInt(time.substring(l1 - 2));
        int s2 = Integer.parseInt(anotherTime.substring(l2 - 2));
//        System.out.printf("%d:%d:%d", h1, m1, s1);
//        System.out.println();
//        System.out.printf("%d:%d:%d", h2, m2, s2);

        return Math.abs(h1 * 3600 - h2 * 3600 + m1 * 60 - m2 * 60 + s1 - s2);
    }

    static class Kutya {
        private final String fajta;
        private final int kor;
        private String szin;

        public Kutya(String f, int k, String sz) {
            fajta = f;
            kor = k;
            szin = sz;
        }

        public Kutya(String fajta, int kor) {
            this.fajta = fajta;
            this.kor = kor;
        }

        @Override
        public String toString() {
            return "fajta= " + fajta + ", kor= " + kor +
                    ", szin= " + szin + '\n';
        }

    }

    /**
     * Adott egy lista, melyben kutyák adatai vannak.
     * A feladat az, hogy töröld ki a listából a vizslák adatait.
     *
     * @return
     */
    public List<Kutya> torlesAListabol(List<Kutya> kutyak) {
        List<Kutya> delete = new ArrayList<>();
        for (Kutya k : kutyak)
            if ("vizsla".equals(k.fajta))
                delete.add(k);
        kutyak.removeAll(delete);
        return kutyak;
    }

    /**
     * Egy tetszőleges Stringben meg kell számolni, hogy egy másik String hányszor fordul elő
     */
    public int hanyszorVanBenneAzAdottString(String hol, String mit) {
        int counter = 0;
        for (int i = 0; i < hol.length() - 2; i++)
            if (hol.substring(i, i + 3).equalsIgnoreCase(mit))
                counter++;
        return counter;
        //return (hol.length() - hol.replaceAll( mit, "" ).length()) / mit.length();
        //return hol.split(mit, -1).length - 1;
    }

    /**
     * Számold meg, mi hányszor szerepel a tömbben
     */
    public Map<String, Integer> tobbszorosMegszamlalas(String[] strings) {
        Map<String, Integer> strs = new HashMap<>();
        for (String s : strings)
            if (strs.containsKey(s))
                strs.replace(s, strs.get(s) + 1);
            else
                strs.put(s, 1);
        return strs;
    }

    /**
     *
     */
    public int[] tobbszorosMegszamlalas(int[] tomb, int rangeMin, int rangeMax) {
        int[] a = new int[rangeMax - rangeMin + 1];
        for (int i = 0; i < tomb.length; i++)
            a[tomb[i] - rangeMin]++;
        return a;
    }

    /**
     * Adott 3 változó:
     * s: small, vagyis kis téglák darabszáma, magasságuk egyenként 1 egység
     * b: big, vagyis nagy téglák darabszáma, magasságuk egyenként 5 egység
     * w: wall, vagyis a fal magassága
     * <p>
     * A feladat az, hogy vizsgáld meg, a rendelkezésre álló téglákból megépíthető-e az adott falmagasság. Nem alacsonyabb, nem magasabb, pontosan annyi. Hiába van 4 nagy téglád, abból csak 5-10-15-20 magasságú falakat építhetsz, de 17-est, 8-ast, 14-est, stb nem.
     * <p>
     * A feladat megoldása során nem használhatsz semmilyen ciklust, vagy beépített osztályt!
     */
    public boolean epitsunkFalat(int small, int big, int wall) {
        if (wall <= big * 5)
            if (wall % 5 <= small)
                return true;
            else return false;
        else if (wall <= big * 5 + small)
            return true;
        else return false;
    }

    /**
     * Adott egy String. Deklaráljunk egy boolean változót, melynek értéke true legyen, ha a String tartalma “1”, és false, ha “0”.
     * <p>
     * Itt egy megoldás, amit a Java nyelvvel ismerkedők szoktak megadni:
     * <p>
     * String s = "1";
     * boolean b;
     * <p>
     * if( s.equals("1") )
     * {
     * b = true;
     * }
     * else
     * {
     * b = false;
     * }
     */
    public boolean logikaiErtekEgyszerubben(String s) {
        return s.equals("1");
    }

    /**
     * Adott egy osztály, melyben kutyákat tartunk nyilván.
     * A kutyáknak csak két tulajdonsága van, fajtája és életkora.
     * Legyen 5 kutyánk egy tömbben.
     * Rendezd a kutyákat fajta, azon belül életkor szerint növekvő sorrendbe.
     */
    public void tobbszorosRendezes(Kutya[] kutyak) {
        {
            int index = kutyak.length;
            while (index != 0) {
                int j = 0;
                int i = 0;
                while (j < index - 1) {
                    if (kutyak[j].fajta.compareTo(kutyak[j + 1].fajta) > 0 ||
                            kutyak[j].kor > kutyak[j + 1].kor && kutyak[j].fajta.equals(kutyak[j + 1].fajta)) {
                        Kutya k = kutyak[j];
                        kutyak[j] = kutyak[j + 1];
                        kutyak[j + 1] = k;
                        i = j + 1;
                    }
                    j++;
                }
                index = i;
            }
        }
    }


    /**
     * Írj programot, amely egy string-ként megadott egész számot átkonvertál valódi számmá.
     * Az egyetlen probléma, hogy konverziós függvényeket (*.parse*) nem használhatod, az átalakítást neked kell megírni!
     * Ha ez megvan, akkor írd meg ugyanezt úgy, hogy lebegőpontos számokra is működjön!
     */

    public int parseInt(String strToInt) {

        final int length = strToInt.length();
        int num = 0;
        for (int i = 0; i < length; i++) {
            num += Math.pow(10, length - 1 - i) * (strToInt.charAt(i) - '0');
        }
        return num;

    }

    public double parseDouble(String strToDouble) {

        int iPoint = strToDouble.indexOf(".");
        if (iPoint < 0)
            return parseInt(strToDouble);
        int dCount = iPoint;
        int tCount = strToDouble.length() - iPoint - 1;
        double num = parseInt(strToDouble.substring(0, dCount));
        for (int i = 1; i <= tCount; i++) {
            num += Math.pow(10, -i) * (strToDouble.charAt(iPoint + i) - '0');
        }
        return num;

    }

}
