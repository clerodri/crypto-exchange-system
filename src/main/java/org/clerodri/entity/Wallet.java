package org.clerodri.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {
    private Integer balance ;

    private Map cryptos ;

    public Wallet() {
        this.balance = 0;
        this.cryptos = new HashMap();
    }

    public Map getCryptos() {
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

