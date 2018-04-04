package com.example.michal.phonecontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity
{

    DataSender dataSender;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameMessage fm = new FrameMessage();

        dataSender = new DataSender(new CryptXor(Key64.key64), new Sms());
        dataSender.send(fm);

    }
}
