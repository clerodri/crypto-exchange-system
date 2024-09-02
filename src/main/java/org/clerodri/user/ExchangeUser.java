package org.clerodri.user;

import org.clerodri.crypto.CryptoType;
import org.clerodri.service.Market;

public class ExchangeUser extends  User implements Market {

    private String uniqueId;
    private Wallet  wallet;

    public ExchangeUser(String name, String email, String password, String uniqueId, Wallet wallet) {
        super(name, email, password);
        this.uniqueId = uniqueId;
        this.wallet = wallet;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return
                "\tUniqueId: " + uniqueId + "\n"+
                "\tWallet: " + wallet.toString()+"\n";
    }

    @Override
    public void depositFitMoney(Integer amount) {
        Integer oldAmount = this.wallet.getBalance();
        this.wallet.setBalance(oldAmount + amount);
    }

    @Override
    public void buyCryptoFromExchange(int totalCost, double quantity, CryptoType type) {
        Integer oldBalance = this.wallet.getBalance();
        this.wallet.setBalance(oldBalance - totalCost);
        this.wallet.getCryptos().put(type, quantity);

    }

    @Override
    public boolean validateCryptoQuantity(double quantity,CryptoType type) {
        double value =  this.wallet.getCryptos().get(type);
        return value >= quantity;
    }

    @Override
    public boolean validateBalance(int amount) {
        return this.getWallet().getBalance()>=amount;
    }
}
