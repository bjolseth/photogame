package com.cisco.photogame;

import android.graphics.Point;

public class Dude {
    private String name;
    private int bitmapId;
    private Point position;

    public Dude(String name, int bitmapId, Point position) {
        this.name = name;
        this.bitmapId = bitmapId;
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
}
