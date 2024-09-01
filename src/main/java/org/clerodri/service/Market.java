package org.clerodri.service;

import org.clerodri.entity.CryptoType;

public interface Market {
    void deposit(Integer amount);
    void buyCrypto(int totalCost, double quantity, CryptoType type);
}
