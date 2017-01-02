package com.example.leolopez.helloworld.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.leolopez.helloworld.R;

/**
 * Created by leolopez94 on 22/12/16.
 *
 */

public class NotifyManager {
    public void playNotification(Context context, Class<?> cls, String
                                 textNotification, String titleNotification,
                                 int drawable){
        /* NOTOFICATION VARS */

        NotificationManager mNotificationManager;
        int SIMPLE_NOTIFICATION_ID = 1;
        Notification notifyDetails;

        /*Notification INICIO*/

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(context, cls);
        PendingIntent intent = PendingIntent.getActivity(context, 0, notifyIntent,
                PendingIntent.FLAG_ONE_SHOT);
        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        long[] vibrate = {100,100,200,300};

        notifyDetails = new Notification.Builder(context)
                                                .setAutoCancel(false)
                                                .setSmallIcon(drawable)
                                                .setContentTitle(titleNotification)
                                                .setContentText(textNotification)
                                                .setTicker(textNotification)
                                                .setVibrate(vibrate)
                                                .setDefaults(Notification.DEFAULT_ALL)
                                                .build();

        /*NOTIFICATION FIN*/

        mNotificationManager.notify(SIMPLE_NOTIFICATION_ID, notifyDetails);
    }
}
