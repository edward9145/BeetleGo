package game.edw.beetlego;

import game.edw.beetlego.menu.HowFrame;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SelectModeFrame extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.mode);
    }
	
	
	public void normalMode_onclick(View view){
		Intent intent = new Intent();
		intent.setClass(SelectModeFrame.this, SelectFrame.class);
		startActivity(intent);
	}
	public void endlessMode_onclick(View view){
		C.STAGE_ID = 0;
		C.STAGE_LENGTH = C.STAGE_INFO[C.STAGE_ID];
		Intent intent = new Intent();
		intent.setClass(SelectModeFrame.this, LoadingFrame.class);
		startActivity(intent);
	}

	public void normalMode_info(View view){
		showMsg("Levels is waiting for you...");
	}
	public void endlessMode_info(View view){
		showMsg("Hold on! Break the record!");
	}

	public void showMsg(final String s){
		this.runOnUiThread(new Runnable() {
			public void run(){
				Toast.makeText(SelectModeFrame.this, s, Toast.LENGTH_LONG)
				.show();
			}
		});
	}
	
	public void back_onclick(View view){
		finish();
	}
	
	public void toHow(View view){
		Intent intent = new Intent();
		intent.setClass(SelectModeFrame.this, HowFrame.class);
		startActivity(intent);
	}
}
