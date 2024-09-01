package org.clerodri.entity;

import org.clerodri.service.Crypto;

public class Ethereum extends CryptoMoney implements Crypto {


    public Ethereum(Integer quantity, Integer value) {
        super(quantity, value);
    }


    @Override
    public void updateQuantity(double quantity) {
        double qty = super.getQuantity();
        super.setQuantity(qty - quantity);
    }



    @Override
    public String showDetails() {
        return "ETHEREUM : [ Quantity: "+ this.getQuantity() + "\t Price: $" + this.getValue()+" ]";
    }
}
