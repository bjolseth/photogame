package com.cisco.photogame;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class HighscoreActivity extends Activity {

    private int seconds;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.highscore);

        Bundle extra = getIntent().getExtras();
        if (extra != null && extra.getInt("time", -1) > 0) {
            seconds = extra.getInt("time");
            String time = Util.getTimeString(seconds);
            ((TextView) findViewById(R.id.your_score)).setText(time);
        }
        else
            return;

        final EditText name = ((EditText) findViewById(R.id.your_name));
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    saveScore(seconds, name.getText().toString());
                    showList();
                }
                return false;
            }
        });

    }

    private void showList() {
        findViewById(R.id.your_name).setVisibility(View.GONE);
        // todo show the list
    }

    private void saveScore(int time, String name) {
        Highscore highscore = new Highscore(this);
        highscore.saveHighscore(time, name);

        highscore.debugHighscore();
    }


}
