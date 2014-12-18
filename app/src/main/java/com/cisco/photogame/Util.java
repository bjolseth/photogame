package com.cisco.photogame;


public class Util {

    public static String getTimeString(int time) {
        int mins = time / 60;
        time = time - mins * 60;
        int secs = time;
        return String.format("%02d:%02d", mins, secs);
    }
}
