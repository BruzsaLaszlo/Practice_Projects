package geeksforgeeks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumProductSubarrayTest {

    MaximumProductSubarray mps = new MaximumProductSubarray();

    @Test
    void maxProduct() {

        int[] arr = {6, -3, -10, 0, 2};
        int[] arr2 = {2, 3, 4, 5, -1, 0};

        assertEquals(180, mps.maxProduct(arr));
        assertEquals(120, mps.maxProduct(arr2));

    }
}