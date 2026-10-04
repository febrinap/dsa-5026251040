package lw03.prelab;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new LinkedList<>();

        while (sc1.hasNext()) {
            String command = sc1.next();
            if (command.equals("ADD")) {
                String song = sc1.nextLine().trim();
                playlist.add(song);
            } else if (command.equals("INSERT")) {
                int index = sc1.nextInt();
                String song = sc1.nextLine().trim();
                playlist.add(index, song);
            } else if (command.equals("REMOVE")) {
                String song = sc1.nextLine().trim();
                playlist.remove(song);
            }
        }
        sc1.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        int songIndex = 1;
        for (String song : playlist) {
            System.out.println(songIndex + ": " + song);
            songIndex++;
        }

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;

        while (sc2.hasNext()) {
            String name = sc2.next();
            
            boolean isAdded = participants.add(name);
            if (!isAdded) {
                duplicates++;
            }
        }
        sc2.close();

        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int rank = 1;
        for (String participant : participants) {
            System.out.println(rank + ". " + participant);
            rank++;
        }
        System.out.println("Duplicate registrations: " + duplicates);

        Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        while (sc3.hasNext()) {
            String type = sc3.next();
            String product = sc3.next();
            int qty = sc3.nextInt();

            if (type.equals("ADD")) {
                int current = inventory.getOrDefault(product, 0);
                inventory.put(product, current + qty);
            } else if (type.equals("SELL")) {
                int current = inventory.getOrDefault(product, 0);
                
                if (inventory.containsKey(product) && current >= qty) {
                    inventory.put(product, current - qty);
                } else {
                    failedSales++;
                }
            }
        }
        sc3.close();

        System.out.println();
        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);

    }
}