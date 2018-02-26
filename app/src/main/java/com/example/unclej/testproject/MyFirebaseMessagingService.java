package com.example.unclej.testproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by uncleJ on 2018-02-15.
 */
/*
MyFirebaseMessagingService
        onMessageReceived에서 이벤트가 걸린다.
        remoteMessage에 값이 담겨있게 되는데
        MyFirebaseMessagingService 클래스는 앱이 실행중일때 푸시 알람을 보내면 발생하는
        이벤트 이다.
        ex)카카오톡 실행중인데 다른 대화상자 친구가 말걸면 알람뜨는 행위
        */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyMS";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG,"onMessageReceived 호출");
        String from = remoteMessage.getFrom();
        Map<String, String> resData = remoteMessage.getData();
        String command = resData.get("command");
        String type = resData.get("type");
        String data = resData.get("data");

        Log.v(TAG, "from : " + from + ", command : " + command + ", type : " + type + ", data : " + data);

        sendToNotification(getApplicationContext(), from, command, type, data);
        //sendToActivity(getApplicationContext(), from, command, type, data);


    }

    private void sendToNotification(Context context, String from, String command, String type, String data) {


        Resources res = getResources();

        Intent notificationIntent = new Intent(this, TestActivity2.class);
        notificationIntent.putExtra("from", from);
        notificationIntent.putExtra("command", command);
        notificationIntent.putExtra("type", type);
        notificationIntent.putExtra("data", data);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle(data)
                .setContentText(data)
                .setTicker("상태바 한줄 메시지")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_MESSAGE)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC);
        }

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1234, builder.build());
    }



    private void sendToActivity(Context context, String from, String command, String type, String data) {
        Intent intent = new Intent(context, MenuEditActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("command", command);
        intent.putExtra("type", type);
        intent.putExtra("data", data);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);

        context.startActivity(intent);
    }
}
