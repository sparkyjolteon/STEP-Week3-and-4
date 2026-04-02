```java
import java.util.*;

public class FeeSortingSystem {

    // Transaction class
    static class Transaction {
        String id;
        double fee;
        String timestamp; // HH:mm

        public Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    // ---------------- BUBBLE SORT ----------------
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early stop
        }

        System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
    }

    // ---------------- INSERTION SORT ----------------
    public static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j)); // shift
                j--;
            }

            list.set(j + 1, key);
        }
    }

    // Comparator: fee first, then timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // ---------------- OUTLIER DETECTION ----------------
    public static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }
        return outliers;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Choose sorting method
        if (transactions.size() <= 100) {
            bubbleSort(transactions);
        } else {
            insertionSort(transactions);
        }

        // Output sorted transactions
        System.out.println("Sorted Transactions:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        // Outliers
        List<Transaction> outliers = findOutliers(transactions);
        System.out.println("High-fee Outliers: " + outliers);
    }
}
```

