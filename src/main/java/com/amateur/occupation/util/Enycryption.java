package com.amateur.occupation.util;



import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

@Slf4j
public class Enycryption {
    /**
     *
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) {
        try {
            byte[] result = (new BASE64Decoder()).decodeBuffer(key);
            return new String(result);
        } catch (IOException e) {
            log.error("decrypt error,data:" + key);
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}
