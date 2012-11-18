package game.edw.beetlego.menu;

import game.edw.beetlego.C;
import game.edw.beetlego.R;
import game.edw.beetlego.data.Storage;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuFrame extends Activity {
	
	ImageButton ibSound;
	ImageButton ibMusic;
	ImageButton ibGravity;
	ImageButton ibDaynight;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.menu);
        ibSound = (ImageButton)findViewById(R.id.ibSound);
        ibMusic = (ImageButton)findViewById(R.id.ibMusic);
        ibGravity = (ImageButton)findViewById(R.id.ibGravity);
        ibDaynight = (ImageButton)findViewById(R.id.ibDaynight);
        
        ibMusic.setImageResource(C.hasMusic ? 
				R.drawable.btn_musicon :
				R.drawable.btn_musicoff);
        ibSound.setImageResource(C.hasSound ? 
				R.drawable.btn_soundon :
				R.drawable.btn_soundoff);
        ibGravity.setImageResource(C.hasGravity ? 
				R.drawable.btn_gravityon :
				R.drawable.btn_gravityoff);
        ibDaynight.setImageResource(C.hasDaynight ? 
				R.drawable.btn_daynighton :
				R.drawable.btn_daynightoff);
    }
	
	public void toHome(View view){
    	Log.d("MENU", view.getId() + "");
    	finish();
    }
	
	public void setSound(View view){
		C.hasSound = !C.hasSound;
		ibSound.setImageResource(C.hasSound ? 
								R.drawable.btn_soundon :
								R.drawable.btn_soundoff);
	}
	
	public void setMusic(View view){
		C.hasMusic = !C.hasMusic;
		ibMusic.setImageResource(C.hasMusic ? 
								R.drawable.btn_musicon :
								R.drawable.btn_musicoff);
	}
	
	public void setGravity(View view){
		C.hasGravity = !C.hasGravity;
		ibGravity.setImageResource(C.hasGravity ? 
					R.drawable.btn_gravityon :
					R.drawable.btn_gravityoff);
	}
	
	public void setDayNight(View view){
		C.hasDaynight = !C.hasDaynight;
		ibDaynight.setImageResource(C.hasDaynight ? 
					R.drawable.btn_daynighton :
					R.drawable.btn_daynightoff);
	}
	
	public void toAchi(View view){
		Intent intent = new Intent();
		intent.setClass(MenuFrame.this, AchieveFrame.class);
		startActivity(intent);
	}
	public void toReset(View view){
//		Intent intent = new Intent(MenuFrame.this, DebugFrame.class);
//    	startActivity(intent);
		resetConfirm();	
	}
	public void toTools(View view){
		Intent intent = new Intent();
		intent.setClass(MenuFrame.this, ToolsFrame.class);
		startActivity(intent);
	}
	public void toTopRank(View view){
		Intent intent = new Intent();
		intent.setClass(MenuFrame.this, TopRankFrame.class);
		startActivity(intent);
	}
	public void toCredits(View view){
		Intent intent = new Intent();
		intent.setClass(MenuFrame.this, CreditsFrame.class);
		startActivity(intent);
	}
	public void toHow(View view){
		Intent intent = new Intent();
		intent.setClass(MenuFrame.this, HowFrame.class);
		startActivity(intent);
	}

	protected void resetConfirm() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("Reset the top ranks and stages? ");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Storage.resetScore(MenuFrame.this);
				Storage.resetConfig(MenuFrame.this);
				Toast.makeText(MenuFrame.this, "Data has been reset", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {		   
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}
