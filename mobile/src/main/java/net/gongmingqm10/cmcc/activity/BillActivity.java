package net.gongmingqm10.cmcc.activity;

import android.app.Activity;
import android.os.Bundle;

import net.gongmingqm10.cmcc.R;

public class BillActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getActionBar().setTitle("My bill");
    }
}
