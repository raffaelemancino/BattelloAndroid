package com.raffaelemancino.helm;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    public BluetoothMessanger bluetoothMessanger = new BluetoothMessanger();
    private Switch runSwitch = null;
    private Button sendButton = null;
    private EditText message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.runSwitch = (Switch)findViewById(R.id.runSwitch);
        this.runSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked) {
                    bluetoothMessanger.open();
                }else{
                    bluetoothMessanger.close();
                }
            }
        });

        this.sendButton = (Button)findViewById(R.id.sendButton);
        this.sendButton.setOnClickListener(new CompoundButton.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bluetoothMessanger.sendMessage(message.getText().toString() + " --> " + new Date().toString());
            }
        });

        this.message = (EditText) findViewById(R.id.editText);
    }
}
