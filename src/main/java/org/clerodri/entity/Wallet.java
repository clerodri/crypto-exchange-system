package org.clerodri.entity;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private Integer balance ;
    private List<CryptoMoney> cryptos;

    public Wallet() {
        this.balance = 0;
        this.cryptos = new ArrayList<>();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<CryptoMoney> getCryptos() {
        return cryptos;
    }

    public void setCryptos(List<CryptoMoney> cryptos) {
        this.cryptos = cryptos;
    }

    @Override
    public String toString() {
        return  "\n" +
                "\t\tBalance: " + balance + "\n"+
                "\t\tCryptos:" + cryptos + "\n";
    }
}
