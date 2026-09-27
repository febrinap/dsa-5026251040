package lw02.prelab;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        InputStream inputStream = Main.class.getResourceAsStream("transactions.txt");
        if (inputStream == null) {
            System.err.println("File transactions.txt tidak ditemukan!");
            return;
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            String[] transaction = new String[3];
            transaction[0] = scanner.next(); 
            transaction[1] = scanner.next(); 
            transaction[2] = scanner.next(); 
            transactions.add(transaction);

            boolean exists = false;
            for (String[] cust : customerList) {
                if (cust[0].equals(transaction[0])) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                customerList.add(new String[]{transaction[0], "0"});
            }
        }
        scanner.close();

        Queue<String[]> transactionQueue = new LinkedList<>(transactions);

        Stack<String[]> failedStack = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] tx = transactionQueue.poll();
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            String[] targetCustomer = null;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW")) {
                    if (amount > currentBalance) {
                        // Transaksi gagal dimasukkan ke Stack
                        failedStack.push(tx);
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failedTx = failedStack.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}