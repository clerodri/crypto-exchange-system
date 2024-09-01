package org.clerodri;

import org.clerodri.entity.CryptoType;
import org.clerodri.entity.ExchangeUser;
import org.clerodri.entity.Wallet;
import org.clerodri.service.Crypto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ExchangeMarket {

    private static final Set<ExchangeUser> users = new HashSet<>();
    CryptoFactory btcFactory = new BitcoinFactory();
    CryptoFactory etcFactory = new EthereumFactory();

    // Factory pattern for create Cryptos
    Crypto BTC = btcFactory.createCrypto();
    Crypto ETH = etcFactory.createCrypto();


    public void register(String name,String email, String password ){
        String uniqueID = UUID.randomUUID().toString();
        Wallet wallet = new Wallet();
        ExchangeUser exchangeUser = new ExchangeUser(name,email,password,uniqueID,wallet);
        users.add(exchangeUser);
        System.out.print("Exchange User created successfully\n");
        System.out.println(exchangeUser);
    }

    public ExchangeUser login(String email, String password){
        List<ExchangeUser> checkUser = users.stream().filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).toList();
        if(checkUser.isEmpty()) return null;
        return checkUser.get(0);
    }



    public void deposit(ExchangeUser user, Integer amount){
        user.deposit(amount);
        System.out.println("Deposit Succesfully");
        System.out.println("\tUpdated Balance: "+user.getWallet().toString());
    }

    public void showWalletBalance(ExchangeUser user){
        String message = "\nFinancial status: " + user.getWallet().toString();
        System.out.print(message);
    }

    private boolean checkFunds(ExchangeUser user,  int totalCost){
        return user.getWallet().getBalance() >= totalCost;
    }

    public void buyCryptocurrencies(ExchangeUser user, double amount, String type){
        int totalCost;
        switch (type){
            case "1":
               totalCost = (int)(amount * BTC.getValue());

                if(checkFunds(user,totalCost)){
                    //update crypto exchange and user exchange
                    BTC.updateQuantity(amount);
                    user.buyCrypto(totalCost,amount, CryptoType.BTC);
                    System.out.println("\tTransaction executed Successfully");
                }else{
                    System.out.println("\nNo founds available");
                }
                break;
            case "2":
                totalCost = (int)(amount * ETH.getValue());
                if(checkFunds(user,totalCost)){
                    // update crypto exchange and user exchange
                    ETH.updateQuantity(amount);
                    user.buyCrypto(totalCost,amount, CryptoType.ETH);
                    System.out.println("\nTransaction executed Successfully");
                }else{
                    System.out.println("\tNo founds Available");
                }
                break;
            default:
                System.out.println("Invalid option");
        }

    }

    public void showCryptosMarket(){
        System.out.println(BTC.showDetails());
        System.out.println(ETH.showDetails());
    }
}
