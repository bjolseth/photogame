package com.cisco.photogame;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Positions {

    public static List<Dude> defineHitAreas() {
        List<Dude> piecePositions = new ArrayList<Dude>();
        piecePositions.add(new Dude("Tony", R.drawable.dude_1, new Point(1184, 431)));
        piecePositions.add(new Dude("Lasse", R.drawable.dude_2, new Point(858, 289)));
        piecePositions.add(new Dude("Sema, Morten", R.drawable.dude_3, new Point(546, 245)));
        piecePositions.add(new Dude("Jens", R.drawable.dude_4, new Point(1468, 283)));
        return piecePositions;
    }

}
