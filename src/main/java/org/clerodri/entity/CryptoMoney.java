package org.clerodri.entity;

import org.clerodri.CryptoEnum;

public class CryptoMoney {
    private Integer quantity;
    private Integer value;

    public CryptoMoney( Integer quantity, Integer value) {
        this.quantity = quantity;
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
