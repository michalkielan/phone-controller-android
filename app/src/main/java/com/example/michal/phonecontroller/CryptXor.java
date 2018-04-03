package com.example.michal.phonecontroller;

/**
 * Created by Michal Kielan on 4/3/18.
 */


/**
 * @brief Crypt xor algorithm
 */
public class CryptXor implements Crypt {

    String mKey;

    CryptXor(String key)
    {
        mKey = key;
    }

    /**
     * @brief Crypt array of data using xor method, the key is global
     *        buffer of random characters. This function is used for
     *        crypt and encrypt.
     *
     * @param inputFrame decrypted/encrypted output buffer of data
     *
     * @return encrypted/decrypted output buffer of data
     */
    private byte[] crypt(byte[] inputFrame)
    {
        byte[] cryptFrame = new byte[inputFrame.length];

        for (int i = 0; i < inputFrame.length; i++)
        {
            cryptFrame[i] = (byte)(inputFrame[i] ^ mKey.getBytes()[i]);
        }

        return cryptFrame;
    }

    @Override
    public byte[] encrypt(byte[] input)
    {
        return crypt(input);
    }

    @Override
    public byte[] decrypt(byte[] input)
    {
        return crypt(input);
    }

}
