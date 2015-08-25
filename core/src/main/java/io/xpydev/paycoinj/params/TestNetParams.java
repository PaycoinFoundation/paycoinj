/*
 * Copyright 2013 Google Inc.
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

package io.xpydev.paycoinj.params;

import io.xpydev.paycoinj.core.NetworkParameters;
import io.xpydev.paycoinj.core.Sha256Hash;
import io.xpydev.paycoinj.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the test network on which people trade goods and services.
 */
public class TestNetParams extends NetworkParameters {
    public TestNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x200fffffL);
        dumpedPrivateKeyHeader = 239;
        addressHeader = 111;
        p2shHeader = 196;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 9000;
        packetMagic= 0xbbbbbbbb;
        genesisBlock.setDifficultyTarget(0x1d0fffff);
        genesisBlock.setTime(1417219210L);
        genesisBlock.setNonce(18631020L);
        id = ID_TESTNET;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("0000000f6bb18c77c5b39a25fa03e4c90bffa5cc10d6d9758a1bed5adcee9404"), genesisHash);

        checkpoints.put(1000, new Sha256Hash("00000004a854d721d424e4a79a45592fe54652d85a17cab4a6293f8b53d3f2f6"));
        checkpoints.put(2000, new Sha256Hash("000000050bd1d79a24f6f977c1e7078fbf50f85f5818f80fbed23fb37eeb0467"));
        checkpoints.put(3000, new Sha256Hash("00000000b8af844ff895382751d1c3fb7dc9f9a2ec48513e0e5962035f9c84f0"));
        checkpoints.put(4000, new Sha256Hash("00000003b3084ef5b355e6cca4046be8f0da2aab2226d5bd18403dc82fb4c619"));
        checkpoints.put(5000, new Sha256Hash("00000002f59a0b0a6128988d984c5e4d7fdb315abda055d3c17b08762e4b9e09"));
        checkpoints.put(6000, new Sha256Hash("000000022039dfa5b955199ed1ef9f331fcfa21b0f8266bc7cf67603f8a286d7"));
        checkpoints.put(7000, new Sha256Hash("9a7e2d80dbb45307962a212ff8ee79912a734a3a101982038a0febc8ba43f76e"));
        checkpoints.put(8000, new Sha256Hash("000000011ced5a0876668477227f83236ad0bf60bb692d5ce0d16f3ba64e125b"));
        checkpoints.put(9000, new Sha256Hash("00000001ac70da3f3d80452dc02a3fd0f36e0027bf8f9dd172ea76477daf894a"));
        checkpoints.put(10000, new Sha256Hash("000000006968b552472636a68549964ee533c13bcafe0f6a2ab2fc7e77d8db2b"));
        checkpoints.put(11000, new Sha256Hash("03398160ba81e76e6fee42f150186ea211566d337e79673fca2aed97d58a937a"));
        checkpoints.put(12000, new Sha256Hash("00000000e4c26369c1dc29fc98e8e3ca12065b99bd8b1f39fca4311f826c12b7"));
        checkpoints.put(13000, new Sha256Hash("00000003a584f909af4b1df670c1c92a05eb7ec39e889aa04b0540fa475851bd"));
        checkpoints.put(14000, new Sha256Hash("00000004e6cb520a248df389573922ebf1b1980c577b00d6948b8954eb560a39"));
        checkpoints.put(15000, new Sha256Hash("00000003c28cafe5bdc1daf92b505f21f1412c442bdf6452d8344bd87e501868"));
        checkpoints.put(16000, new Sha256Hash("00000003d6e29377761841d1d3ef821423c597b0b0d0ad5b06f86a72a0fa537a"));
        checkpoints.put(17000, new Sha256Hash("a402091c1561495a50780fa7e0d52cd4b2c5bd267879755f2527cd2e9098e15c"));
        checkpoints.put(18000, new Sha256Hash("364ab497737797b96df599ee3de0c065474d438ef3c123b1c1c7a84f9042892c"));
        checkpoints.put(19000, new Sha256Hash("c0a1f5a1458747d797b24a30900528a4c253647980e8e65d829c815cf100ff1a"));
        checkpoints.put(20000, new Sha256Hash("d7d2e0d1faaff6dcbfc99393d9292b20e252f3e7afcb65add484f734be8ee9ca"));
        checkpoints.put(21000, new Sha256Hash("000000044cd6e4f2c2506e33506671f5af84c01e21770d2e0e806ae5445017fd"));

        dnsSeeds = new String[] {
                "tseed.paycoin.com"
        };
    }

    private static TestNetParams instance;
    public static synchronized TestNetParams get() {
        if (instance == null) {
            instance = new TestNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_TESTNET;
    }
}
