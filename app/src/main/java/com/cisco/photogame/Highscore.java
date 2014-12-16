package com.cisco.photogame;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Highscore {

    private Context context;

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
