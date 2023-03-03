package org.joew502;

import org.json.simple.JSONObject;

import java.util.Scanner;
import java.util.Set;

public class CmdInputIncome {
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
                    addIncomeType();
                } else {
                    addIncome((String) incomeTypes.toArray()[menuChoice-1]);
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and "+(i+1));
                menuInput.next();
            }
        }
    }
    private static void addIncomeType() {
        Scanner typeInput = new Scanner(System.in);
        JSONObject incomeData = (JSONObject) Main.jsonData.get("Income");
        int exitValue = 0;
        while (exitValue != 1) {
            System.out.print("\nPlease enter the type of income to add: ");
            try {
                String typeName = typeInput.nextLine();
                if (incomeData.containsKey(typeName)) {
                    System.out.println("This income type already exists");
                } else {
                    ((JSONObject) Main.jsonData.get("Income")).put(typeName, 0);
                    exitValue = 1;
                }
            } catch (Exception e) {
                System.out.println("Please enter a String");
                typeInput.next();
            }
        }
    }
    private static void addIncome(String typeKey) {
        int currentValue = Integer.parseInt(((JSONObject) Main.jsonData.get("Income")).get(typeKey).toString());
        Scanner amountInput = new Scanner(System.in);
        int exitValue = 0;
        while (exitValue != 1) {
            System.out.print("\nPlease enter the amount of income to add to "+typeKey+" (Current Value: "+currentValue
                    +"): ");
            try {
                int changeValue = amountInput.nextInt();
                ((JSONObject) Main.jsonData.get("Income")).put(typeKey, currentValue+changeValue);
                exitValue = 1;
            } catch (Exception e) {
                System.out.println("Please enter an integer");
                amountInput.next();
            }
        }
    }
}
