package org.clerodri.crypto;

import java.util.Objects;

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

    public Integer getCryptoPriceExchange() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoMoney that = (CryptoMoney) o;
        return Double.compare(quantity, that.quantity) == 0 && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, value);
    }
}
