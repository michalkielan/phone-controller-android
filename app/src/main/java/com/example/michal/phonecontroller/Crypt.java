package com.example.michal.phonecontroller;

import java.util.Vector;

/**
 * Created by Michal Kielan on 4/3/18.
 */

/**
 * @brief Crypt interface
 */
public interface Crypt {

    /**
     * @brief  Encrypt vector of byte
     *
     * @param input frame
     * @return encrypted frame
     */
    byte[] encrypt(byte[] input);

    /**
     * @brief Decrypt vector of byte
     *
     * @param input frame
     * @return decrypted frame
     */
    byte[] decrypt(byte[] input);
}
