package org.clerodri;

import org.clerodri.entity.ExchangeUser;
import org.clerodri.entity.Wallet;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ExchangeMarket {
    private static Set<ExchangeUser> users = new HashSet<>();


    public void register(String name,String email, String password ){
        String uniqueID = UUID.randomUUID().toString();
        Wallet wallet = new Wallet(0);
        ExchangeUser exchangeUser = new ExchangeUser(name,email,password,uniqueID,wallet);
        users.add(exchangeUser);
        System.out.print("Exchange User created successfully\n");
        System.out.println(exchangeUser);
    }




}
