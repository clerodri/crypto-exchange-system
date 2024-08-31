package org.clerodri;

import org.clerodri.entity.Ethereum;
import org.clerodri.service.Crypto;

public class EthereumFactory extends CryptoFactory{
    @Override
    Crypto createCrypto() {
        return new Ethereum(500,3000);
    }
}
