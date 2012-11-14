package game.edw.beetlego;

import game.edw.beetlego.data.Config;
import game.edw.beetlego.data.Storage;
import game.edw.beetlego.menu.MenuFrame;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BeetleGoActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        
        C.initConfig(Storage.loadConfig(this));
    }
    
    @Override
    protected void onDestroy(){
    	Storage.saveConfig(new Config(C.STAGE_NUM, C.hasSound, C.hasMusic, C.hasGravity, C.LAST_NAME), this);
    	super.onDestroy();
    }
    
    public void toPlay(View view){
    	Log.d("PLAY", view.getId() + "");
    	Intent intent = new Intent();
		intent.setClass(BeetleGoActivity.this, SelectModeFrame.class);
		startActivity(intent);
    }
    
    public void toMenu(View view){
    	Log.d("MENU", view.getId() + "");
    	Intent intent = new Intent();
		intent.setClass(BeetleGoActivity.this, MenuFrame.class);
		startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Intent intent = new Intent(BeetleGoActivity.this, DebugFrame.class);
    	startActivity(intent);
		return false;
    }
    
}