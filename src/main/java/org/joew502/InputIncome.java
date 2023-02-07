package org.joew502;

import org.json.simple.JSONObject;

import java.util.Scanner;
import java.util.Set;

public class InputIncome {
    public static void incomeMenu() {
        Scanner menuInput = new Scanner(System.in);
        int menuChoice = 0;
        Set incomeTypes = ((JSONObject) Main.jsonData.get("Income")).keySet();
        while (menuChoice != incomeTypes.size()+2) {
            System.out.println("""
                                    
                    -- Input Income --
                    
                    Pick a type to add income to or add a new type
                    """);
            incomeTypes = ((JSONObject) Main.jsonData.get("Income")).keySet();
            int i;
            for (i = 1; i< incomeTypes.size()+1; i++) {
                System.out.println(i+") "+ incomeTypes.toArray()[i-1]);
            }
            System.out.println("\n"+(i)+") Add Income Type");
            System.out.println((i+1)+") Return to Previous Menu");
            System.out.print("\nInput the number of your choice:");
            try {
                menuChoice = menuInput.nextInt();
                if (menuChoice < 1 || menuChoice > i+1) {
                    System.out.println("Please enter an integer value between 1 and "+(i+1));
                } else if (menuChoice == i+1) {
                    break;
                } else if (menuChoice == i) {
                    System.out.println("--- WIP ---");
                    //TODO - Add Income Type
                } else {
                    System.out.println("--- WIP ---");
                    //TODO - Append Income Type
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and "+(i+1));
                menuInput.next();
            }
        }
    }
}
