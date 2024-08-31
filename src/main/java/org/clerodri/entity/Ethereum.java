package org.clerodri.entity;

import org.clerodri.service.Crypto;
import org.clerodri.CryptoEnum;

public class Ethereum extends CryptoMoney implements Crypto {


    public Ethereum(Integer quantity, Integer value) {
        super(quantity, value);
    }

    @Override
    public CryptoEnum getType() {
        return CryptoEnum.ETH;
    }

    @Override
    public void updateQuantity(Integer quantity) {
        Integer qty = this.getQuantity();
        this.setQuantity(qty - quantity);
    }
}
