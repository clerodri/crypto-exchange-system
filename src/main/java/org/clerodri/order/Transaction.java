package org.clerodri.order;

import org.clerodri.crypto.CryptoType;
import org.clerodri.user.ExchangeUser;



public class Transaction extends Order{
    String buyer;
    String seller;

    public Transaction(Order order, String buyerId, String sellerId) {
        super(order.getType(), order.getCryptoMoney().getQuantity(), order.getCryptoMoney().getValue(), order.getAction());
        this.buyer = buyerId;
        this.seller = sellerId;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "buyer=" + buyer +
                ", seller=" + seller +
                '}';
    }



}
