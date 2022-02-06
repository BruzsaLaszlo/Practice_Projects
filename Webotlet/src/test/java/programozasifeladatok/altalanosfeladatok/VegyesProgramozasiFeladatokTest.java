package programozasifeladatok.altalanosfeladatok;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VegyesProgramozasiFeladatokTest {

    VegyesProgramozasiFeladatok vpf = new VegyesProgramozasiFeladatok();

    @Test
    void numberOne() {
        assertEquals(80009, vpf.numberOne());
    }

    @Test
    void numberTwo() {
        assertEquals(4761, vpf.numberTwo()[9]);
    }

    @Test
    void numberThree() {
        assertEquals(Long.MAX_VALUE,vpf.numberThree());
    }
}