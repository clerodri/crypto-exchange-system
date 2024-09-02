package org.clerodri.factory;

import org.clerodri.crypto.Ethereum;
import org.clerodri.service.Crypto;

public class EthereumFactory extends CryptoFactory {
    @Override
    public Crypto createCrypto() {
        return new Ethereum(500,3000);
    }
}
