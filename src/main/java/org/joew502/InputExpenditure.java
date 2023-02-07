package org.joew502;

import org.json.simple.JSONObject;

import java.util.Scanner;
import java.util.Set;

public class InputExpenditure {
    public static void expenditureMenu() {
        Scanner menuInput = new Scanner(System.in);
        int menuChoice = 0;
        Set expenditureTypes = ((JSONObject) Main.jsonData.get("Expenditure")).keySet();
        while (menuChoice != expenditureTypes.size()+2) {
            System.out.println("""
                                    
                    -- Input Expenditure --
                    
                    Pick a type to add expenditure to or add a new type
                    """);
            expenditureTypes = ((JSONObject) Main.jsonData.get("Expenditure")).keySet();
            int i;
            for (i = 1; i< expenditureTypes.size()+1; i++) {
                System.out.println(i+") "+ expenditureTypes.toArray()[i-1]);
            }
            System.out.println("\n"+(i)+") Add Expenditure Type");
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
                    //TODO - Add Expenditure Type
                } else {
                    System.out.println("--- WIP ---");
                    //TODO - Append Expenditure Type
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and "+(i+1));
                menuInput.next();
            }
        }
    }
}
