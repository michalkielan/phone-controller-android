package com.example.michal.phonecontroller;

import java.util.Vector;

/**
 * Created by Michal Kielan on 4/3/18.
 */


/**
 * @brief Hard coded instructions
 */
class Instruction
{
    static public final int StartCode = 0x1234;
}


/**
 * @brief Commands to control io controllers
 */
enum Commands
{
    CommandFirst,
    Set,
    Get,
    ResponseOk,
    ResponseFailed,
    CommandLast
};
/**
 * @brief Frame message buffer structure
 */
public class FrameMessage {

    byte[] mStart;
    byte[] mDevice;
    byte[] mCommand;
    byte[] mData;
    byte mCrc;

    public FrameMessage()
    {
        mStart = new byte[2];
        mStart[0] = (byte)(Instruction.StartCode >> 8);
        mStart[1] = (byte)Instruction.StartCode;

        mDevice = new byte[2];
        mCommand = new byte[2];
        mData = new byte[2];
    }

    public Vector<Byte> toBuffer()
    {
        Vector<Byte> buff = new Vector();
        buff.add(mStart[0]);
        buff.add(mStart[1]);
        buff.add(mDevice[0]);
        buff.add(mDevice[1]);
        buff.add(mCommand[0]);
        buff.add(mCommand[1]);
        buff.add(mData[0]);
        buff.add(mData[1]);
        buff.add(mCrc);

        return buff;
    }
}
