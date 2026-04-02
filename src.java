import java.util.Arrays;

class TransactionSearch {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // ===== LINEAR SEARCH (FIRST + LAST) =====
    public static int[] linearSearch(String[] arr, String target) {
        int first = -1, last = -1;

        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }
        return new int[]{first, last};
    }

    // ===== BINARY SEARCH (ANY ONE MATCH) =====
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // ===== FIND FIRST OCCURRENCE (BINARY) =====
    public static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // move left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ===== FIND LAST OCCURRENCE (BINARY) =====
    public static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // move right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ===== COUNT OCCURRENCES =====
    public static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    // ===== MAIN FLOW =====
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // 1. Linear Search (unsorted)
        int[] res = linearSearch(logs, "accB");
        System.out.println("Linear Search:");
        System.out.println("First Index: " + res[0] + ", Last Index: " + res[1]);
        System.out.println("Comparisons: " + linearComparisons);

        // 2. Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("\nSorted Logs:");
        System.out.println(Arrays.toString(logs));

        // 3. Binary Search + Count
        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("\nBinary Search:");
        System.out.println("Any Index: " + index);
        System.out.println("Count: " + count);
        System.out.println("Comparisons: " + binaryComparisons);
    }
}
