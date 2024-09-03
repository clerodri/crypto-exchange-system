package org.clerodri.user;

import org.clerodri.crypto.CryptoType;
import org.clerodri.order.ActionOrder;
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


    public Wallet getWallet() {
        return wallet;
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

    @Override
    public void updateCryptoAndBalance(double quantity, int balance, CryptoType type, ActionOrder action) {
        if(action == ActionOrder.BUY){
            if(this.getWallet().getCryptos().get(type)==null){
                int oldBalance = this.getWallet().getBalance();
                this.getWallet().getCryptos().put(type, quantity);
                this.getWallet().setBalance(oldBalance - balance);
            }else{
                double qty = this.getWallet().getCryptos().get(type);
                int oldBalance = this.getWallet().getBalance();
                this.getWallet().getCryptos().replace(type,qty + quantity);
                this.getWallet().getCryptos().replace(type,qty + quantity);
                this.getWallet().setBalance(oldBalance - balance);
            }

        }
        if(action == ActionOrder.SELL){
            double qty = this.getWallet().getCryptos().get(type);
            int oldBalance = this.getWallet().getBalance();
            this.getWallet().getCryptos().replace(type,qty - quantity);
            this.getWallet().setBalance(oldBalance + balance);
        }

    }
}
