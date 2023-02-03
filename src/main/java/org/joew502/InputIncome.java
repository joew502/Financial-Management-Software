package org.joew502;

import org.json.simple.JSONObject;

import java.util.Scanner;
import java.util.Set;

public class InputIncome {
    public static void incomeMenu() {
        Scanner menuInput = new Scanner(System.in);
        int menuChoice = 0;
        while (menuChoice != 3) {
            System.out.println("""
                                    
                    -- Input Income --
                    
                    Pick a type to add income to or add a new type
                    """);
            Set incomeTypes = ((JSONObject) Main.jsonData.get("Income")).keySet();
            int i;
            for (i = 1; i< incomeTypes.size()+1; i++) {
                System.out.println(i+") "+ incomeTypes.toArray()[i-1]);
            }
            System.out.println((i++)+") Add Income Type");
            System.out.println((i+1)+") Return to Previous Menu");
            System.out.print("\nInput the number of your choice:");
            try {
                menuChoice = menuInput.nextInt();
                switch (menuChoice) {
                    case 1 -> {}
                    case 2 -> {}
                    case 3 -> {}
                    default -> System.out.println("Please enter an integer value between 1 and 3");
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and 3");
                menuInput.next();
            }
        }
    }
}
