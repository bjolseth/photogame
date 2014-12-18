package com.cisco.photogame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class HighscoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.highscore);

        Bundle extra = getIntent().getExtras();
        if (extra != null && extra.getInt("time", -1) > 0) {
            String time = Util.getTimeString(extra.getInt("time"));
            ((TextView) findViewById(R.id.your_score)).setText(time);
        }
    }


}
