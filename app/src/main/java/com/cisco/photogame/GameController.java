package com.cisco.photogame;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private List<Dude> dudes;
    private View game;
    private static final int ACCEPTED_DISTANCE = 50;
    private Dude currentPiece;
    private TextView dbgText;
    private Context context;

    public GameController(View game) {
        this.game = game;
        this.context = game.getContext();
        dudes = Positions.defineHitAreas();
        setListeners();
        setNextPiece();
        dbgText = (TextView) game.findViewById(R.id.distancedebug);
    }

    private void setNextPiece() {
        int index = (int) Math.floor(Math.random() * dudes.size());
        currentPiece = dudes.remove(index);
        ((ImageView) game.findViewById(R.id.puzzle_piece)).setImageResource(currentPiece.getBitmapId());
    }

    private boolean foundRightSpot(Point pos, Dude dude) {
        int dx = pos.x - dude.getPosition().x;
        int dy = pos.y - dude.getPosition().y;
        int dist = (int) Math.sqrt(dx*dx + dy*dy);
        //debug("distance %d", dist);
        dbgText.setText("distance: " + dist);
        if (dist < ACCEPTED_DISTANCE)
            dbgText.setBackgroundColor(0xFF009900);
        else
            dbgText.setBackgroundColor(0xFF990000);

        return (dist < ACCEPTED_DISTANCE);
    }

    private void setListeners() {
        View piece = game.findViewById(R.id.puzzle_piece);
        piece.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.startDrag(null, new View.DragShadowBuilder(view), view, 0);
                }
                return true;
            }
        });

        View gameBoard = game.findViewById(R.id.gamephoto);
        gameBoard.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DRAG_LOCATION) {
                    foundRightSpot(new Point((int) dragEvent.getX(), (int) dragEvent.getY()), currentPiece);
                    //debug("drag on %d,%d", (int) dragEvent.getX(), (int) dragEvent.getY());
                }
                else if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
                    Point drop = new Point((int) dragEvent.getX(), (int) dragEvent.getY());
                    if (foundRightSpot(drop, currentPiece))
                        spotFound(drop, currentPiece);
                }
                return true;
            }
        });
    }

    private void spotFound(Point pos, Dude dude) {
        debug("Found spot for %s", dude.getName());

        addPieceToBoard(pos, dude);

        if (! dudes.isEmpty())
            setNextPiece();
        else
            gameFinished();
    }

    private void addPieceToBoard(Point dropPos, Dude dude) {
        Point pos = dude.getPosition();
        ImageView view = new ImageView(context);
        view.setImageResource(dude.getBitmapId());
        int w = 120;
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(w, w);
        p.setMargins(pos.x - w/2, pos.y - w/2, 0, 0);
        view.setLayoutParams(p);
        ((ViewGroup) game).addView(view);

    }

    private void gameFinished() {
        game.findViewById(R.id.puzzle_piece).setVisibility(View.GONE);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Game finished!", duration);
        toast.show();
    }

    public static void debug(String message, Object ... args) {
        Log.i("photogame", String.format(message, args));
    }

}
