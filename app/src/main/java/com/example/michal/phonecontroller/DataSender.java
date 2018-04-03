package com.example.michal.phonecontroller;


/**
 * Created by Michal on 4/3/18.
 */


/**
 * @brief DataSender interface for send and receive messages from io controller
 */
public interface DataSender {

    /**
     * @brief Send data
     * @param inputFrame array of bytes to send
     */
    void send(byte[] inputFrame);
}
