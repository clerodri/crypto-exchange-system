package org.clerodri.entity;

import org.clerodri.service.Crypto;
import org.clerodri.CryptoEnum;

public class Bitcoin extends  CryptoMoney implements Crypto {


    public Bitcoin(Integer quantity, Integer value) {
        super(quantity, value);
    }

    @Override
    public CryptoEnum getType() {
        return CryptoEnum.BTC;
    }

    @Override
    public void updateQuantity(Integer quantity) {
            Integer qty = this.getQuantity();
            this.setQuantity(qty - quantity);
    }
}
