package com.cisco.photogame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HighscoreActivity extends Activity {

    private int seconds;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.highscore);

        findViewById(R.id.go_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // go back
            }
        });


        Bundle extra = getIntent().getExtras();
        if (extra != null && extra.getInt("time", -1) > 0) {
            seconds = extra.getInt("time");
            String time = Util.getTimeString(seconds);
            ((TextView) findViewById(R.id.your_score)).setText(time);
        }
        else
            showList();

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
        findViewById(R.id.highscore_list).setVisibility(View.VISIBLE);

        final ListView listview = (ListView) findViewById(R.id.highscore_list);
        listview.setDivider(null);

        Highscore highscore = new Highscore(this);
        List<Highscore.HighscoreItem> list = highscore.getList();

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

    }

    private void saveScore(int time, String name) {
        Highscore highscore = new Highscore(this);
        highscore.saveHighscore(time, name);
        highscore.debugHighscore();
    }


    private class StableArrayAdapter extends ArrayAdapter<Highscore.HighscoreItem> {

        //HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        List<Highscore.HighscoreItem> scores = new ArrayList<Highscore.HighscoreItem>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<Highscore.HighscoreItem> objects) {
            super(context, textViewResourceId, objects);
            scores = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.highscore_row, parent, false);

            TextView pos = (TextView) rowView.findViewById(R.id.highscore_position);
            TextView name = (TextView) rowView.findViewById(R.id.highscore_name);
            TextView time = (TextView) rowView.findViewById(R.id.highscore_time);

            Highscore.HighscoreItem score = scores.get(position);
            if (score != null) {
                pos.setText(String.valueOf(position + 1));
                name.setText(score.name);
                time.setText(Util.getTimeString(score.time));
            }
            return rowView;
        }

        @Override
        public long getItemId(int position) {
            Log.i("photogame", "retrive highscore item for position " + position);
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }


}
