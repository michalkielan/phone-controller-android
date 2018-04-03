package com.example.michal.phonecontroller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;


/**
 * Created by Michal Kielan on 4/3/18.
 */


/**
 * @brief Object for sms send/received
 */
public class Sms extends BroadcastReceiver implements DataSender
{
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    SmsManager mSmsManager;
    boolean mReceiverEnabled;

    /**
     * @brief Constructor
     */
    Sms()
    {
        mReceiverEnabled = false;
        mSmsManager = mSmsManager.getDefault();
    }

    @Override
    public void send(byte[] inputFrame)
    {
        mReceiverEnabled = true;
        mSmsManager.sendTextMessage(ControllerNumber.number, null, inputFrame.toString(), null, null);
    }

    /**
     * @brief pare sms message
     *
     * @param inputFrame
     */
    private void parse(byte[] inputFrame)
    {
        Crypt crypt = new CryptXor(Key64.key64);

        byte[] data = crypt.encrypt(inputFrame);
        mReceiverEnabled = false;
    }

    /**
     * @brief Receive sms
     *
     * @param context
     * @param intent
     */
    private void receiveSmsEvent(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();

        if (bundle != null)
        {
            // get sms objects
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus.length == 0)
            {
                return;
            }
            // large message might be broken into many
            SmsMessage[] messages = new SmsMessage[pdus.length];
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < pdus.length; i++)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    final String format = bundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                }

                else
                {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                sb.append(messages[i].getMessageBody());
            }
            String sender = messages[0].getOriginatingAddress();
            String message = sb.toString();

            parse(message.getBytes());

        }
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(mReceiverEnabled == true)
        {
            if (intent.getAction().equals(SMS_RECEIVED))
            {
                receiveSmsEvent(context, intent);
            }
        }
    }
}
