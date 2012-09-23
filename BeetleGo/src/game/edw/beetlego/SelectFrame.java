package game.edw.beetlego;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class SelectFrame extends Activity {
	
	ImageButton ibStages[] = new ImageButton[6];
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.select);
    }
	
	@Override
	protected void onResume(){
		super.onResume();
		enableStage();
	}
	
	public void enableStage(){
		for(int i=0; i<6; i++){
			ibStages[i] = (ImageButton)findViewById(R.id.ibStage1 + i);
			ibStages[i].setEnabled(false);
		}
		for(int i=0; i<C.STAGE_NUM; i++){
			ibStages[i].setBackgroundResource(R.drawable.btn_stage1 + i);
			ibStages[i].setEnabled(true);
		}
	}
	
	public void stage1_onclick(View view){
		view.setEnabled(false);
		startStage(1);
	}
	public void stage2_onclick(View view){
		view.setEnabled(false);
		startStage(2);
	}
	public void stage3_onclick(View view){
		view.setEnabled(false);
		startStage(3);
	}
	public void stage4_onclick(View view){
		view.setEnabled(false);
		startStage(4);
	}
	public void stage5_onclick(View view){
		view.setEnabled(false);
		startStage(5);
	}
	public void stage6_onclick(View view){
		view.setEnabled(false);
		startStage(6);
	}
	
	public void startStage(int stageID){
		if(stageID<1) stageID = 1;
		else if(stageID>C.STAGE_MAX) stageID = C.STAGE_MAX;
		
		C.STAGE_ID = stageID;
		C.STAGE_LENGTH = C.STAGE_INFO[C.STAGE_ID];
		Log.d("stage_id", "" + C.STAGE_ID);
		
		Intent intent = new Intent();
		intent.setClass(SelectFrame.this, LoadingFrame.class);
		startActivity(intent);
	}
		
	public void back_onclick(View view){
		finish();
	}
}
