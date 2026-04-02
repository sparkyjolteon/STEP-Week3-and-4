
import java.util.*;

class RiskManagementSystem {

    // Client class
    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        public Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + ":" + riskScore + "($" + accountBalance + ")";
        }
    }

    // ---------------- BUBBLE SORT (ASCENDING riskScore) ----------------
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // visualize swap
                    System.out.println("Swap: " + arr[j] + " <-> " + arr[j + 1]);
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.println("Total Swaps: " + swaps);
    }

    // ---------------- INSERTION SORT (DESC riskScore + balance) ----------------
    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j]; // shift right
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // Comparator: DESC riskScore, then DESC accountBalance
    private static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c2.riskScore, c1.riskScore); // DESC
        }
        return Double.compare(c2.accountBalance, c1.accountBalance); // DESC
    }

    // ---------------- TOP N HIGH RISK ----------------
    public static void printTopN(Client[] arr, int n) {
        System.out.println("Top " + n + " High-Risk Clients:");
        for (int i = 0; i < Math.min(n, arr.length); i++) {
            System.out.println(arr[i].name + "(" + arr[i].riskScore + ")");
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // Clone arrays to demonstrate both sorts independently
        Client[] bubbleArray = clients.clone();
        Client[] insertionArray = clients.clone();

        // Bubble Sort (ASC)
        System.out.println("Bubble Sort (Ascending Risk):");
        bubbleSort(bubbleArray);
        System.out.println("Result: " + Arrays.toString(bubbleArray));

        System.out.println();

        // Insertion Sort (DESC)
        System.out.println("Insertion Sort (Descending Risk + Balance):");
        insertionSort(insertionArray);
        System.out.println("Result: " + Arrays.toString(insertionArray));

        System.out.println();

        // Top 10 (or fewer if not enough data)
        printTopN(insertionArray, 10);
    }
}

