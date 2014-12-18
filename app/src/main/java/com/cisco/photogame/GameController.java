package com.cisco.photogame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class GameController {

    private List<Dude> dudes;
    private View game;
    private static final int ACCEPTED_DISTANCE = 80;
    private Dude currentPiece;
    private TextView dbgText;
    private Context context;
    private long startTime;
    private int totalDudeCount;
    private final float scaleFactor = 1.21f; // todo find out what this actually should be on iltempo
    private Handler timer;
    private SoundController soundController;

    public GameController(View game) {
        this.game = game;
        this.context = game.getContext();
        this.timer = new Handler();
        this.dbgText = (TextView) game.findViewById(R.id.debug);
        this.soundController = new SoundController(context);

        soundController.loadSound(R.raw.acideron);
        setListeners();
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
        timer.postDelayed(updateTimerTask, 1000);
        ((TextView) game.findViewById(R.id.duration)).setText("00:00");
        ((TextView) game.findViewById(R.id.restart_button)).setText("Restart");
        soundController.playSound();
    }

    private boolean checkSpot(Point pos, Dude dude) {
        int dx = pos.x - dude.getPosition().x;
        int dy = pos.y - dude.getPosition().y;
        int dist = (int) Math.sqrt(dx*dx + dy*dy);
        //debug("distance %d", dist);
        //dbgText.setText("distance: " + dist);
        debug2("%s Point(%d, %d) ", dude.getName(), pos.x, pos.y, dist);

        if (dist < ACCEPTED_DISTANCE)
            dbgText.setBackgroundColor(0x99009900);
        else
            dbgText.setBackgroundColor(0x99990000);

        //return (dist < ACCEPTED_DISTANCE);
        return true; // TODO REMOVE!!! Just for finding locations quickly
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

        View gameBoard = game.findViewById(R.id.photo_container);
        gameBoard.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DRAG_LOCATION) {
                    //checkSpot(new Point((int) dragEvent.getX(), (int) dragEvent.getY()), currentPiece);
                } else if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
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
        stopTimerTask();
        setCompletePhotoAlpha(0);
        game.findViewById(R.id.puzzle_piece).setVisibility(View.VISIBLE);
        removeCompletedDudes();
        startGame();
    }

    private void setCompletePhotoAlpha(float alpha) {
//        float start = (alpha > 0 ? 0 : 1);
//        AlphaAnimation fade = new AlphaAnimation(start, alpha);
//        fade.setDuration(1000);
//        fade.setFillAfter(true);
//        game.findViewById(R.id.completed_gamephoto).startAnimation(fade);
        game.findViewById(R.id.completed_gamephoto).setAlpha(alpha);
    }

    private void removeCompletedDudes() {
        View mainPhoto = game.findViewById(R.id.gamephoto);
        View facesPhoto = game.findViewById(R.id.completed_gamephoto);

        ViewGroup frame = ((ViewGroup) game.findViewById(R.id.photo_container));

        frame.removeAllViews();

        frame.addView(mainPhoto);
        frame.addView(facesPhoto);
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
        progress.setText(String.format("%d/%d", count, totalDudeCount));
    }

    private void addPieceToBoard(Point dropPos, Dude dude) {
        Point pos = dude.getPosition();
        ImageView view = new ImageView(context);
        Drawable drawable = context.getResources().getDrawable(dude.getBitmapId());
        view.setImageResource(dude.getBitmapId());
        int w = (int) (drawable.getIntrinsicWidth() * scaleFactor);
        int h = (int) (drawable.getIntrinsicHeight() * scaleFactor);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(w, h);
        p.setMargins(pos.x - w/2, pos.y - h/2, 0, 0);
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

    private void stopTimerTask() {
        timer.removeCallbacksAndMessages(null);
    }

    private void gameFinished() {
        stopTimerTask();
        game.findViewById(R.id.puzzle_piece).setVisibility(View.GONE);
        ((TextView) game.findViewById(R.id.progress)).setText(totalDudeCount + "/" + totalDudeCount);
        setCompletePhotoAlpha(1);

        int[] time = elapsedTime();
        Intent highscore = new Intent(context, HighscoreActivity.class);
        highscore.putExtra("time", time[2]);
        context.startActivity(highscore);
    }

    public void debug2(String message, Object ... args) {
        ((TextView) game.findViewById(R.id.debug)).setText(String.format(message, args));
        Log.i("photogame", String.format(message, args));
    }

    public static void debug(String message, Object ... args) {
        Log.i("photogame", String.format(message, args));
    }


    private Runnable updateTimerTask = new Runnable() {
        @Override
        public void run() {
            int[] time = elapsedTime();
            int minutes = time[0];
            int secs = time[1];

            String str = String.format("%02d:%02d", minutes, secs);

            ((TextView) game.findViewById(R.id.duration)).setText(str);
            timer.postDelayed(updateTimerTask, 1000);
        }
    };


}
