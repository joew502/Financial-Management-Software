package org.joew502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainCmdMenu {
    public void mainMenuSwitch() {
        while (true) {
            switch (mainMenu()) {
                case "1":
                    System.out.println("1");
                    break;
                case "2":
                    System.out.println("2");
                    break;
                case "3":
                    inAndExpMenu();
                    break;
                case "4":
                    System.out.println("4");
                    break;
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
        }
    }

    public String mainMenu() {
        Scanner menuChoice = new Scanner(System.in);
        System.out.print("""
                                
                -- Financial Management Software --
                Main Menu:
                1) Load Data
                2) Save Data
                3) Input Income or Expenditure
                4) Display Income or Expenditure
                                
                Input the number of your choice:""");
        return menuChoice.nextLine();
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
                    case 1:
                        System.out.println("1");
                        break;
                    case 2:
                        System.out.println("2");
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Please enter an integer value between 1 and 3");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer value between 1 and 3");
                menuInput.next();
            }
        }
    }
}