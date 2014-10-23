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
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            Intent msgIntent = new Intent(Constant.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(Constant.KEY_MESSAGE, message);
//            if (!TextUtils.isEmpty(extras)) msgIntent.putExtra(Constant.KEY_EXTRAS, extras);
//            context.sendBroadcast(msgIntent);
        }
    }

    private void startNotification(String message, Context context) {
        int notificationID = 100;
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10086.cn/sn/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent actionIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=中国移动"));
        PendingIntent actionPendingIntent = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        String[] replyChoices = new String[] {Constant.CMD_YES, Constant.CMD_NO, Constant.CMD_MAYBE};
        RemoteInput remoteInput = new RemoteInput.Builder(Constant.EXTRA_VOICE_REPLY)
                .setLabel("Choose to open")
                .setChoices(replyChoices)
                .build();
        Intent voiceReplyIntent = new Intent(context, RouterActivity.class);
        PendingIntent voicePendingIntent = PendingIntent.getActivity(context, 0, voiceReplyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.mobile_small,
                        "Choose action",
                        voicePendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.mobile_small)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.mobile_small, "Navigate to CMCC", actionPendingIntent)
                .extend(new NotificationCompat.WearableExtender().addAction(replyAction));
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationID, builder.build());
    }

}
