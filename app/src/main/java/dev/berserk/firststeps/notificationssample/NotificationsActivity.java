package dev.berserk.firststeps.notificationssample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dev.berserk.firststeps.R;
import dev.berserk.firststeps.util.KeysConstants;

public class NotificationsActivity extends AppCompatActivity {


    String message;
    private int value = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        if (getIntent()!=null){
            message = getIntent().getStringExtra(KeysConstants.MESSAGE);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //oreoNotification("Hola mundo");
                sendOreONotification(message);
            }else{
                showPushNotification(message);
            }
        }
    }

    private void showPushNotification(String message) {
        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(getApplicationContext(), FirstActivity.class);

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(FirstActivity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.BigTextStyle notificationStyle = new NotificationCompat.BigTextStyle();
        notificationStyle.setSummaryText("this is a test 😬");
        notificationStyle.bigText("☠️ "+message+".");
        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"TAG");

        // Define the notification settings.
        builder.setSmallIcon(R.drawable.ic_pokeball)
                // In a real app, you may want to use a library like Volley
                // to decode the Bitmap.
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.ic_pokeball))
                .setColor(Color.BLUE)
                .setStyle(notificationStyle)
                .setContentTitle("PUM ☠")
                .setContentText("Details️ "+message)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setContentIntent(notificationPendingIntent);

        // Dismiss notification once the user touches it.
        builder.setAutoCancel(false);

        // Get an instance of the Notification manager
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Issue the notification
        mNotificationManager.notify(value, builder.build());
        value++;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void sendOreONotification(String message){
        Intent resultIntent = new Intent(this,FirstActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.BigTextStyle notificationStyle = new NotificationCompat.BigTextStyle();
        notificationStyle.setSummaryText("this is summary text 😬");
        notificationStyle.bigText("☠ "+message);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(),String.valueOf(value));
        notificationBuilder.setSmallIcon(R.drawable.ic_game_controller);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_pizza_slice));
        notificationBuilder.setContentTitle(getString(R.string.app_name)+ " 😬")
                //.setContentText("🌮 "+notificationDetails)
                .setAutoCancel(false)
                .setStyle(notificationStyle)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel notificationChannel = new NotificationChannel(String.valueOf(value), "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        long []vibration = {100, 200, 300, 400, 500, 400, 300, 200, 400};
        notificationChannel.setVibrationPattern(vibration);
        notificationBuilder.setChannelId(String.valueOf(value));
        assert mNotificationManager != null;
        mNotificationManager.createNotificationChannel(notificationChannel);

        mNotificationManager.notify(value /* Request Code */, notificationBuilder.build());
        value ++;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
        finish();
    }
}