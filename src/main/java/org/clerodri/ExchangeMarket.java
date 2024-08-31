package org.clerodri;

import org.clerodri.entity.ExchangeUser;
import org.clerodri.entity.Wallet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExchangeMarket {
    private static final Set<ExchangeUser> users = new HashSet<>();


    public void register(String name,String email, String password ){
        String uniqueID = UUID.randomUUID().toString();
        Wallet wallet = new Wallet(0);
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



}
