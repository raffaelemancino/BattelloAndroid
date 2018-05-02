package com.raffaelemancino.helm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

/**
 * Created by Raf on 05/04/2018.
 */
public class BluetoothMessanger
{
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;

    public BluetoothMessanger()
    {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.setupCommunication();
    }

    private void setupCommunication()
    {
        if(this.bluetoothAdapter.isEnabled())
        {
            Set<BluetoothDevice> bondedDevices = this.bluetoothAdapter.getBondedDevices();
            if(bondedDevices.size()>0)
            {
                BluetoothDevice device = (BluetoothDevice) bondedDevices.toArray()[0];
                ParcelUuid[] uuids = device.getUuids();
                this.bluetoothSocket = null;
                try
                {
                    this.bluetoothSocket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void open()
    {
        this.bluetoothAdapter.enable();
    }

    public void close()
    {
        this.bluetoothAdapter.disable();
    }

    public void sendMessage(String msg)
    {
        if(this.bluetoothSocket.isConnected())
        {
            try
            {
                msg += "\n";
                this.bluetoothSocket.getOutputStream().write(msg.getBytes());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }else{
            try
            {
                this.bluetoothSocket.connect();
                this.sendMessage(msg);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
