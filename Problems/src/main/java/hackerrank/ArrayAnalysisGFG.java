package hackerrank;

public class ArrayAnalysisGFG {

    static boolean isalpha(int input_char) {
        if ((input_char >= 65 && input_char <= 90)
            || (input_char >= 97 && input_char <= 122))
            return true;

        return false;
    }

    // Function to find the starting and the
    // ending index of the sub-array with equal
    // number of alphabets and numeric digits
    static void findSubArray(int arr[], int n) {
        int sum = 0;
        int maxsize = -1, startindex = 0;
        for (int i = 0; i < n; i++) {

            // If its an alphabet
            if (isalpha(arr[i])) {
                arr[i] = 0;
            }

            // Else its a number
            else {
                arr[i] = 1;
            }
        }

        // Pick a starting point as i
        for (int i = 0; i < n - 1; i++) {
            sum = (arr[i] == 0) ? -1 : 1;

            // Consider all sub-arrays starting from i
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == 0)
                    sum += -1;
                else
                    sum += 1;

                // If this is a 0 sum sub-array then
                // compare it with maximum size sub-array
                // calculated so far
                if (sum == 0 && maxsize < j - i + 1) {
                    maxsize = j - i + 1;
                    startindex = i;
                }
            }
        }

        // If no valid sub-array found
        if (maxsize == -1)
            System.out.println(maxsize);
        else
            System.out.println(startindex + " " + (startindex + maxsize - 1));
    }

    // Driver code
    public static void main(String[] args) {

        int arr[] = {'A', 'B', 'X', 4, 6, 'X', 'a'};
        int size = arr.length;

        findSubArray(arr, size);
    }

}
