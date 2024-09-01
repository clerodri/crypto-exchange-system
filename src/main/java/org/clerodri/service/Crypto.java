package org.clerodri.service;

public interface Crypto {
//    Integer getValue();
//    CryptoEnum getType();
//    Integer  getQuantity();
    void updateQuantity(double quantity);

    String showDetails();

    Integer getValue();
}
