package com.cisco.photogame;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import java.util.HashMap;

public class SoundController {

    private Context context;
    private SoundPool soundPool;

    private HashMap<Integer, Integer> soundPoolMap;
    private AudioManager audioManager;
    private int singleIndex = 0;
    private static final int VOLUME = 2;

    public SoundController(Context context) {
        this.context = context;
        initSounds();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        // who knows....
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, VOLUME, 0);
    }

    private void initSounds() {

        int maxStreams = 1;
        soundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 100);

        soundPoolMap = new HashMap<Integer, Integer>();
    }

    public void loadSound(int rawId) {
        soundPoolMap.put(singleIndex, soundPool.load(context, rawId, 1));
    }

    public void playSound() {
        int priority = 1;
        int loop = 0;
        int volume = 3;
        float pitch = 1f;
        soundPool.play(soundPoolMap.get(singleIndex), volume, volume, priority, loop, pitch);
    }

}
