package org.clerodri.crypto;

import org.clerodri.service.Crypto;

import java.util.Objects;

public class CryptoMoney implements Crypto, Comparable<CryptoMoney> {
    private double quantity;
    private Integer value;

    public CryptoMoney( double quantity, Integer value) {
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

    @Override
    public void updateCryptoQuantity(double quantity) {
        double qty = this.getQuantity();
        this.setQuantity(qty - quantity);
    }

    @Override
    public String displayCryptoDetails() {
        return  "[ Quantity: "+ this.getQuantity() + "\t Price: $" + this.getCryptoPriceExchange()+" ]";
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

    @Override
    public int compareTo(CryptoMoney o) {
        int quantityEquals = Double.compare(this.quantity, o.quantity);
        if(quantityEquals == 0) {
            if (this.value <= o.value) {
                return 1;
            } else {
                return -1;
            }
        }
        return  quantityEquals;
    }

    public int compareTypeSeller(CryptoMoney o) {
        int quantityEquals = Double.compare(this.quantity, o.quantity);
        if (quantityEquals == 0) {
            if (this.value >= o.value) {
                return 1;
            } else {
                return -1;
            }
        }
        return -1;
    }

}
