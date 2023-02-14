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
                    addExpenditureType();
                } else {
                    addExpenditure((String) expenditureTypes.toArray()[menuChoice-1]);
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and "+(i+1));
                menuInput.next();
            }
        }
    }
    private static void addExpenditureType() {
        Scanner typeInput = new Scanner(System.in);
        JSONObject expenditureData = (JSONObject) Main.jsonData.get("Expenditure");
        int exitValue = 0;
        while (exitValue != 1) {
            System.out.print("\nPlease enter the type of expenditure to add: ");
            try {
                String typeName = typeInput.nextLine();
                if (expenditureData.containsKey(typeName)) {
                    System.out.println("This expenditure type already exists");
                } else {
                    ((JSONObject) Main.jsonData.get("Expenditure")).put(typeName, 0);
                    exitValue = 1;
                }
            } catch (Exception e) {
                System.out.println("Please enter a String");
                typeInput.next();
            }
        }
    }
    private static void addExpenditure(String typeKey) {
        int currentValue = Integer.parseInt(((JSONObject) Main.jsonData.get("Expenditure")).get(typeKey).toString());
        Scanner amountInput = new Scanner(System.in);
        int exitValue = 0;
        while (exitValue != 1) {
            System.out.print("\nPlease enter the amount of expenditure to add to "+typeKey+" (Current Value: "
                    +currentValue+"): ");
            try {
                int changeValue = amountInput.nextInt();
                ((JSONObject) Main.jsonData.get("Expenditure")).put(typeKey, currentValue+changeValue);
                exitValue = 1;
            } catch (Exception e) {
                System.out.println("Please enter an integer");
                amountInput.next();
            }
        }
    }
}
