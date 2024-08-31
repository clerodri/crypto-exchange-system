package org.clerodri;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu();
    }


    private static void Menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            String option;
            do {
                LabelMenu();
                System.out.println("Enter option:");
                option = scanner.next();
                scanner.nextLine();
                switch (option) {
                    case "1":
                        System.out.println("Enter your name:");
                        String userName = scanner.nextLine();
                        System.out.println("Enter your email:");
                        String userMail = scanner.nextLine();
                        System.out.println("Enter your new Password:");
                        String userPassword = scanner.nextLine();
                        //create user by an services
                        System.out.printf("Usuario: %s - created successfully\n", userName);
                        break;
                    case "2":
                        System.out.print("Logging Successfully\n");
                        HomeMenu(scanner);
                        break;
                    case "3":
                        System.out.println("Program finished, thanks.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Option no valid, try again");

                }
            }
            while (true);


        }
    }



    private static void HomeMenu(Scanner scanner){
            String optionHome;
            String type="";
            String amount="";
            do {
                LabelHome();
                System.out.println("\n Enter an option:");
                optionHome = scanner.next();
                scanner.nextLine();
                switch (optionHome) {
                    case "1":
                        System.out.println("Enter an amount:");
                        amount = scanner.nextLine();
                        // LOGIC FOR UPDATE YOUR WALLET
                        System.out.printf("Deposity successefully: your new Balance is: %s\n",amount);
                        break;
                    case "2":
                        System.out.println("\n");
                        System.out.println("\t\nCurrent Balance: \n");
                        System.out.println("\t\nCryptoCurrencies: \n");
                        break;
                    case "3":
                        System.out.println("\n");
                        System.out.println("Enter an type:");
                        type = scanner.nextLine();
                        System.out.println("Enter an amount:");
                        amount = scanner.nextLine();
                        //CHECK ENOUGH FUNDS
                        System.out.println("\t\n Transaction Executed Successfully \n");
                        break;
                    case "4":
                        System.out.println("\n");
                        System.out.println("Enter an type:");
                        type = scanner.nextLine();
                        System.out.println("Enter an amount:");
                        amount = scanner.nextLine();
                        System.out.println("Enter max price acceptable:");
                        String maxPrice = scanner.nextLine();
                        //add logic of place buy order
                        break;
                    case "5":
                        System.out.println("\n");
                        System.out.println("Enter an type:");
                        type = scanner.nextLine();
                        System.out.println("Enter an amount:");
                        amount = scanner.nextLine();
                        System.out.println("Enter min price acceptable:");
                        String minPrice = scanner.nextLine();
                        //add logic place sell order
                        break;
                    case "6":
                        System.out.println("List of transactions:");
                        System.out.println("\t*****");
                        System.out.println("\t*****");
                        //SHOW ALL YOUR TRX FROM TRANSACTIONS LIST
                        break;
                    case "7":
                        System.out.println("Logging out Successfully:\n");
                        break;
                }
            }
            while (!optionHome.equals("7"));

    }



    private static void LabelMenu(){
        System.out.println("\n#####   Crypto Exchange System   #####\n");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit:");
    }
    private static void LabelHome(){
        System.out.println("\n#####   Home   #####");
        System.out.println("1. Deposit Money");
        System.out.println("2. View Wallet Balance");
        System.out.println("3. Buy Cryptocurrencies");
        System.out.println("4. Place Buy Order");
        System.out.println("5. Place Sell Order");
        System.out.println("6. View Transaction History");
        System.out.println("7. Logout");
    }
}