package org.clerodri;

import org.clerodri.crypto.CryptoMoney;
import org.clerodri.crypto.CryptoType;
import org.clerodri.order.ActionOrder;
import org.clerodri.order.Transaction;
import org.clerodri.user.ExchangeUser;
import org.clerodri.order.Order;
import org.clerodri.user.Wallet;
import java.util.*;


public class ExchangeMarket {
    Map<String, ExchangeUser> users = new HashMap<>();

    List<Order> orderBooks = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();
    CryptoMoney BTC = new CryptoMoney(100,50000);
    CryptoMoney ETH = new CryptoMoney(500,3000);

    public void register(String name,String email, String password ){
        String uniqueID = UUID.randomUUID().toString();
        Wallet wallet = new Wallet();
        ExchangeUser exchangeUser = new ExchangeUser(name,email,password,uniqueID,wallet);
        users.put(uniqueID, exchangeUser);
        //users.add(exchangeUser);
        System.out.print("Exchange User created successfully\n");
        System.out.println(exchangeUser);
    }

    public ExchangeUser login(String email, String password){
        for (ExchangeUser user : users.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
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
        CryptoType cryptoType = getCryptoTypeFromString(type);
        if (cryptoType == null) {
            System.out.println("Invalid option");
            return;
        }
        if (!user.validateBalance(price)) {
            System.out.println("Insufficient funds to place buy order, check your balance");
            return;
        }
        Order buyOrder = new Order(cryptoType, quantity, price, ActionOrder.BUY, user.getUniqueId());
        orderBooks.add(buyOrder);
        List<Order> matchingOrders = findMatchingSellOrders(cryptoType);
        List<Order> findOrder = findPlaceOrder(matchingOrders,buyOrder);
        executePlaceOrder(findOrder, buyOrder, user, cryptoType);
    }

    public void placeSellOrder(String type, double quantity, int price, ExchangeUser user ){
        CryptoType cryptoType = getCryptoTypeFromString(type);
        if (cryptoType == null) {
            System.out.println("Invalid option");
            return;
        }
        if (!user.validateBalance(price)) {
            System.out.println("Insufficient funds to place buy order, check your balance");
            return;
        }
        Order sellOrder = new Order(cryptoType, quantity, price, ActionOrder.SELL, user.getUniqueId());
        orderBooks.add(sellOrder);
        List<Order> matchingOrders = findMatchingBuyOrders(cryptoType);
        List<Order> findOrder = findPlaceOrder(matchingOrders,sellOrder);
        executePlaceOrder(findOrder, sellOrder, user, cryptoType);
    }

    private List<Order> findPlaceOrder(List<Order> orderFilters, Order myOrder){
        List<Order> newList;
        if (myOrder.getAction() == ActionOrder.BUY) {
            newList= orderFilters.stream().filter((order) -> myOrder.getCryptoMoney().compareTo(order.getCryptoMoney()) > 0).toList();

        } else {
            newList= orderFilters.stream().filter((order) -> myOrder.getCryptoMoney().compareTypeSeller(order.getCryptoMoney()) > 0).toList();        //CASE MY ORDER IS TYPE SELLER
        }
        return newList;
    }

    private void executePlaceOrder(List<Order> order, Order myOrder, ExchangeUser user, CryptoType type){
        if (order.isEmpty()) return;
        if(myOrder.getAction() == ActionOrder.BUY){
            Order orderSeller = order.get(0);
            ExchangeUser seller = users.get(orderSeller.getTraderId());
            user.updateCryptoAndBalance(orderSeller.getCryptoMoney().getQuantity(),orderSeller.getCryptoMoney().getValue(),type,ActionOrder.BUY);
            seller.updateCryptoAndBalance(orderSeller.getCryptoMoney().getQuantity(),orderSeller.getCryptoMoney().getValue(),type,ActionOrder.SELL);

        }
        if(myOrder.getAction() == ActionOrder.SELL){
            Order orderBuyer = order.get(0);
            ExchangeUser buyer = users.get(orderBuyer.getTraderId());
            user.updateCryptoAndBalance(orderBuyer.getCryptoMoney().getQuantity(),orderBuyer.getCryptoMoney().getValue(),type,ActionOrder.SELL);
            buyer.updateCryptoAndBalance(orderBuyer.getCryptoMoney().getQuantity(),orderBuyer.getCryptoMoney().getValue(),type,ActionOrder.BUY);
        }
    }


    public void showTransactions(String uniqueId){
        transactions.forEach( transaction -> {
            if(transaction.getTraderId().equals(uniqueId)){
                System.out.println(transaction);
            }
        });
    }


    private List<Order> findMatchingSellOrders(CryptoType cryptoType) {
        return orderBooks.stream()
                .filter(order -> order.getType().equals(cryptoType) && order.getAction().equals(ActionOrder.SELL))
                .toList();
    }
    private List<Order> findMatchingBuyOrders(CryptoType cryptoType) {
        return orderBooks.stream()
                .filter(order -> order.getType().equals(cryptoType) && order.getAction().equals(ActionOrder.BUY))
                .toList();
    }
    private CryptoType getCryptoTypeFromString(String type) {
        return switch (type) {
            case "1" -> CryptoType.BTC;
            case "2" -> CryptoType.ETH;
            default -> null;
        };
    }

}
