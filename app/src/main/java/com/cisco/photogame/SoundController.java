package com.cisco.photogame;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;

public class SoundController {

    private Context context;
    private SoundPool soundPool;

    private HashMap<Integer, Integer> soundPoolMap;
    private AudioManager audioManager;
    private int DUDE_INDEX = 0;
    private int DOH_INDEX = 1;
    private static final int VOLUME = 4;

    public SoundController(Context context) {
        this.context = context;
        initSounds();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, VOLUME, 0);
    }

    private void initSounds() {

        int maxStreams = 2;
        soundPool = new SoundPool(maxStreams, AudioManager.STREAM_RING, 100);

        soundPoolMap = new HashMap<Integer, Integer>();

        soundPoolMap.put(DOH_INDEX, soundPool.load(context, R.raw.doh, 1));
    }

    public void loadSound(int rawId) {
        soundPoolMap.put(DUDE_INDEX, soundPool.load(context, rawId, 1));
    }

    public void playDoh() {
        Log.i("photogame", "volume=" + audioManager.getStreamVolume(AudioManager.STREAM_RING));
        play(DOH_INDEX);
    }

    public void playNextDudeSound() {
        play(DUDE_INDEX);
    }

    private void play(int index) {
        int priority = 1;
        int loop = 0;
        int volume = 3;
        float pitch = 1f;
        soundPool.play(soundPoolMap.get(index), volume, volume, priority, loop, pitch);

    }

}
