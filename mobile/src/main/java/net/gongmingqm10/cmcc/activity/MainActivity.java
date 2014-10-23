package net.gongmingqm10.cmcc.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import net.gongmingqm10.cmcc.R;
import net.gongmingqm10.cmcc.utils.Constant;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.android.api.InstrumentedActivity;


public class MainActivity extends InstrumentedActivity {

    @InjectView(R.id.pushBtn)
    Button pushBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNotification();
            }
        });
    }

    private void startNotification() {
        int notificationID = 100;
        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://10086.cn/sn/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent actionIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=中国移动"));
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        String[] replyChoices = new String[] {Constant.CMD_YES, Constant.CMD_NO, Constant.CMD_MAYBE};
        RemoteInput remoteInput = new RemoteInput.Builder(Constant.EXTRA_VOICE_REPLY)
                .setLabel("Choose to open")
                .setChoices(replyChoices)
                .build();
        Intent voiceReplyIntent = new Intent(this, RouterActivity.class);
        PendingIntent voicePendingIntent = PendingIntent.getActivity(this, 0, voiceReplyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.mobile_small,
                        "Choose action",
                        voicePendingIntent)
                .addRemoteInput(remoteInput)
                .build();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.mobile_small)
                .setContentTitle("Points exchange")
                .setContentText("Use points to exchange gift in CMCC shop.")
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.mobile_small, "Navigate to CMCC", actionPendingIntent)
                .extend(new NotificationCompat.WearableExtender().addAction(replyAction));
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationID, builder.build());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
