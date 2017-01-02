package com.example.leolopez.helloworld.models;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;

/**
 * Created by leolopez94 on 30/12/16.
 */
public class App {

    private long tx = 0;
    private long rx = 0;

    private long current_tx = 0;
    private long current_rx = 0;

    private long mobil_tx = 0;
    private long mobil_rx = 0;

    private String name;
    private String received;


    private ApplicationInfo app;

    public App(ApplicationInfo app) {
        this.app = app;
        update();
    }

    public void update() {
        long delta_tx = TrafficStats.getUidTxBytes(app.uid) - tx;
        long delta_rx = TrafficStats.getUidRxBytes(app.uid) - rx;

        tx = TrafficStats.getUidTxBytes(app.uid);
        rx = TrafficStats.getUidRxBytes(app.uid);

        current_tx = current_tx + delta_tx;
        current_rx = current_rx + delta_rx;

        mobil_tx = mobil_tx + delta_tx;
        mobil_rx = mobil_rx + delta_rx;
    }

    public String getName(PackageManager packageManager) {
        return packageManager.getApplicationLabel(app).toString();
    }

    public int getReceived() {
        //return Math.round((tx + rx) / 1024);
        return Math.round(tx + rx);
    }
}
