package game.edw.beetlego;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class LoadingFrame extends Activity {
	
	Handler hWait = new Handler();
	long waitTime = 500;
	
	ImageView ivLoad_fg;
	ImageView ivLoading;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.loading);
        ivLoad_fg = (ImageView)findViewById(R.id.ivLoad_fg);
        ivLoading = (ImageView)findViewById(R.id.ivLoading);
        
        hWait.postDelayed(new Runnable(){
        	@Override
        	public void run(){
        		finish();
        		Intent intent = new Intent(LoadingFrame.this, GameFrame.class);
        		startActivity(intent);
        	}
        }, waitTime);
        
    }
	
	@Override
	public void onBackPressed() {
		return ;
	}
	
}
