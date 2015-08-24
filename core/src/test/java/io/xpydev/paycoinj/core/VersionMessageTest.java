/*
 * Copyright 2012 Matt Corallo
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

package io.xpydev.paycoinj.core;

import static io.xpydev.paycoinj.core.Utils.HEX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.xpydev.paycoinj.params.UnitTestParams;

public class VersionMessageTest {
    @Test
    // Test that we can decode version messages which miss data which some old nodes may not include
    public void testDecode() throws Exception {
        NetworkParameters params = UnitTestParams.get();
        
        // VersionMessage ver = new VersionMessage(params, HEX.decode("71110100000000000000000048e5e95000000000000000000000000000000000000000000000ffff7f000001479d000000000000000000000000000000000000ffff7f000001479d0000000000000000122f50656572636f696e4a3a302e31322e322f0004000000"));
        VersionMessage ver = new VersionMessage(params, HEX.decode("7311010000000000000000000271b555000000000271b555000000000000000000000000000000000000ffff7f00000123260271b555000000000000000000000000000000000000ffff7f00000123260000000000000000112f506179636f696e4a3a302e31322e332f0004000000"));
        assertTrue(!ver.relayTxesBeforeFilter);
        assertEquals(ver.bestHeight, 1024);
        assertTrue(ver.subVer.equals("/PaycoinJ:0.12.3/"));

        // ver = new VersionMessage(params, HEX.decode("71110100000000000000000048e5e95000000000000000000000000000000000000000000000ffff7f000001479d000000000000000000000000000000000000ffff7f000001479d0000000000000000122f50656572636f696e4a3a302e31322e322f00040000"));
        ver = new VersionMessage(params, HEX.decode("7311010000000000000000001871b555000000001871b555000000000000000000000000000000000000ffff7f00000123261871b555000000000000000000000000000000000000ffff7f00000123260000000000000000112f506179636f696e4a3a302e31322e332f0004000001"));
        assertTrue(ver.relayTxesBeforeFilter);
        assertEquals(ver.bestHeight, 1024);
        assertTrue(ver.subVer.equals("/PaycoinJ:0.12.3/"));

        // ver = new VersionMessage(params, HEX.decode("71110100000000000000000048e5e95000000000000000000000000000000000000000000000ffff7f000001479d000000000000000000000000000000000000ffff7f000001479d0000000000000000122f50656572636f696e4a3a302e31322e322f"));
        ver = new VersionMessage(params, HEX.decode("7311010000000000000000003571b555000000003571b555000000000000000000000000000000000000ffff7f00000123263571b555000000000000000000000000000000000000ffff7f00000123260000000000000000112f506179636f696e4a3a302e31322e332f0000000001"));
        assertTrue(ver.relayTxesBeforeFilter);
        assertEquals(ver.bestHeight, 0);
        assertTrue(ver.subVer.equals("/PaycoinJ:0.12.3/"));

        // ver = new VersionMessage(params, HEX.decode("71110100000000000000000048e5e95000000000000000000000000000000000000000000000ffff7f000001479d000000000000000000000000000000000000ffff7f000001479d0000000000000000"));
        ver = new VersionMessage(params, HEX.decode("7311010000000000000000004b71b555000000004b71b555000000000000000000000000000000000000ffff7f00000123264b71b555000000000000000000000000000000000000ffff7f00000123260000000000000000000000000001"));
        assertTrue(ver.relayTxesBeforeFilter);
        assertEquals(ver.bestHeight, 0);
        assertTrue(ver.subVer.equals(""));
    }
}
