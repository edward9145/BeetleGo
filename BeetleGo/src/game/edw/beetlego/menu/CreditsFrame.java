package game.edw.beetlego.menu;

import game.edw.beetlego.R;
import game.edw.beetlego.R.layout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class CreditsFrame extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.credits);

    }
	
	public void toMenu(View view){
		finish();
	}
}
