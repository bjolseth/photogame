package com.cisco.photogame;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Positions {

    public static List<Dude> defineHitAreas() {
        List<Dude> piecePositions = new ArrayList<Dude>();
        piecePositions.add(new Dude("Tyrion", R.drawable.tyrion, new Point(1184, 431)));
        piecePositions.add(new Dude("Daenerys", R.drawable.daenerys, new Point(858, 289)));
        piecePositions.add(new Dude("Ned", R.drawable.nedstark, new Point(546, 245)));
        piecePositions.add(new Dude("John", R.drawable.johnsnow, new Point(1468, 283)));
        return piecePositions;
    }

}
