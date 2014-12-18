package com.cisco.photogame;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Positions {

    public static List<Dude> getDudes() {
        List<Dude> piecePositions = new ArrayList<Dude>();

        int defaultSound = R.raw.lasthore;

        piecePositions.add(new Dude(R.drawable.dude_1, R.raw.tjosenda, "Tony", new Point(89, 536)));
        piecePositions.add(new Dude(R.drawable.dude_2, R.raw.lasthore, "Lasse", new Point(1191, 659)));
        piecePositions.add(new Dude(R.drawable.dude_4, R.raw.jensand, "Jens", new Point(904, 336)));
        piecePositions.add(new Dude(R.drawable.dude_29, defaultSound, "John", new Point(929, 558)));
        piecePositions.add(new Dude(R.drawable.dude_25, R.raw.emorchbr, "Eivind M B", new Point(1401, 331)));

        piecePositions.add(new Dude(R.drawable.dude_3, R.raw.mweeljoh, "Samaneh, Morten", new Point(373, 814)));
        piecePositions.add(new Dude(R.drawable.dude_5, R.raw.eivhaarr, "Eivind H", new Point(775, 945)));
        piecePositions.add(new Dude(R.drawable.dude_6, R.raw.johauge, "Jørgen H", new Point(1428, 833)));
        piecePositions.add(new Dude(R.drawable.dude_7, R.raw.geibjune, "Geir", new Point(637, 855)));
        piecePositions.add(new Dude(R.drawable.dude_8, R.raw.stistryn, "Stian", new Point(1515, 473)));
        piecePositions.add(new Dude(R.drawable.dude_9, R.raw.jensand, "Jens Petter", new Point(966, 826)));
        piecePositions.add(new Dude(R.drawable.dude_10, R.raw.mbaisgar, "Martin", new Point(1465, 148)));
        piecePositions.add(new Dude(R.drawable.dude_11, R.raw.hbauge, "Håvard", new Point(1276, 124)));
        piecePositions.add(new Dude(R.drawable.dude_12, R.raw.acideron, "Arthur", new Point(1102, 117)));
        piecePositions.add(new Dude(R.drawable.dude_13, R.raw.krthorse, "Kristian", new Point(972, 124)));
        piecePositions.add(new Dude(R.drawable.dude_14, R.raw.jtrosby, "Jørgen T", new Point(826, 87)));
        piecePositions.add(new Dude(R.drawable.dude_15, R.raw.vhammer, "Vegard", new Point(623, 97)));
        piecePositions.add(new Dude(R.drawable.dude_16, R.raw.gpouzera, "Gaël", new Point(469, 140)));
        piecePositions.add(new Dude(R.drawable.dude_17, R.raw.peknudse, "Petter", new Point(368, 140)));
        piecePositions.add(new Dude(R.drawable.dude_18, R.raw.heide, "Hans Petter", new Point(174, 138)));
        piecePositions.add(new Dude(R.drawable.dude_19, R.raw.aostolaz, "Ainhoa", new Point(142, 340)));
        piecePositions.add(new Dude(R.drawable.dude_20, R.raw.tbjolset, "Tore", new Point(327, 315)));
        piecePositions.add(new Dude(R.drawable.dude_21, R.raw.msvennin, "Marve", new Point(534, 327)));
        piecePositions.add(new Dude(R.drawable.dude_22, defaultSound, "Indrajit", new Point(701, 329)));
        piecePositions.add(new Dude(R.drawable.dude_23, R.raw.kpeters, "Kalle", new Point(1031, 362)));
        piecePositions.add(new Dude(R.drawable.dude_24, R.raw.kfayelun, "Kristi", new Point(1242, 330)));
        piecePositions.add(new Dude(R.drawable.dude_26, R.raw.jogonza4, "Luis", new Point(1657, 527)));
        piecePositions.add(new Dude(R.drawable.dude_27, defaultSound, "Norma", new Point(1360, 571)));
        piecePositions.add(new Dude(R.drawable.dude_28, defaultSound, "Kirti", new Point(1170, 490)));
        piecePositions.add(new Dude(R.drawable.dude_30, R.raw.lbergers, "Lene", new Point(696, 573)));
        piecePositions.add(new Dude(R.drawable.dude_31, R.raw.adwarpal, "Achyut", new Point(454, 523)));
        piecePositions.add(new Dude(R.drawable.dude_32, defaultSound, "Bharat", new Point(227, 501)));
        return piecePositions;
    }

}
