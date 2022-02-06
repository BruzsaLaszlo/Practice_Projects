package programozasifeladatok.altalanosfeladatok;

public class VegyesProgramozasiFeladatok {

    /**
     * Írd ki, hogy melyik a legnagyobb szám a [100;100000] intervallumból,
     * amelyiknek az utolsó számjegye nagyobb, mint az előtte lévő számjegyek összege.
     */
    public int numberOne() {
        int max = 0;

        for (int i = 100; i < 100_000; i++) {
            char[] c = String.valueOf(i).toCharArray();
            int sum = 0;
            for (int j = 0; j < c.length - 1; j++)
                sum += c[j] - '0';
            if (c[c.length - 1] - '0' > sum && max < i)
                max = i;
        }

        return max;
    }

    /**
     * Írd ki 100-tól kezdve a második 10 darab olyan számot,
     * amelyiknek pontosan 7 osztója van (1-et és önmagát figyelmen kívül hagyva).
     */
    public int[] numberTwo() {
        int[] nums = new int[10];

        int count = 0;
        int i = 100;
        while (count != 20) {
            int d = 0;
            for (int j = 2; j <= i / 2; j++)
                if (i % j == 0)
                    d++;
            if (d == 7) {
                count++;
                if (count > 10)
                    nums[count - 10 - 1] = i;
            }
            i++;
        }

        return nums;
    }


    /**
     * Írd ki annak a sorozatnak a 15. elemét, amelyet úgy kapsz meg,
     * hogy minden következő elemet az előző szám számjegyeinek kétszereséből állítod elő!
     * (1, 2, 4, 8, 16, 212, 424, 848, 16816)
     */
    public long numberThree() {
        long n = 1;
        for (int i = 1; i < 15; i++) {
            long sn = 0;
            int d = 0;
            while (n != 0) {
                sn += (n % 10) * 2 * Math.pow(10, d);
                d += n % 10 >= 5 ? 2 : 1;
                n /= 10;
            }
            n = sn;
        }
        return n;
    }





}
