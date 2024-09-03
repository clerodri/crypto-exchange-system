package org.clerodri.order;

import org.clerodri.crypto.CryptoMoney;
import org.clerodri.crypto.CryptoType;

public class Order {
    private CryptoType type;
    private CryptoMoney cryptoMoney;
    private ActionOrder action;
    private String traderId;

    public Order(CryptoType type, double quantity, int price, ActionOrder action, String traderId) {
        this.type = type;
        this.cryptoMoney = new CryptoMoney(quantity,price);
        this.action = action;
        this.traderId = traderId;
    }

    public Order(CryptoType type, double quantity, int price, ActionOrder action) {
        this.type = type;
        this.cryptoMoney = new CryptoMoney(quantity,price);
        this.action = action;
    }

    public CryptoMoney getCryptoMoney() {
        return cryptoMoney;
    }

    public String getTraderId() {
        return traderId;
    }

    public CryptoType getType() {
        return type;
    }

//    public double getQuantity() {
//        return quantity;
//    }
//
//    public int getPrice() {
//        return price;
//    }

    public ActionOrder getAction() {
        return action;
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "type=" + type +
//                ", quantity=" + quantity +
//                ", price=" + price +
//                ", action=" + action +
//                ", trader=" + traderId +
//                '}';
//    }
}
