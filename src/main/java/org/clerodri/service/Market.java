package org.clerodri.service;

import org.clerodri.crypto.CryptoType;

public interface Market {
    void depositFitMoney(Integer amount);
    void buyCryptoFromExchange(int totalCost, double quantity, CryptoType type);
    boolean validateCryptoQuantity(double quantity, CryptoType type);
    boolean validateBalance(int amount);
}
