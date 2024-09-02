package org.clerodri;

import org.clerodri.crypto.CryptoType;
import org.clerodri.order.ActionOrder;
import org.clerodri.order.Transaction;
import org.clerodri.user.ExchangeUser;
import org.clerodri.order.Order;
import org.clerodri.user.Wallet;
import org.clerodri.factory.BitcoinFactory;
import org.clerodri.factory.CryptoFactory;
import org.clerodri.factory.EthereumFactory;
import org.clerodri.service.Crypto;

import java.util.*;

public class ExchangeMarket {

    Set<ExchangeUser> users = new HashSet<>();
    List<Order> orderBooks = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();
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
        user.depositFitMoney(amount);
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
               totalCost = (int)(amount * BTC.getCryptoPriceExchange());

                if(checkFunds(user,totalCost)){
                    //update crypto exchange and user exchange
                    BTC.updateCryptoQuantity(amount);
                    user.buyCryptoFromExchange(totalCost,amount, CryptoType.BTC);
                    System.out.println("\tTransaction executed Successfully");
                }else{
                    System.out.println("\nNo founds available");
                }
                break;
            case "2":
                totalCost = (int)(amount * ETH.getCryptoPriceExchange());
                if(checkFunds(user,totalCost)){
                    // update crypto exchange and user exchange
                    ETH.updateCryptoQuantity(amount);
                    user.buyCryptoFromExchange(totalCost,amount, CryptoType.ETH);
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
        System.out.println(BTC.displayCryptoDetails());
        System.out.println(ETH.displayCryptoDetails());
    }

    public void placeBuyOrder(String type, double quantity, int price, ExchangeUser user ){
        Order buyOrder;
        boolean hasFunds;
        switch (type){
            case "1":
                hasFunds = user.validateBalance(price);
                if (hasFunds){
                    buyOrder = new Order(CryptoType.BTC,quantity,price, ActionOrder.BUY, user.getUniqueId());
                    orderBooks.add(buyOrder);
                    checkOrder();
                }else{
                    System.out.println("insufficient balance for your buy order");
                }

                break;
            case "2":
                hasFunds =user.validateBalance(price);
                if (hasFunds){
                    buyOrder = new Order(CryptoType.ETH,quantity,price, ActionOrder.BUY,user.getUniqueId());
                    orderBooks.add(buyOrder);
                }else{
                    System.out.println("insufficient balance for your buy order");
                }

                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void checkOrder(ActionOrder actionOrder, int price, double quantity) {

    }

    public void placeSellOrder(String type, double quantity, int price, ExchangeUser user ){
        Order sellOrder;
        boolean hasCrypto;
        switch (type){
            case "1":
                hasCrypto = user.validateCryptoQuantity(quantity,CryptoType.BTC);
                if (hasCrypto) {
                    sellOrder = new Order(CryptoType.BTC,quantity,price, ActionOrder.SELL,user.getUniqueId());
                    orderBooks.add(sellOrder);
                }else{
                    System.out.println("Bitcoins no available in your balance.");
                }

                break;
            case "2":
                hasCrypto = user.validateCryptoQuantity(quantity,CryptoType.ETH);
                if (hasCrypto) {
                    sellOrder = new Order(CryptoType.ETH,quantity,price, ActionOrder.SELL,user.getUniqueId());
                    orderBooks.add(sellOrder);
                }else{
                    System.out.println("Ethereum no available in your balance.");
                }

                break;
            default:
                System.out.println("Invalid option");
        }
    }
}
