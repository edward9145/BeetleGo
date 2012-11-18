package game.edw.beetlego.manager;

import game.edw.beetlego.C;
import game.edw.beetlego.R;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	
	public static SoundPool soundPool = null;
	public static HashMap<String, Integer> soundMap = null;
	
	public static void loadSound(Context context){
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 7);
        soundMap = new HashMap<String, Integer>(2);
        soundMap.put("bang0", soundPool.load(context, R.raw.bang0, 1));
		soundMap.put("bang1", soundPool.load(context, R.raw.bang1, 1));
		soundMap.put("shelter", soundPool.load(context, R.raw.shelter, 1));
		soundMap.put("thunder", soundPool.load(context, R.raw.thunder, 1));
	}
	
	public static void play(final int soundID){
		if(C.hasSound)
			soundPool.play(soundID, 0.8f, 0.7f, 0, 0, 1);
		
//		new Handler().post(new Runnable(){
//			public void run(){
//				if(C.hasSound)
//					soundPool.play(soundID, 0.8f, 0.7f, 0, 0, 1);
//			}
//		});
	}
	
	public static void play(final String name){
		Integer soundID = soundMap.get(name);
		if(soundID != null)
			play(soundID);
	}

	public static void release(){
		soundMap.clear();
		soundPool.release();
	}
}
