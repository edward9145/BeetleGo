package game.edw.beetlego;

import game.edw.beetlego.data.Config;
import game.edw.beetlego.data.Storage;
import game.edw.beetlego.menu.HowFrame;
import game.edw.beetlego.menu.MenuFrame;
import game.edw.beetlego.menu.TopRankFrame;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class BeetleGoActivity extends Activity {

	ImageButton ibMainSound;
	ImageButton ibMainMusic;
	ImageView ivPlane;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        ibMainSound = (ImageButton)findViewById(R.id.ibMainSound);
        ibMainMusic = (ImageButton)findViewById(R.id.ibMainMusic);
        ivPlane = (ImageView)findViewById(R.id.ivPlane);
        
        C.initConfig(Storage.loadConfig(this));
        
        ibMainMusic.setImageResource(C.hasMusic ? 
				R.drawable.btn_musicon :
				R.drawable.btn_musicoff);
        ibMainSound.setImageResource(C.hasSound ? 
				R.drawable.btn_soundon :
				R.drawable.btn_soundoff);
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
    	super.onWindowFocusChanged(hasFocus);
    	ivPlane.setImageResource(R.anim.plane_anim);
    	AnimationDrawable anim = (AnimationDrawable)ivPlane.getDrawable();
    	anim.start();
    }
    
    @Override
    protected void onDestroy(){
    	Storage.saveConfig(new Config(C.STAGE_NUM, C.hasSound, C.hasMusic, C.LAST_NAME), this);
    	super.onDestroy();
    }
    
    public void toPlay(View view){
    	Log.d("PLAY", view.getId() + "");
    	startGameFrame();
    }
    
    public void toMenu(View view){
    	Log.d("MENU", view.getId() + "");
    	Intent intent = new Intent();
		intent.setClass(BeetleGoActivity.this, MenuFrame.class);
		startActivity(intent);
    }
    
    public void toHelp(View view){
    	Intent intent = new Intent();
		intent.setClass(BeetleGoActivity.this, HowFrame.class);
		startActivity(intent);
    }
    
    public void toScore(View view){
    	Intent intent = new Intent();
		intent.setClass(BeetleGoActivity.this, TopRankFrame.class);
		startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//    	Intent intent = new Intent(BeetleGoActivity.this, DebugFrame.class);
//    	startActivity(intent);
		return false;
    }
    
    public void startGameFrame(){
    	C.STAGE_ID = 0;
		C.STAGE_LENGTH = C.STAGE_INFO[C.STAGE_ID];
		Intent intent = new Intent();
		intent.setClass(BeetleGoActivity.this, LoadingFrame.class);
		startActivity(intent);
    }
    
    public void startModeFrame(){
    	Intent intent = new Intent();
		intent.setClass(BeetleGoActivity.this, SelectModeFrame.class);
		startActivity(intent);
    }
    
    public void setSound(View view){
		C.hasSound = !C.hasSound;
		ibMainSound.setImageResource(C.hasSound ? 
								R.drawable.btn_soundon :
								R.drawable.btn_soundoff);
	}
	
	public void setMusic(View view){
		C.hasMusic = !C.hasMusic;
		ibMainMusic.setImageResource(C.hasMusic ? 
								R.drawable.btn_musicon :
								R.drawable.btn_musicoff);
	}
    
}