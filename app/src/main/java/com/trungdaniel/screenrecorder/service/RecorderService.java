package com.trungdaniel.screenrecorder.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.trungdaniel.screenrecorder.MainFragment;
import com.trungdaniel.screenrecorder.R;

public class RecorderService extends Service {
    private WindowManager windowManager;
    private MyGroupView myViewIcon;
    private WindowManager.LayoutParams mIconParams;
    private ImageView imgIconRecorder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        createIconView();
        showIcon();

    }

    private void showIcon() {
        windowManager.addView(myViewIcon, mIconParams);
    }

    private void createIconView() {
        myViewIcon = new MyGroupView(this);
        View view = View.inflate(this, R.layout.item_recorder_layout, myViewIcon);
        //anh xa fid view id
        imgIconRecorder = view.findViewById(R.id.img_icon_recorder);
        imgIconRecorder.setBackgroundResource(R.drawable.ic_btn_record);
        imgIconRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        //
        mIconParams = new WindowManager.LayoutParams();
        mIconParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mIconParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mIconParams.gravity = Gravity.CENTER;
        mIconParams.format = PixelFormat.TRANSLUCENT;
        mIconParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mIconParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mIconParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mIconParams.flags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        windowManager.removeView(myViewIcon);
    }
}
