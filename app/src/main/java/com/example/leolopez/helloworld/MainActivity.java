package com.example.leolopez.helloworld;

import android.app.ActivityManager;
import android.content.pm.ApplicationInfo;
import android.net.TrafficStats;
import android.os.Handler;
import android.support.v4.net.TrafficStatsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.leolopez.helloworld.adapters.AppAdapter;
import com.example.leolopez.helloworld.models.App;
import com.example.leolopez.helloworld.services.ExampleService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppAdapter adapter;
    private long dataUsageTotalLast = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent service = new Intent(this, ExampleService.class);
        startService(service);*/

        if(TrafficStats.getTotalRxBytes() != TrafficStats.UNSUPPORTED &&
                TrafficStats.getTotalTxBytes() != TrafficStats.UNSUPPORTED){
            handler.postDelayed(runnable, 0);
            Log.d("MainActivity", "Entro");

            initAdapter();
            recyclerView = (RecyclerView) findViewById(R.id.list_apps);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } else {
            Log.e("MainActivity", "No soporto");
        }

        /*for(ActivityManager.RunningAppProcessInfo runningApp: runningApps){
            int uid = ((ActivityManager.RunningAppProcessInfo))
        }*/
    }

    private void initAdapter() {

        List<App> apps = new ArrayList<>();

        for(ApplicationInfo appInfo: getApplicationContext().getPackageManager().getInstalledApplications(0)){
            long _tx = TrafficStats.getUidTxBytes(appInfo.uid);
            long _rx = TrafficStats.getUidRxBytes(appInfo.uid);

            if((_tx + _rx) > 0) {
                App app = new App(appInfo);
                apps.add(app);
            }
        }

        adapter = new AppAdapter(apps, this);
    }

    public Handler handler = new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            long mobile = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
            long total = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
            if(dataUsageTotalLast != total){
                dataUsageTotalLast = total;
                updateAdapter();
            }
            handler.postDelayed(runnable, 5000);
        }
    };

    private void updateAdapter() {
        for(int i = 0, l = adapter.getItemCount(); i < l; i++){
            App app = adapter.getApps().get(i);
            app.update();
        }
        adapter.notifyDataSetChanged();
    }
}
