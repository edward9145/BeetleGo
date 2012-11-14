package game.edw.beetlego;

import game.edw.beetlego.data.RankScore;
import game.edw.beetlego.data.Storage;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameWinFrame extends Activity {
	
	TextView tvWinScore;
	RankScore rankScore;
	EditText etWinName;
	LinearLayout llName;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.gamewin);
        
        llName = (LinearLayout)findViewById(R.id.llWinNmae);
        
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Bradley Hand ITC.TTF");
        etWinName = (EditText)findViewById(R.id.etWinName);
        etWinName.setText(C.LAST_NAME);
        etWinName.setTypeface(typeFace, Typeface.BOLD);
        
        tvWinScore = (TextView)findViewById(R.id.tvWinScore);
        C.score *= 10;	// convert to storage score
        tvWinScore.setText(C.score + "");
        tvWinScore.setTypeface(typeFace, Typeface.BOLD);
        
        rankScore = Storage.loadScore(this);
        int result = rankScore.testRank(C.score);
        if(result != -1)
        	showMsg("Congratulation! You are the " + (result+1) + " in TOP 5");
        else
        	llName.setVisibility(View.GONE);
        
        unlockStage();
	}

	public void saveScore_onclick(View view){
		String name = etWinName.getText().toString();
		C.LAST_NAME = name;
		Storage.saveScore(rankScore, C.score, name, this);
		llName.setVisibility(View.GONE);
	}
	
	public void showMsg(final String s){
		this.runOnUiThread(new Runnable() {
			public void run(){
				Toast.makeText(GameWinFrame.this, s, Toast.LENGTH_SHORT)
				.show();
			}
		});
	}
	
	public void back_onclick(View view){
		finish();
	}
	
	public void next_onclick(View view){
		view.setEnabled(false);
		
		finish();
		C.STAGE_LENGTH = C.STAGE_INFO[C.STAGE_ID];
		Intent intent = new Intent(GameWinFrame.this, GameFrame.class);
		startActivity(intent);
	}
	
	public void unlockStage(){
		C.STAGE_ID ++;
		if(C.STAGE_ID > C.STAGE_NUM) C.STAGE_NUM ++;
		if(C.STAGE_ID > C.STAGE_MAX) C.STAGE_ID = C.STAGE_MAX;
		if(C.STAGE_NUM > C.STAGE_MAX) C.STAGE_NUM = C.STAGE_MAX;
		Log.d("stage_info", "id: " + C.STAGE_ID + " num: " + C.STAGE_NUM);
	}
}
