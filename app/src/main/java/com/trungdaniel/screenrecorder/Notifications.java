package com.trungdaniel.screenrecorder;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.text.format.DateUtils;
import static com.trungdaniel.screenrecorder.MainFragment.ACTION_STOP;

class Notifications extends ContextWrapper {
    private static final int id = 0x1fff;
    private static final String CHANNEL_ID = "Recording";
    private static final String CHANNEL_NAME = "Screen Recorder Notifications";
    private long mLastFiredTime = 0;
    private NotificationManager mManager;
    private Notification.Action mStopAction;
    private Notification.Builder mBuilder;

    Notifications(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
    }

    public void recording(long timeMs) {
        if (SystemClock.elapsedRealtime() - mLastFiredTime < 1000) {
            return;
        }
        Notification notification = getBuilder()
                .setContentText(getString(R.string.length_video) + " " + DateUtils.formatElapsedTime(timeMs / 1000))
                .build();
        getNotificationManager().notify(id, notification);
        mLastFiredTime = SystemClock.elapsedRealtime();
    }

    private Notification.Builder getBuilder() {
        if (mBuilder == null) {
            Notification.Builder builder = new Notification.Builder(this)
                    .setContentTitle(getString(R.string.gravando))
                    .setOngoing(true)
                    .setLocalOnly(true)
                    .setOnlyAlertOnce(true)
                    .addAction(stopAction())
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_btn_record);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setChannelId(CHANNEL_ID)
                        .setUsesChronometer(true);
            }
            mBuilder = builder;
        }
        return mBuilder;
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel channel =
                new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
        channel.setShowBadge(false);
        getNotificationManager().createNotificationChannel(channel);
    }

    private Notification.Action stopAction() {
        if (mStopAction == null) {
            Intent intent = new Intent(ACTION_STOP).setPackage(getPackageName());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1,
                    intent, PendingIntent.FLAG_ONE_SHOT);
            mStopAction = new Notification.Action(android.R.drawable.ic_media_pause, getString(R.string.stop), pendingIntent);
        }
        return mStopAction;
    }

    void clear() {
        mLastFiredTime = 0;
        mBuilder = null;
        mStopAction = null;
        getNotificationManager().cancelAll();
    }

    NotificationManager getNotificationManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
}
