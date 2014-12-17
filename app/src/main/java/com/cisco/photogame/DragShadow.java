package com.cisco.photogame;


import android.graphics.Canvas;

import android.graphics.Point;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class DragShadow extends View.DragShadowBuilder {

    private Drawable shadow;
    private float scaleFactor;

    public DragShadow(View v, int drawableId, float scaleFactor) {
        super(v);
        this.scaleFactor = scaleFactor;
        shadow = v.getResources().getDrawable(drawableId);
    }

    @Override
    public void onProvideShadowMetrics (Point size, Point touch) {
        int width = (int) (shadow.getIntrinsicWidth() * scaleFactor);
        int height = (int) (shadow.getIntrinsicHeight() * scaleFactor);

        shadow.setBounds(0, 0, width, height);

        size.set(width, height);
        touch.set(width/2, height/2);
    }

    @Override
    public void onDrawShadow(Canvas canvas) {

        // Draws the ColorDrawable in the Canvas passed in from the system.
        shadow.draw(canvas);
    }
}