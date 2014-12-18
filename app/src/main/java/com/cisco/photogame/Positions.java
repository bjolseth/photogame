package com.cisco.photogame;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Positions {

    public static List<Dude> getDudes() {
        List<Dude> piecePositions = new ArrayList<Dude>();

        int defaultSound = R.raw.lasthore;

        piecePositions.add(new Dude(R.drawable.dude_1, defaultSound, "Tony", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_2, R.raw.lasthore, "Lasse", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_3, R.raw.mweeljoh, "Samaneh, Morten", new Point(465, 841)));
        piecePositions.add(new Dude(R.drawable.dude_4, R.raw.jensand, "Jens", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_5, R.raw.eivhaarr, "Eivind H", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_6, R.raw.johauge, "Jørgen T", new Point( 1399, 856)));
        piecePositions.add(new Dude(R.drawable.dude_7, R.raw.geibjune, "Geir", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_8, R.raw.stistryn, "Stian", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_9, R.raw.jensand, "Jens Petter", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_10, R.raw.mbaisgar, "Martin", new Point(1428, 254)));
        piecePositions.add(new Dude(R.drawable.dude_11, R.raw.hbauge, "Håvard", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_12, R.raw.acideron, "Arthur", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_13, R.raw.krthorse, "Kristian", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_14, R.raw.jtrosby, "Jørgen T", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_15, R.raw.vhammer, "Vegard", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_16, R.raw.gpouzera, "Gaël", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_17, R.raw.peknudse, "Petter", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_18, R.raw.heide, "Hans Petter", new Point(291, 245)));
        piecePositions.add(new Dude(R.drawable.dude_19, defaultSound, "Ainhoa", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_20, R.raw.tbjolset, "Tore", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_21, R.raw.msvennin, "Marve", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_22, defaultSound, "Indrajit", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_23, R.raw.kpeters, "Kalle", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_24, R.raw.kfayelun, "Kristi", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_25, R.raw.emorchbr, "Eivind M B", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_26, R.raw.jogonza4, "Luis", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_27, defaultSound, "Norma", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_28, defaultSound, "Kirti", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_29, defaultSound, "John", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_30, defaultSound, "Lene", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_31, R.raw.adwarpal, "Achyut", new Point(0, 0)));
        piecePositions.add(new Dude(R.drawable.dude_32, defaultSound, "Bharat", new Point(0, 0)));
        return piecePositions;
    }

}
