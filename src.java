import java.util.Arrays;

public class RiskBandSearch {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // ===== LINEAR SEARCH (UNSORTED) =====
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // ===== BINARY SEARCH INSERTION POINT (LOWER_BOUND) =====
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // insertion index
    }

    // ===== FLOOR (largest ≤ target) =====
    public static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] < target) {
                result = arr[mid]; // candidate floor
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ===== CEILING (smallest ≥ target) =====
    public static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) return arr[mid];

            if (arr[mid] > target) {
                result = arr[mid]; // candidate ceiling
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    // ===== MAIN FLOW =====
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // 1. Linear Search (unsorted scenario)
        int index = linearSearch(risks, target);
        System.out.println("Linear Search:");
        System.out.println("Index: " + index);
        System.out.println("Comparisons: " + linearComparisons);

        // 2. Ensure sorted (required for binary)
        Arrays.sort(risks);

        // 3. Binary insertion point (lower_bound)
        int insertPos = lowerBound(risks, target);

        // 4. Floor & Ceiling
        Integer floorVal = floor(risks, target);
        Integer ceilVal = ceiling(risks, target);

        System.out.println("\nBinary Search:");
        System.out.println("Insertion Index: " + insertPos);
        System.out.println("Floor: " + floorVal);
        System.out.println("Ceiling: " + ceilVal);
        System.out.println("Comparisons: " + binaryComparisons);
    }
}
