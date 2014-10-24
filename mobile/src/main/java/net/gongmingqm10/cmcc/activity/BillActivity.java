package net.gongmingqm10.cmcc.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import net.gongmingqm10.cmcc.R;

public class BillActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getSupportActionBar().setTitle("My bill");
    }
}
