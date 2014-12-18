package com.cisco.photogame;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Highscore {

    private Context context;

    public class HighscoreItem {
        public int pos;
        public String name;
        public int time;

        public HighscoreItem(int pos, String name, int time) {
            this.pos = pos;
            this.name = name;
            this.time = time;
        }
    }

    public Highscore(Context context) {
        this.context = context;
        SQLiteDatabase db = getDb();
        db.execSQL("CREATE TABLE IF NOT EXISTS Highscore(Time INT, Name VARCHAR);");
    }

    public SQLiteDatabase getDb() {
        return context.openOrCreateDatabase("photogame", context.MODE_PRIVATE, null);
    }

    public void saveHighscore(int time, String name) {
        String sql = String.format("INSERT INTO Highscore VALUES(%d, '%s');", time, name);
        getDb().execSQL(sql);
    }

    public void debug(String message, Object ... args) {
        Log.i("photogame", String.format(message, args));
    }

    public List<HighscoreItem> getList() {
        List<HighscoreItem> list = new ArrayList<HighscoreItem>();
        Cursor resultSet = getDb().rawQuery("Select * from Highscore ORDER BY Time ASC", null);
        resultSet.moveToFirst();

        int pos = 0;
        while (! resultSet.isAfterLast()) {
            int time = resultSet.getInt(0);
            String name = resultSet.getString(1);
            resultSet.moveToNext();
            list.add(new HighscoreItem(pos, name, time));
            pos++;
        }

        return list;
    }

    public void debugHighscore() {
        Cursor resultSet = getDb().rawQuery("Select * from Highscore ORDER BY Time ASC", null);
        debug("--- highscore, size: " + resultSet.getCount());
        resultSet.moveToFirst();
        while (! resultSet.isAfterLast()) {
            int time = resultSet.getInt(0);
            String name = resultSet.getString(1);
            debug("%d - %s", time, name);
            resultSet.moveToNext();
        }
    }
}
