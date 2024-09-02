package org.clerodri.crypto;

import org.clerodri.service.Crypto;

public class Bitcoin extends  CryptoMoney implements Crypto {


    public Bitcoin(Integer quantity, Integer value) {
        super(quantity, value);
    }


    @Override
    public void updateCryptoQuantity(double quantity) {
            double qty = this.getQuantity();
            this.setQuantity(qty - quantity);
    }

    @Override
    public String displayCryptoDetails() {

        return "BITCOIN  : [ Quantity: "+ this.getQuantity() + "\t Price: $" + this.getCryptoPriceExchange()+" ]";
    }

}
