package com.example.leolopez.helloworld.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.leolopez.helloworld.MainActivity;
import com.example.leolopez.helloworld.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leolopez94 on 22/12/16.
 *
 */

public class ExampleService extends Service {

    private Timer mTimer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mTimer = new Timer();
        this.mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ejecutarTarea();
            }
        }, 0, 1000 * 60);
    }

    private void ejecutarTarea(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                NotifyManager notify = new NotifyManager();
                notify.playNotification(getApplicationContext(), MainActivity.class,
                        "Tienes una notificacion", "Notificiacion", R.mipmap.ic_launcher);
            }
        });
        t.start();
    }
}
