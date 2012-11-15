package game.edw.beetlego;

import game.edw.beetlego.data.RankScore;
import game.edw.beetlego.data.Storage;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverFrame extends Activity {
	
	TextView tvScore;
	RankScore rankScore;
	EditText etOverName;
	LinearLayout llName;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.gameover);
        
        llName = (LinearLayout)findViewById(R.id.llOverNmae);
        
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Bradley Hand ITC.TTF");
        etOverName = (EditText)findViewById(R.id.etOverName);
        etOverName.setText(C.LAST_NAME);
        etOverName.setTypeface(typeFace, Typeface.BOLD);
        
        tvScore = (TextView)findViewById(R.id.tvScore);
        C.score *= 10;
        tvScore.setText(C.score + "");
        tvScore.setTypeface(typeFace, Typeface.BOLD);
        
        rankScore = Storage.loadScore(this);
        int result = rankScore.testRank(C.score);
        if(result != -1)
        	showMsg("Congratulation! You are the " + (result+1) + " in TOP 5");
        else
        	llName.setVisibility(View.GONE);
	}

	public void saveScore_onclick(View view){
		String name = etOverName.getText().toString();
		Storage.saveScore(rankScore, C.score, name, this);
		C.LAST_NAME = name;
		llName.setVisibility(View.GONE);
	}
	
	public void showMsg(final String s){
		this.runOnUiThread(new Runnable() {
			public void run(){
				Toast.makeText(GameOverFrame.this, s, Toast.LENGTH_SHORT)
				.show();
			}
		});
	}
	
	public void restart_onclick(View view){
		view.setEnabled(false);
		
		finish();
		Intent intent = new Intent(GameOverFrame.this, LoadingFrame.class);
		startActivity(intent);
	}
	
	public void back_onclick(View view){
		finish();
	}
}
