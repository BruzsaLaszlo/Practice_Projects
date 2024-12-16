package bruzsal;

import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WinningNumbers6Test {

    @Test
    void parseSorsolas() {

        String line = "1998;53;;2;36 092 040 Ft;3;2 441 611 Ft;139;52 697 Ft;5502;1 330 Ft;76882;332 Ft;7;13;17;23;32;36;40";
        WinningNumbers6 sorsolas = WinningNumbers6.parseSorsolas(line);
        assertEquals(1998, sorsolas.year());
        assertEquals(53, sorsolas.week());
        assertNull(sorsolas.date());
        assertEquals(2, sorsolas.count6());
        assertEquals(36_092_040, sorsolas.prize6());
        assertEquals(3, sorsolas.count5p1());
        assertEquals(2_441_611, sorsolas.prize5p1());
        assertEquals(139, sorsolas.count5());
        assertEquals(52_697, sorsolas.prize5());
        assertEquals(5502, sorsolas.count4());
        assertEquals(1330, sorsolas.prize4());
        assertEquals(76_882, sorsolas.count3());
        assertEquals(332, sorsolas.prize3());
        assertThat(sorsolas.numbers()).containsExactly(7, 13, 17, 23, 32, 36);


        String line2 = "2024;7;2024.02.18.;2;984 179 605 Ft;0;0;71;304 500 Ft;3074;7 035 Ft;45311;2 790 Ft;2;14;17;25;28;32";
        WinningNumbers6 sorsolas2 = WinningNumbers6.parseSorsolas(line2);
        assertEquals(2024, sorsolas2.year());
        assertEquals(7, sorsolas2.week());
        assertThat(sorsolas2.date()).hasYear(2024).hasMonth(Month.FEBRUARY).hasDayOfMonth(18);
        assertEquals(2, sorsolas2.count6());
        assertEquals(984_179_605, sorsolas2.prize6());
        assertEquals(0, sorsolas2.count5p1());
        assertEquals(0, sorsolas2.prize5p1());
        assertEquals(71, sorsolas2.count5());
        assertEquals(304_500, sorsolas2.prize5());
        assertEquals(3074, sorsolas2.count4());
        assertEquals(7035, sorsolas2.prize4());
        assertEquals(45_311, sorsolas2.count3());
        assertEquals(2790, sorsolas2.prize3());
        assertThat(sorsolas2.numbers()).containsExactly(2, 14, 17, 25, 28, 32);
    }

}