package com.cisco.photogame;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Positions {

    public static List<Dude> getDudes() {
        List<Dude> piecePositions = new ArrayList<Dude>();
        piecePositions.add(new Dude("Jorgen", R.drawable.dude_6, new Point( 1399, 856)));
        piecePositions.add(new Dude("Martin", R.drawable.dude_10, new Point(1428, 254)));
        piecePositions.add(new Dude("Sema, Morten", R.drawable.dude_3, new Point(465, 841)));
        piecePositions.add(new Dude("Hans Petter", R.drawable.dude_18, new Point(291, 245)));

        return piecePositions;
    }

}
