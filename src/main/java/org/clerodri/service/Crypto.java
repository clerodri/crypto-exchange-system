package org.clerodri.service;

import org.clerodri.CryptoEnum;

public interface Crypto {

    CryptoEnum getType();
    void updateQuantity(Integer quantity);
}
