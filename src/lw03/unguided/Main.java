package lw03.unguided;

import java.util.*;

public class Main {
    public static void main (String[] args){

        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));

        Set<String> registered =  new LinkedHashSet<>();
        Set<String> checkedIn = new LinkedHashSet<>();
        List<String> output = new ArrayList<>();

        int rejected = 0;

        while(sc1.hasNext()){
           String studId = sc1.next();
            registered.add(studId);
        }
        sc1.close();

        while(sc2.hasNext()){
            String studId2 = sc2.next();
        
            if(!registered.contains(studId2)){
                output.add(studId2 + ": Rejected (not registered)"); 
                rejected++;
            } else if (checkedIn.contains(studId2)) {
                output.add(studId2 + ": Rejected (already checked in)");
                rejected++;
            }  else {
                checkedIn.add(studId2);
                output.add(studId2 + ": Checked in");
            }
        }
        sc2.close();

        System.out.println("===== Event Check-In Results =====");
        for(String result : output){
            System.out.println(result);
        }

        System.out.println(" ");
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + (registered.size() - checkedIn.size()));
        System.out.println("Rejected attempts: " + rejected);
        }
    
    }

