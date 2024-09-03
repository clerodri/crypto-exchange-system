package org.clerodri;

import org.clerodri.user.ExchangeUser;
import org.clerodri.user.Wallet;

import java.util.Scanner;

public class Main {
    static ExchangeMarket market = new ExchangeMarket();
    public static void main(String[] args) {
        Menu();

    }


    private static void Menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            String option;
            String email;
            String password;
            do {
                LabelMenu();
                System.out.println("Enter option:");
                option = scanner.next();
                scanner.nextLine();
                switch (option) {
                    case "1":
                        System.out.println("\nEnter your name:");
                        String userName = scanner.nextLine();
                        System.out.println("Enter your email:");
                        email = scanner.nextLine();
                        System.out.println("Enter your new password:");
                        password = scanner.nextLine();
                        //create user by a services
                        market.register(userName,email,password);
                        break;
                    case "2":
                        System.out.println("\nEnter your email:");
                        email = scanner.nextLine();
                        System.out.println("Enter your password:");
                        password = scanner.nextLine();
                        ExchangeUser user = market.login(email,password);
                        if(user==null) {
                            System.out.println("Credentials Incorrect or User not registered, try again");
                        }else{
                            System.out.print("Logging Successfully\n");
                            HomeMenu(scanner,user);
                        }

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



    private static void HomeMenu(Scanner scanner, ExchangeUser user){
            String optionHome;
            String type="";
            double amount;
            int price;
            do {
                LabelHome();
                System.out.println("\nEnter an option:");
                optionHome = scanner.next();
                scanner.nextLine();
                switch (optionHome) {
                    case "1":
                        System.out.println("##########   CHECK BALANCE   #########");
                        System.out.println("Enter an amount:");
                        amount = scanner.nextInt();
                        // LOGIC FOR UPDATE YOUR WALLET
                        market.deposit(user, (int) amount);
                        System.out.printf("Deposity successefully: your new Balance is: %s\n",amount);
                        break;
                    case "2":
                        market.showWalletBalance(user);
                        break;
                    case "3":
                          // Shows bitcoins del exchange
                        System.out.println("##########   BUY CRYPTO MONEYS   #########");
                        labelCryptoExchange();
                        System.out.println("\n");
                        System.out.println("Choose an option:");
                        type = scanner.nextLine();
                        System.out.println("Enter an amount:");
                        amount = scanner.nextDouble();
                        //CHECK ENOUGH FUNDS
                        market.buyCryptocurrencies(user, amount,type);
                        break;
                    case "4":
                        System.out.println("##########   PLACE BUY ORDER   #########");
                        System.out.println("1. BITCOIN");
                        System.out.println("2. ETHEREUM");
                        System.out.println("Choose an option:");
                        type = scanner.nextLine();
                        System.out.println("Enter an quantity:");
                        amount = scanner.nextDouble();
                        System.out.println("Enter max price acceptable:");
                        price = scanner.nextInt();
                        //add logic of place buy order
                        market.placeBuyOrder(type,amount,price,user);
                        break;
                    case "5":
                        System.out.println("##########   PLACE SELL ORDER   #########");
                        System.out.println("1. BITCOIN");
                        System.out.println("2. ETHEREUM");
                        System.out.println("Choose an option:");
                        type = scanner.nextLine();
                        System.out.println("Enter an quantity:");
                        amount = scanner.nextDouble();
                        System.out.println("Enter min price acceptable:");
                        price = scanner.nextInt();
                        //add logic place sell order
                        market.placeSellOrder(type,amount,price,user);

                        break;
                    case "6":
                        System.out.println("########### List of transactions ##########");
                        //SHOW ALL YOUR TRX FROM TRANSACTIONS LIST
                        market.showTransactions(user.getUniqueId());
                        break;
                    case "7":
                        System.out.println("Logout Successfully:\n");
                        break;
                }
            }
            while (!optionHome.equals("7"));

    }

    private static void labelCryptoExchange(){
        System.out.println("Current CryptosMoney Available");
        market.showCryptosMarket();
        System.out.println("1. BITCOIN");
        System.out.println("2. ETHEREUM");
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



    private static void LabelMenu(){
        System.out.println("\n#####   Crypto Exchange System   #####\n");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit:");
    }
}