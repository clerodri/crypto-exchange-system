package org.clerodri.entity;

public class CryptoMoney {
    private double quantity;
    private Integer value;

    public CryptoMoney( Integer quantity, Integer value) {
        this.quantity = quantity;
        this.value = value;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
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
