package net.gongmingqm10.cmcc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.widget.TextView;

import net.gongmingqm10.cmcc.R;
import net.gongmingqm10.cmcc.utils.Constant;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RouterActivity extends Activity {

    @InjectView(R.id.cmdText)
    TextView cmdText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router);
        ButterKnife.inject(this);

        Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
        if (remoteInput != null) {
            CharSequence cmd = remoteInput.getCharSequence(Constant.EXTRA_VOICE_REPLY);
            cmdText.setText(cmd);
        }
    }
}
