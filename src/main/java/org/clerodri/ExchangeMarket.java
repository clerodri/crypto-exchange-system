package org.clerodri;

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

    public void  updateAmountCrypto(CryptoEnum type,Integer quantity){
        if (type == BTC.getType()) BTC.updateQuantity(quantity);
        if (type == ETH.getType()) ETH.updateQuantity(quantity);

    }

    public void deposit(ExchangeUser user, Integer amount){
        user.deposit(amount);
        System.out.println("Deposit Succesfully");
        System.out.println("\tUpdated Balance: "+user.getWallet().toString());
    }
    public void showWalletBalance(ExchangeUser user){
        String message = "Financial status: \n" + user.getWallet().toString();
        System.out.print(message);
    }

}
