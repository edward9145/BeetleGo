package game.edw.beetlego.menu;

import game.edw.beetlego.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class HowFrame extends Activity {
	
	ImageView ivHow;
	int[] howRes = 
		{R.drawable.help0, R.drawable.help1};
//		{	R.drawable.how_moveup, R.drawable.how_movedown, 
//			R.drawable.how_shield, R.drawable.how_score};
	int howId = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.how);
        ivHow = (ImageView)findViewById(R.id.ivHow);

    }
	
	public void back_onclick(View view){
		finish();
	}
	
	public void next_onclick(View view){
		howId ++;
		if(howId >= howRes.length) howId = 0;
		ivHow.setBackgroundResource(howRes[howId]);
	}
	
	public void prev_onclick(View view){
		howId --;
		if(howId < 0) howId = howRes.length - 1;
		ivHow.setBackgroundResource(howRes[howId]);
	}
}
