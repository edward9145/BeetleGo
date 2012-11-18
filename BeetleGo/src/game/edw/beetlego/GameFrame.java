package game.edw.beetlego;

import game.edw.beetlego.audio.AudioClip;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class GameFrame extends Activity {
	
	SeekBar sbStage;
	GameView gameView = null;
	ImageButton ibPlay;
	ImageButton ibReplay;
	ImageButton ibShield;
	ImageView ivShield0;
	ImageView ivShield1;
	
	LinearLayout llGame;
	ImageView []ivHeart = new ImageView[5];
	ImageView []ivScore = new ImageView[6];
	AudioClip acBG = null;
	
	Handler hWait = new Handler();
	long waitTime = 1000;
	
	SensorManager sensorManager = null;
	Sensor sAcc;
	
	int checkScore = C.score;
	int checkLife = C.PLANE_LIFE;
	int checkShield = 1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.game);
        ibPlay = (ImageButton)findViewById(R.id.ibPlay);
        ibReplay = (ImageButton)findViewById(R.id.ibReplay);
        ivShield0 = (ImageView)findViewById(R.id.ivShield0);
        ivShield1 = (ImageView)findViewById(R.id.ivShield1);
        ibShield = (ImageButton)findViewById(R.id.ibShield);
        
        sbStage = (SeekBar)findViewById(R.id.sbStage);
        sbStage.setEnabled(false);
        sbStage.setMax(C.STAGE_LENGTH);
        if(C.STAGE_ID == 0) sbStage.setVisibility(View.GONE);
        
        llGame = (LinearLayout)findViewById(R.id.llGame);
        ivHeart[0] = (ImageView)findViewById(R.id.ivHeart0);
        ivHeart[1] = (ImageView)findViewById(R.id.ivHeart1);
        ivHeart[2] = (ImageView)findViewById(R.id.ivHeart2);
        ivHeart[3] = (ImageView)findViewById(R.id.ivHeart3);
        ivHeart[4] = (ImageView)findViewById(R.id.ivHeart4);
        
        ivScore[0] = (ImageView)findViewById(R.id.ivScore0);
        ivScore[1] = (ImageView)findViewById(R.id.ivScore1);
        ivScore[2] = (ImageView)findViewById(R.id.ivScore2);
        ivScore[3] = (ImageView)findViewById(R.id.ivScore3);
        ivScore[4] = (ImageView)findViewById(R.id.ivScore4);
        ivScore[5] = (ImageView)findViewById(R.id.ivScore5);
        
        setLife(C.PLANE_LIFE);
        gameView = new GameView(this);
        gameView.setFrame(GameFrame.this);
        gameView.initGame();
        Log.d("GameFrame", "CREATE gameView.initGame()");
        llGame.addView(gameView);
        
        acBG = new AudioClip(this, R.raw.bg_music);
        
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sAcc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
	
	@Override
    protected void onResume(){
    	super.onResume();
    	if(C.hasGravity){
    		sensorManager.registerListener(this.gameView, sAcc, SensorManager.SENSOR_DELAY_GAME);
    		Log.d("RESUME", "sensor register");
    	}
    	if(gameView.state == C.GAME_PAUSE){
    		ibPlay.setImageResource(R.drawable.btn_play);
    	}
    	acBG.play(true);
    	gameView.startTimer();
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	Log.d("GameFrame", "PAUSE!");
    	if(C.hasGravity){
    		sensorManager.unregisterListener(this.gameView);
    		Log.d("PAUSE", "sensor unregister");
    	}
    	if(gameView.state == C.GAME_PLAY){
    		gameView.state = C.GAME_PAUSE;
    	}
    	acBG.pauseMusic();

    	// TODO !! should handle the pause event(store the scene and then restore). I'm lazy...
    	gameView.state = -1;
    	gameView.release();
    	finish();
    }
	
	@Override
	protected void onStop(){
		super.onStop();
		Log.d("GameFrame", "STOP!");
		if(gameView != null){
			gameView.stopTimer();
		}
		if(acBG != null){
			acBG.freeMusic();
		}
	}
	
	public void replay_onclick(View view){
		gameView.stopTimer();
		gameView.initGame();
		gameView.updateInfo();
		setSeekBar(0);
		setPlayBtn();
		setShield(gameView.shieldNum);
		gameView.startTimer();
	}
	
	public void play_onclick(View view){
		setPlayBtn();
	}
	
	public void shield_onclick(View view){
		gameView.enableShelter();
		setShield(gameView.shieldNum);
		Log.d("SHELTER", "enable | SHIELD " + gameView.shieldNum);
	}
	
	public void showMsg(final String s){
		this.runOnUiThread(new Runnable() {
			public void run(){
				Toast.makeText(GameFrame.this, s, Toast.LENGTH_SHORT)
				.show();
			}
		});
	}
	
	public void setPlayBtn(){
		if(gameView.state == C.GAME_PLAY){
			gameView.state = C.GAME_PAUSE;
			ibPlay.setImageResource(R.drawable.btn_play);
			ibReplay.setVisibility(View.VISIBLE);
		}
		else if(gameView.state == C.GAME_PAUSE){
			gameView.state = C.GAME_PLAY;
			ibPlay.setImageResource(R.drawable.btn_pause);
			ibReplay.setVisibility(View.GONE);
		}
		else{
			ibPlay.setImageResource(R.drawable.btn_pause);
			ibReplay.setVisibility(View.GONE);
		}
	}
	
	public void setLife(final int life){
		if(life == checkLife) return;
		this.runOnUiThread(new Runnable() {
			public void run(){
				checkLife = life;
				
				Log.d("Life", ""+ life);
				int tmplife = (life>C.PLANE_MAXLIFE) ?
						C.PLANE_MAXLIFE : life;
				int num = tmplife / 2;
				int half = tmplife % 2;
				int i = 0;
				for(i=0;i<num;i++)
					ivHeart[i].setImageResource(R.drawable.heart);
				if(half == 1)
					ivHeart[i++].setImageResource(R.drawable.heart_half);
				for(;i<5;i++)
					ivHeart[i].setImageResource(R.drawable.heart_none);
			}
		});
	}
	
	public void setSeekBar(final int pos){
		this.runOnUiThread(new Runnable() {
			public void run(){
				if(pos > 0 && pos < C.STAGE_LENGTH)
					sbStage.setProgress(pos);
			}
		});
	}
	
	public void setScore(final int score){
		if(score == checkScore) return;
		this.runOnUiThread(new Runnable() {
			public void run(){
				checkScore = score;
				
				int s = score>0 ? score : 0;
				for(int i=5; i>=0; i--){
					ivScore[i].setImageResource(R.drawable.num0 + s % 10);
					s/=10;
				}
			}
		});
	}
	
	public void setShield(final int shieldNum){
		if(shieldNum == checkShield) return;
		this.runOnUiThread(new Runnable() {
			public void run(){
				if(shieldNum == 0){
					ivShield0.setVisibility(View.GONE);
					ivShield1.setVisibility(View.GONE);
					ibShield.setVisibility(View.GONE);
				}
				else if(shieldNum == 1){
					ivShield0.setVisibility(View.GONE);
					ivShield1.setVisibility(View.GONE);
					ibShield.setVisibility(View.VISIBLE);
				}
				else if(shieldNum == 2){
					ivShield0.setVisibility(View.GONE);
					ivShield1.setVisibility(View.VISIBLE);
					ibShield.setVisibility(View.VISIBLE);
				}
				else if(shieldNum == 3){
					ivShield0.setVisibility(View.VISIBLE);
					ivShield1.setVisibility(View.VISIBLE);
					ibShield.setVisibility(View.VISIBLE);
				}
				checkShield = shieldNum;
			}
		});
	}
	
	public void gameOver(){
		hWait.postDelayed(new Runnable(){
        	@Override
        	public void run(){
        		finish();
        		Intent intent = new Intent(GameFrame.this, GameOverFrame.class);
        		startActivity(intent);
        	}
        }, waitTime);
		
	}
	
	
	public void gameWin(){		
		hWait.postDelayed(new Runnable(){
        	@Override
        	public void run(){
        		finish();
        		Intent intent = new Intent(GameFrame.this, GameWinFrame.class);
        		startActivity(intent);
        	}
        }, waitTime);
		
	}
	
	@Override
	public void onBackPressed() {
		if(gameView.state == C.GAME_PLAY){
			gameView.state = C.GAME_PAUSE;
			ibPlay.setImageResource(R.drawable.btn_play);
			ibReplay.setVisibility(View.VISIBLE);
			return ;
		}
		gameView.state = -1;	// won't invoke draw();
		gameView.stopTimer();
		super.onBackPressed();
	}

}
