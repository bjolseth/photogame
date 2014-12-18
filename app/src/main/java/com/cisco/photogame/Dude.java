package com.cisco.photogame;

import android.graphics.Point;

public class Dude {
    private String name;
    private int bitmapId;
    private int audioId;
    private Point position;

    public Dude(int bitmapId, int audioId, String name, Point position) {
        this.name = name;
        this.bitmapId = bitmapId;
        this.audioId = audioId;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getBitmapId() {
        return bitmapId;
    }

    public Point getPosition() {
        return position;
    }

    public int getAudioId() {
        return audioId;
    }
}
