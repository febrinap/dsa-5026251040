package lw04.Unguided;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = scanner.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        scanner.close();

        int[] after2Passes = new int[n];

        bubbleSortDescending(parcels, after2Passes);

        System.out.println("==== After 2 Passes ====");
        printArray(after2Passes);

        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        System.out.println("==== Search Results ====");
        for (int target : targets) {
            binarySearchDescending(parcels, target);
        }
    }

    static void bubbleSortDescending(int[] arr, int[] copyAfter2Passes) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (i == 1) {
                for (int k = 0; k < n; k++) {
                    copyAfter2Passes[k] = arr[k];
                }
            }
        }
    }

    static void binarySearchDescending(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {
            steps++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                System.out.println(target + ": FOUND at index " + mid + " (steps: " + steps + ")");
                return;
            } else if (arr[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
    }

    
    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i == arr.length - 1 ? "" : " "));
        }
        System.out.println();
    }
}