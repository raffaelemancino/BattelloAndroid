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
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    public BluetoothMessanger bluetoothMessanger = new BluetoothMessanger();
    private Switch runSwitch = null;
    private Button sendButton = null;
    private EditText message = null;
    private SeekBar helmSeekBar = null;
    private SeekBar speedSeekBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.bluetoothMessanger.close();

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
                bluetoothMessanger.sendMessage(message.getText().toString());
            }
        });

        this.message = (EditText) findViewById(R.id.editText);

        this.helmSeekBar = (SeekBar) findViewById(R.id.helmSeekBar);
        this.helmSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int oldHelm = 50;
            int sensitivity = 3;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                /*int newHelm = seekBar.getProgress();

                if(this.oldHelm-this.sensitivity >= newHelm || this.oldHelm+this.sensitivity <= newHelm)
                {
                    bluetoothMessanger.sendMessage("helm: " + String.valueOf(newHelm));
                    oldHelm = newHelm;
                }*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                bluetoothMessanger.sendMessage("helm: " + String.valueOf(seekBar.getProgress()));
            }
        });

        this.speedSeekBar = (SeekBar) findViewById(R.id.speedSeekBar);
        this.speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                bluetoothMessanger.sendMessage("speed: " + String.valueOf(seekBar.getProgress()));
            }
        });
    }
}
