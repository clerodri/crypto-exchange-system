package org.clerodri.entity;

public class ExchangeUser extends  User{

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
        return "ExchangeUser{" +
                "uniqueId='" + uniqueId + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
