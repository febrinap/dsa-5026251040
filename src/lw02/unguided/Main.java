package lw02.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("orders.txt"));

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successes = new LinkedList<>();
        Queue<String[]> process = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        while (scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();
            orders.add(order);
        }
        scanner.close();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        process.addAll(orders);

        while (!process.isEmpty()) { 
            String[] order = process.poll();

            String foodName = order[1];
            String drinkName = order[2];

            String[] food = null;
            String[] drink = null;

            if (!foodName.equals("-")) {
                for (String[] f : foods) {
                    if (f[0].equals(foodName)) {
                        food = f;
                        break;
                    }
                }
            }

            if (!drinkName.equals("-")) {
                for (String[] d : drinks) {
                    if (d[0].equals(drinkName)) {
                        drink = d;
                        break;
                    }
                }
            }

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            if (food != null) {
                int stock = Integer.parseInt(food[1]);

                if (stock <= 0) {
                    foodAvailable = false;
                }
            }

            if (drink != null) {
                int stock = Integer.parseInt(drink[1]);

                if (stock <= 0) {
                    drinkAvailable = false;
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (food != null) {
                    int stock = Integer.parseInt(food[1]);
                    food[1] = String.valueOf(stock - 1);
                }
                if (drink != null) {
                    int stock = Integer.parseInt(drink[1]);
                    drink[1] = String.valueOf(stock - 1);
                }
                successes.add(order);
            } else {
                failed.push(order);
            }
        } 

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successes) {
            System.out.println(order[0] + 
                " " + order[1] +
                 " " + order[2] + 
                 " " + order[3]
            );
        }

        System.out.println();
        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println();
        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(order[0] + " " + 
            order[1] + " " + 
            order[2] + " " + 
            order[3]
         );
        }
    }
}