package com.cisco.photogame;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GameController {

    private List<Dude> dudes;
    private View game;
    private static final int ACCEPTED_DISTANCE = 80;
    private Dude currentPiece;
    private TextView dbgText;
    private Context context;
    private long startTime;
    private Highscore highscore;
    private int totalDudeCount;
    private final float scaleFactor = 0.4f; // todo find out what this actually should be on iltempo

    public GameController(View game) {
        this.game = game;
        this.context = game.getContext();

        highscore = new Highscore(context);
        setListeners();
        dbgText = (TextView) game.findViewById(R.id.debug);
        startGame();
    }



    private void setNextPiece() {
        int index = (int) Math.floor(Math.random() * dudes.size());
        currentPiece = dudes.remove(index);
        ((ImageView) game.findViewById(R.id.puzzle_piece)).setImageResource(currentPiece.getBitmapId());
        updateProgress();
    }

    private void startGame() {
        dudes = Positions.getDudes();
        totalDudeCount = dudes.size();
        setNextPiece();
        startTime = System.currentTimeMillis();
    }

    private boolean checkSpot(Point pos, Dude dude) {
        int dx = pos.x - dude.getPosition().x;
        int dy = pos.y - dude.getPosition().y;
        int dist = (int) Math.sqrt(dx*dx + dy*dy);
        //debug("distance %d", dist);
        //dbgText.setText("distance: " + dist);
        debug2("%d, %d - %d", pos.x, pos.y, dist);

        if (dist < ACCEPTED_DISTANCE)
            dbgText.setBackgroundColor(0x99009900);
        else
            dbgText.setBackgroundColor(0x99990000);

        return (dist < ACCEPTED_DISTANCE);
    }

    private void setListeners() {

        View piece = game.findViewById(R.id.puzzle_piece);
        piece.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.startDrag(null, new DragShadow(view, currentPiece.getBitmapId(), scaleFactor), view, 0);
                }
                return true;
            }
        });

        View gameBoard = game.findViewById(R.id.gamephoto);
        gameBoard.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DRAG_LOCATION) {
                    checkSpot(new Point((int) dragEvent.getX(), (int) dragEvent.getY()), currentPiece);
                }
                else if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
                    Point drop = new Point((int) dragEvent.getX(), (int) dragEvent.getY());
                    if (checkSpot(drop, currentPiece))
                        spotFound(drop, currentPiece);
                }
                return true;
            }
        });

        game.findViewById(R.id.restart_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }

    private void restartGame() {
        setCompletePhotoAlpha(0);
        game.findViewById(R.id.puzzle_piece).setVisibility(View.VISIBLE);
        removeCompletedDudes();
        startGame();
    }

    private void setCompletePhotoAlpha(float alpha) {
        game.findViewById(R.id.completed_gamephoto).setAlpha(alpha);
    }

    private void removeCompletedDudes() {
        View mainPhoto = game.findViewById(R.id.gamephoto);
        ViewGroup frame = ((ViewGroup) game.findViewById(R.id.photo_container));
        frame.removeAllViews();
        frame.addView(mainPhoto);

    }

    private void spotFound(Point pos, Dude dude) {
        debug("Found spot for %s", dude.getName());

        addPieceToBoard(pos, dude);

        if (! dudes.isEmpty()) {
            setNextPiece();
            updateProgress();
        }
        else
            gameFinished();
    }

    private void updateProgress() {
        TextView progress = (TextView) game.findViewById(R.id.progress);
        int count = totalDudeCount - dudes.size() - 1;
        progress.setText(String.format("%d / %d", count, totalDudeCount));
    }

    private void addPieceToBoard(Point dropPos, Dude dude) {
        Point pos = dude.getPosition();
        ImageView view = new ImageView(context);
        Drawable drawable = context.getResources().getDrawable(dude.getBitmapId());
        view.setImageResource(dude.getBitmapId());
        int w = (int) (drawable.getIntrinsicWidth() * scaleFactor);
        int h = (int) (drawable.getIntrinsicHeight() * scaleFactor);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(w, w);
        p.setMargins(pos.x - w/2, pos.y - w/2, 0, 0);
        view.setLayoutParams(p);
        ((ViewGroup) game.findViewById(R.id.photo_container)).addView(view);

    }

    private int[] elapsedTime() {
        int time = (int) ((System.currentTimeMillis() - startTime) / 1000);
        int mins = time / 60;
        time = time - mins * 60;
        int secs = time;
        return new int[]{mins, secs, time};
    }

    private void gameFinished() {
        game.findViewById(R.id.puzzle_piece).setVisibility(View.GONE);
        setCompletePhotoAlpha(1);
        
        int duration = Toast.LENGTH_SHORT;
        int[] time = elapsedTime();
        Toast toast = Toast.makeText(context, String.format("Game finished, time: %d:%d", time[0], time[1]), duration);
        toast.show();

        highscore.saveHighscore(time[2], "time=" + time[2]);
        highscore.debugHighscore();
    }

    public void debug2(String message, Object ... args) {
        ((TextView) game.findViewById(R.id.debug)).setText(String.format(message, args));
        Log.i("photogame", String.format(message, args));
    }

    public static void debug(String message, Object ... args) {
        Log.i("photogame", String.format(message, args));
    }

}
