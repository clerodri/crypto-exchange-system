package org.clerodri;

import org.clerodri.crypto.CryptoMoney;
import org.clerodri.crypto.CryptoType;
import org.clerodri.order.ActionOrder;
import org.clerodri.order.Transaction;
import org.clerodri.user.ExchangeUser;
import org.clerodri.order.Order;
import org.clerodri.user.Wallet;


import java.util.*;
import java.util.stream.Stream;

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
        Order buyOrder;
        boolean hasFunds;
        switch (type){
            case "1":
                hasFunds = user.validateBalance(price);
                if (hasFunds){
                    buyOrder = new Order(CryptoType.BTC, quantity,price, ActionOrder.BUY, user.getUniqueId());
                    orderBooks.add(buyOrder);
                    List<Order> ordersFiltered = orderBooks.stream().filter(order -> !order.getAction().equals(ActionOrder.BUY) && order.getType().equals(CryptoType.BTC) ).toList();
                    List<Order> findAnMatch= findPlaceOrder(ordersFiltered,buyOrder);
                    executePlaceOrder(findAnMatch, buyOrder,user,CryptoType.BTC);
                }else{
                    System.out.println("insufficient founds for place buy order, check your balance");
                }
                break;
            case "2":
                hasFunds =user.validateBalance(price);
                if (hasFunds){
                    buyOrder = new Order(CryptoType.ETH,quantity,price, ActionOrder.BUY,user.getUniqueId());
                    orderBooks.add(buyOrder);

                }else{
                    System.out.println("insufficient founds for place buy order, check your balance");
                }

                break;
            default:
                System.out.println("Invalid option");
        }
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
                    List<Order> ordersFiltered = orderBooks.stream().filter(order -> !order.getAction().equals(ActionOrder.SELL) && order.getType().equals(CryptoType.BTC) ).toList();
                    List<Order> findAnMatch= findPlaceOrder(ordersFiltered,sellOrder);
                    executePlaceOrder(findAnMatch, sellOrder,user,CryptoType.BTC);
                }else{
                    System.out.println("insufficient crypto money for place sell order, check your cryptos");
                }

                break;
            case "2":
                hasCrypto = user.validateCryptoQuantity(quantity,CryptoType.ETH);
                if (hasCrypto) {
                    sellOrder = new Order(CryptoType.ETH,quantity,price, ActionOrder.SELL,user.getUniqueId());
                    orderBooks.add(sellOrder);
                }else{
                    System.out.println("insufficient crypto money for place sell order, check your cryptos");
                }

                break;
            default:
                System.out.println("Invalid option");
        }
    }
    //
    private List<Order> findPlaceOrder(List<Order> orderFilters, Order myOrder){
        List<Order> newList;
        if (myOrder.getAction() == ActionOrder.BUY) {
             newList= orderFilters.stream().filter((order) -> myOrder.getCryptoMoney().compareTo(order.getCryptoMoney()) > 0).toList();

        } else {
            newList= orderFilters.stream().filter((order) -> myOrder.getCryptoMoney().compareTypeSeller(order.getCryptoMoney()) > 0).toList();        //CASE MY ORDER IS TYPE SELLER
        }
        System.out.println("LISTA CHEQUEADAS: "+newList);
    return newList;
    }

    private void executePlaceOrder(List<Order> order, Order myOrder, ExchangeUser user, CryptoType type){
        System.out.println("entro execute place");
        if (order.isEmpty()) return;

        if(myOrder.getAction() == ActionOrder.BUY){
            Order orderSeller = order.get(0);
            ExchangeUser seller = users.get(orderSeller.getTraderId());
            System.out.println("entro 2");
            user.updateCryptoAndBalance(orderSeller.getCryptoMoney().getQuantity(),orderSeller.getCryptoMoney().getValue(),type,ActionOrder.BUY);
            System.out.println("entro 3");
            seller.updateCryptoAndBalance(orderSeller.getCryptoMoney().getQuantity(),orderSeller.getCryptoMoney().getValue(),type,ActionOrder.SELL);
            System.out.println("entro 4");
        }
        if(myOrder.getAction() == ActionOrder.SELL){
            System.out.println("entro 5");
            Order orderBuyer = order.get(0);
            System.out.println("entro 6");
            ExchangeUser buyer = users.get(orderBuyer.getTraderId());
            System.out.println("entro 7");
            user.updateCryptoAndBalance(orderBuyer.getCryptoMoney().getQuantity(),orderBuyer.getCryptoMoney().getValue(),type,ActionOrder.SELL);
            System.out.println("entro 8");
            buyer.updateCryptoAndBalance(orderBuyer.getCryptoMoney().getQuantity(),orderBuyer.getCryptoMoney().getValue(),type,ActionOrder.BUY);
        }
    }

}
