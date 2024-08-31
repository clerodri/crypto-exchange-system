package org.clerodri.entity;

public class Crypto {
    private String name;
    private Integer quantity;
    private Integer value;

    public Crypto(String name, Integer quantity, Integer value) {
        this.name = name;
        this.quantity = quantity;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
