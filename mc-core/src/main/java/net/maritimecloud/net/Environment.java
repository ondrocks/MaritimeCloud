/* Copyright (c) 2011 Danish Maritime Authority.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.maritimecloud.net;

/**
 *
 * @author Kasper Nielsen
 */
public abstract class Environment {
    // Client Trust

    /** The default sandbox environment. */
    public static final Environment SANDBOX = new Environment() {
        @Override
        public String mmsServerURL() {
            // TODO replace with ws://mms.sandbox03.maritimecloud.net"
            return "ws://mms03.maritimecloud.net";
        }
    };

    /** The default sandbox environment. */
    public static final Environment SANDBOX_NO_ENCRYPTION = new Environment() {
        @Override
        public String mmsServerURL() {
            // TODO replace with ws://mms.sandbox03.maritimecloud.net"
            return "ws://mms03.maritimecloud.net";
        }
    };

    /** The default test environment. */
    public static final Environment TEST = new Environment() {
        public boolean isEncrypted() {
            return true;
        }

        @Override
        public String mmsServerURL() {
            return "wss://mms.test03.maritimecloud.net";
        }
    };

    // package private for now
    Environment() {}

    /**
     * Returns whether or not the communication with the MMS server is encrypted
     *
     * @return whether or not the communication with the MMS server is encrypted
     */
    public boolean isEncrypted() {
        return false;
    }

    public abstract String mmsServerURL();

    public boolean useBinaryAsDefault() {
        return false;
    }
}