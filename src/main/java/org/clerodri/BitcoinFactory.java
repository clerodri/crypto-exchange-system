package org.clerodri;

import org.clerodri.entity.Bitcoin;
import org.clerodri.service.Crypto;

public class BitcoinFactory extends CryptoFactory{

    @Override
    Crypto createCrypto() {
        return new Bitcoin(100,50000);
    }
}
