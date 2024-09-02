package org.clerodri.factory;

import org.clerodri.crypto.Bitcoin;
import org.clerodri.service.Crypto;

public class BitcoinFactory extends CryptoFactory {

    @Override
    public Crypto createCrypto() {
        return new Bitcoin(100,50000);
    }
}
