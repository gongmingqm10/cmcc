package net.gongmingqm10.cmcc.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import net.gongmingqm10.cmcc.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.android.api.InstrumentedActivity;


public class MainActivity extends InstrumentedActivity {

    @InjectView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com/");
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
