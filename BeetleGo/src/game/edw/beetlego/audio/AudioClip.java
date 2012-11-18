package game.edw.beetlego.audio;

import game.edw.beetlego.C;
import android.content.Context;
import android.media.MediaPlayer;

public class AudioClip {

	public MediaPlayer playerMusic;
	Context mContext;
	public int resID = -1;
	
	public AudioClip(Context m){
		mContext = m;
	}
	
	public AudioClip(Context m, int id){
		mContext = m;
		this.resID = id;
	}
	
	public void play(boolean loop){
		if(resID == -1) return;
		playMusic(this.resID, loop);
	}
	
	public void playMusic(int resID, boolean loop){		
		if (!C.hasMusic) return;
		
		freeMusic();
		//load music
		playerMusic = MediaPlayer.create(mContext, resID);
		
		try{
			//set recycle
			playerMusic.setLooping(loop);
			//prepare
			playerMusic.prepare();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		//start
		playerMusic.start();
	}
	
	public void freeMusic(){
		if (playerMusic != null){
			playerMusic.stop();
			playerMusic.release();
		}
	}
	
	public void stopMusic(){
		if (playerMusic != null){
			playerMusic.stop();
		}
	}
	
	public void pauseMusic(){
		if (playerMusic != null){
			if(playerMusic.isPlaying()){
				playerMusic.pause();
			}
		}
	}
	
	
	public static void musicOn(){
		C.hasMusic = true;
	}
	
	public static void musicOff(){
		C.hasMusic = false;
	}
	
}
