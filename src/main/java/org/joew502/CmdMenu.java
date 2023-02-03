package org.joew502;

import java.util.Scanner;

import static java.lang.System.exit;

public class CmdMenu {
    public void mainMenu() {
        Scanner menuInput = new Scanner(System.in);
        int menuChoice = 0;
        while (menuChoice != 5) {
            System.out.print("""
                                
                -- Financial Management Software --
                Main Menu:
                1) Load Data
                2) Save Data
                3) Input Income or Expenditure
                4) Display Income or Expenditure
                5) Exit
                                
                Input the number of your choice:""");
            try {
                menuChoice = menuInput.nextInt();
                switch (menuChoice) {
                    case 1 -> new DataLoad().load("program_data.json");
                    case 2 -> new DataSave().save("program_data.json");
                    case 3 -> inAndExpMenu();
                    case 4 -> System.out.println("4");
                    case 5 -> exit(0);
                    default -> System.out.println("Please enter an integer value between 1 and 5");
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and 5");
                menuInput.next();
            }
        }
    }
    public void inAndExpMenu() {
        Scanner menuInput = new Scanner(System.in);
        int menuChoice = 0;
        while (menuChoice != 3) {
            System.out.print("""
                                    
                    -- Input Income or Expenditure --
                                    
                    1) Input Income
                    2) Input Expenditure
                    3) Return to Main Menu
                                    
                    Input the number of your choice:""");
            try {
                menuChoice = menuInput.nextInt();
                switch (menuChoice) {
                    case 1 -> DataStore.jsonData.put("Test_Value","1234");
                    case 2 -> System.out.println(DataStore.jsonData.get("Test_Value"));
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