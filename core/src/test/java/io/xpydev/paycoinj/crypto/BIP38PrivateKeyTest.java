/*
 * Copyright 2014 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.xpydev.paycoinj.crypto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.xpydev.paycoinj.core.ECKey;
import io.xpydev.paycoinj.crypto.BIP38PrivateKey.BadPassphraseException;
import io.xpydev.paycoinj.params.MainNetParams;

public class BIP38PrivateKeyTest {

    private static final MainNetParams MAINNET = MainNetParams.get();
    
    @Test
    public void bip38testvector_test1() throws Exception {
        BIP38PrivateKey encryptedKey = new BIP38PrivateKey(MAINNET,
                "6PYWfAfKdtftVhUG4W4PJ79x5pQddNgSrpJ9vo14jbXPq6acnXCUkobWDG");
        ECKey key = encryptedKey.decrypt("TestingOneTwoThree");
        assertEquals("U9FsMwFrD4Kd5xpFwNWuyAwke8obAGHaSznnpmCUErBfgN6stFRz", key.getPrivateKeyEncoded(MAINNET)
                .toString());
    }

    @Test
    public void bip38testvector_test2() throws Exception {
        BIP38PrivateKey encryptedKey = new BIP38PrivateKey(MAINNET,
                "6PYTRc4AZVnHtt7oWvnNn34cEsuzr2DoLvbaAhCT8g4YnT2saPZGBXaXTx");
        ECKey key = encryptedKey.decrypt("Satoshi");
        assertEquals("UBvrNqwkBJbfWhC2iwVLoRcHyvrAZTuG5VaRxX8fj771a4Dq3EMg", key.getPrivateKeyEncoded(MAINNET)
                .toString());
    }

    @Test(expected = BadPassphraseException.class)
    public void badPassphrase() throws Exception {
        BIP38PrivateKey encryptedKey = new BIP38PrivateKey(MAINNET,
                "6PYTCnfcPwZuzERof9gngDcx4fGsqexv8xCyVcuadYtSHaWEytmnhW4d4g");
        encryptedKey.decrypt("BAD");
    }

}

