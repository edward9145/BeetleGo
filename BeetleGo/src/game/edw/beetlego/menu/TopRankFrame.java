package game.edw.beetlego.menu;

import game.edw.beetlego.C;
import game.edw.beetlego.R;
import game.edw.beetlego.data.RankScore;
import game.edw.beetlego.data.Storage;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class TopRankFrame extends Activity {
	
	RankScore rankScore;
	TextView[] tvScores = new TextView[C.RANK_NUM];
	TextView[] tvNames = new TextView[C.RANK_NUM];
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.toprank);
        rankScore = Storage.loadScore(this);
        
        for(int i=0; i<C.RANK_NUM; i++){
        	tvScores[i] = (TextView)findViewById(R.id.tvTopScore1 + i*2);
        	tvNames[i] = (TextView)findViewById(R.id.tvTopName1 + i*2);
        	
        	tvScores[i].setText(String.format("%06d", rankScore.getScore(i)));
        	tvNames[i].setText(rankScore.getName(i));
        }
        
        
    }
	
	public void toMenu(View view){
		finish();
	}
	
}
