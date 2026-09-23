package lw01.Unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("Washes.txt"));
        int washCount = sc.nextInt();
        WashService[] washes = new WashService[washCount];

        for (int i = 0; i < washCount; i++) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            if (type.equals("MOTORCYCLE")) {
                washes[i] = new MotorcycleWash(id, days, units);
            } else if (type.equals("CAR")) {
                washes[i] = new CarWash(id, days, units);
            }
        }
        sc.close();

        for (int i = 0; i < washCount; i++) {
            System.out.println(washes[i].summary());
        }
    }
}