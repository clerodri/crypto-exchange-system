package org.clerodri.user;

import org.clerodri.crypto.CryptoType;
import org.clerodri.service.Crypto;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private Integer balance ;
    private HashMap<CryptoType, Double> cryptos ;

    public Wallet() {
        this.balance = 0;
        this.cryptos = new HashMap<>();
    }

    public HashMap<CryptoType, Double> getCryptos() {
        return cryptos;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return  "\n" +
                "\tBalance: " + balance + "\n"+
                "\tCryptos:" + cryptos + "\n";
    }


}

