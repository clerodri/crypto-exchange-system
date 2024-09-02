package org.clerodri.crypto;

import org.clerodri.service.Crypto;

public class Ethereum extends CryptoMoney implements Crypto {


    public Ethereum(Integer quantity, Integer value) {
        super(quantity, value);
    }


    @Override
    public void updateCryptoQuantity(double quantity) {
        double qty = super.getQuantity();
        super.setQuantity(qty - quantity);
    }



    @Override
    public String displayCryptoDetails() {
        return "ETHEREUM : [ Quantity: "+ this.getQuantity() + "\t Price: $" + this.getCryptoPriceExchange()+" ]";
    }
}
