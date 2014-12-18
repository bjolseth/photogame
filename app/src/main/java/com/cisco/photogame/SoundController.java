package com.cisco.photogame;


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundController {

    private Context context;
    private SoundPool soundPool;

    private HashMap<Integer, Integer> soundPoolMap;

    public SoundController(Context context) {
        this.context = context;
    }

    private void initSounds() {

        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);

        soundPoolMap = new HashMap<Integer, Integer>();

        soundPoolMap.put(0, soundPool.load(context, R.raw.acideron, 1));

    }



    public void playTestSound(int sound) {

        AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        int streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

        soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, 0, 1f);

    }
}
