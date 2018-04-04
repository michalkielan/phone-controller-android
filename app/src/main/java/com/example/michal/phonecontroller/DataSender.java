package com.example.michal.phonecontroller;


/**
 * Created by Michal Kielan on 4/4/18.
 */

public class DataSender
{
    Crypt mCrypt;
    TransmitterReceiver mTransmitterReceiver;

    DataSender(Crypt crypt, TransmitterReceiver transmitterReceiver)
    {
        mCrypt = crypt;
        mTransmitterReceiver = transmitterReceiver;
    }

    public void send(FrameMessage frameMessage)
    {
        final byte[] buf = frameMessage.getBuffer();
        mTransmitterReceiver.send(mCrypt.encrypt(buf));
    }

}
