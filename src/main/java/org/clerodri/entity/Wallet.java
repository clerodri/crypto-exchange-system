package org.clerodri.entity;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private Integer balance ;
    private List<Crypto> cryptos;

    public Wallet(Integer balance) {
        this.balance = balance;
        this.cryptos = new ArrayList<>();
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<Crypto> getCryptos() {
        return cryptos;
    }

    public void setCryptos(List<Crypto> cryptos) {
        this.cryptos = cryptos;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "balance=" + balance +
                ", crytos=" + cryptos +
                '}';
    }
}
