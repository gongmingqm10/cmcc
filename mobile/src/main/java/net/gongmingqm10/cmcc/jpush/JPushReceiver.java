package net.gongmingqm10.cmcc.jpush;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.text.TextUtils;

import net.gongmingqm10.cmcc.R;
import net.gongmingqm10.cmcc.activity.BillActivity;
import net.gongmingqm10.cmcc.activity.MainActivity;
import net.gongmingqm10.cmcc.activity.RouterActivity;
import net.gongmingqm10.cmcc.utils.Constant;

import cn.jpush.android.api.JPushInterface;

public class JPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            startNotification(message, context);
        }
    }

    private void startNotification(String message, Context context) {
        int notificationID = 100;
//        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10086.cn/sn/"));
        Intent notificationIntent = new Intent(context, BillActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent actionIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=西安移动营业厅"));
        PendingIntent actionPendingIntent = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.mobile_small)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.mobile_small, "Navigate to CMCC", actionPendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationID, builder.build());
    }

}
