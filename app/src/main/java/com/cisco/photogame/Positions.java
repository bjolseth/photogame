package com.cisco.photogame;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Positions {

    public static List<Dude> getDudes() {
        List<Dude> piecePositions = new ArrayList<Dude>();
        piecePositions.add(new Dude("Jorgen", R.drawable.dude_6, new Point(1219, 830)));
        piecePositions.add(new Dude("Martin", R.drawable.dude_10, new Point(1232, 247)));
        piecePositions.add(new Dude("Sema, Morten", R.drawable.dude_3, new Point(315, 810)));
        piecePositions.add(new Dude("Hans Petter", R.drawable.dude_18, new Point(145, 240)));
        return piecePositions;
    }

}
