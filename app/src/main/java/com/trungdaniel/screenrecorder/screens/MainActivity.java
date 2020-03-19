package com.trungdaniel.screenrecorder.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaCodecInfo;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ToggleButton;

import com.trungdaniel.screenrecorder.R;
import com.trungdaniel.screenrecorder.view.NamedSpinner;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
