package org.joew502;

import java.util.Scanner;

public class MainCmdMenu {
    public void mainMenuSwitch(){
        switch (mainMenu()) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                inAndExpMenuSwitch();
                break;
            case "4":
                System.out.println("4");
                break;
            default:
                System.out.println("Not a valid choice");
                mainMenuSwitch();
        }
    }
    public String mainMenu() {
        Scanner MenuChoice = new Scanner(System.in);
        System.out.println("""
                
                -- Financial Management Software --
                Main Menu:
                1) Load Data
                2) Save Data
                3) Input Income or Expenditure
                4) Display Income or Expenditure
                
                Input the number of your choice:""");
        return MenuChoice.nextLine();
    }
    public void inAndExpMenuSwitch(){
        switch (incomeExpenditureMenu()) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            case "3":
                mainMenu();
                break;
            default:
                System.out.println("Not a valid choice");
                inAndExpMenuSwitch();
        }
    }
    public String incomeExpenditureMenu() {
        Scanner MenuChoice = new Scanner(System.in);
        System.out.println("""
                
                -- Input Income or Expenditure --
                
                1) Input Income
                2) Input Expenditure
                3) Return to Main Menu
                
                Input the number of your choice:""");
        return MenuChoice.nextLine();
    }
}
