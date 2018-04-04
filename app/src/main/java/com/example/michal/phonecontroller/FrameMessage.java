package com.example.michal.phonecontroller;

import android.view.animation.Interpolator;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
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

    public void setCommand(int command)
    {
        final int lowByte = 3, highByte = 2;
        final byte[] buf = ByteBuffer.allocate(4).putInt(command).array();
        mCommand[0] = buf[highByte];
        mCommand[1] = buf[lowByte];
    }

    public void setDevice(int device)
    {
        final int lowByte = 3, highByte = 2;
        final byte[] buf = ByteBuffer.allocate(4).putInt(device).array();
        mDevice[0] = buf[highByte];
        mDevice[1] = buf[lowByte];
    }

    public void setData(int data)
    {
        final int lowByte = 3, highByte = 2;
        final byte[] buf = ByteBuffer.allocate(4).putInt(data).array();
        mData[0] = buf[highByte];
        mData[1] = buf[lowByte];
    }

    private byte getCrc(Vector<Byte> buff)
    {
        byte crc = 0;
        for(int i=0; i<buff.size(); i++)
        {
            crc ^= buff.get(i);
        }
        return crc;
    }

    public byte[] getBuffer()
    {
        Vector<Byte> v = new Vector();
        v.add(mStart[0]);
        v.add(mStart[1]);
        v.add(mDevice[0]);
        v.add(mDevice[1]);
        v.add(mCommand[0]);
        v.add(mCommand[1]);
        v.add(mData[0]);
        v.add(mData[1]);

        mCrc = getCrc(v);
        v.add(mCrc);

        byte[] bytes = new byte[v.size()];

        for (int i = 0; i < v.size(); i++)
        {
            bytes[i] = v.get(i);
        }

        return bytes;
    }
}
