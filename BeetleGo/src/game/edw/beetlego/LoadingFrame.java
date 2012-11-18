package game.edw.beetlego;

import game.edw.beetlego.manager.BitmapManager;
import game.edw.beetlego.manager.SoundManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class LoadingFrame extends Activity {
	
	Handler hWait = new Handler();
	long waitTime = 100;
	
	ImageView ivLoad_fg;
	ImageView ivLoading;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.loading);
        ivLoad_fg = (ImageView)findViewById(R.id.ivLoad_fg);
        ivLoad_fg.setVisibility(View.INVISIBLE);
        ivLoading = (ImageView)findViewById(R.id.ivLoading);
        
        configResolution();

      hWait.postDelayed(new Runnable(){
    	@Override
    	public void run(){
    		loading();
    	}
    }, waitTime);
        
    }
	
	@Override
	public void onBackPressed() {
		return ;
	}
	
	public void configResolution(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获得手机分辨率
        C.SCR_W = dm.widthPixels;
        C.SCR_H = dm.heightPixels;
        Log.d("SCR_W, H", C.SCR_W +" , " + C.SCR_H);
        if(C.SCR_W < C.SCR_H){ C.SCR_H ^= C.SCR_W; C.SCR_W ^= C.SCR_H; C.SCR_H ^= C.SCR_W;}
        C.SCR_RECT = new Rect(0, 0, C.SCR_W, C.SCR_H);
	}
	
	public void loading(){
		BitmapManager.loadBitmaps(this.getBaseContext());
		SoundManager.loadSound(this.getBaseContext());
		ivLoad_fg.setVisibility(View.VISIBLE);
		finish();
		Intent intent = new Intent(LoadingFrame.this, GameFrame.class);
		startActivity(intent);
	}
	
}
