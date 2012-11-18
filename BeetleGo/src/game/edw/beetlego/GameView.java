package game.edw.beetlego;

import game.edw.beetlego.manager.BitmapManager;
import game.edw.beetlego.manager.SceneManager;
import game.edw.beetlego.manager.SoundManager;
import game.edw.beetlego.model.Background;
import game.edw.beetlego.model.Bang;
import game.edw.beetlego.model.Cactus;
import game.edw.beetlego.model.Cloud;
import game.edw.beetlego.model.EndStation;
import game.edw.beetlego.model.Heart;
import game.edw.beetlego.model.Plane;
import game.edw.beetlego.model.Rocket;
import game.edw.beetlego.model.RocketB;
import game.edw.beetlego.model.Shelter;
import game.edw.beetlego.model.Shield;
import game.edw.beetlego.model.StartStation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements SensorEventListener{
	
	GameFrame gameFrame;
	Context context;
	Random rand = new Random();
	
	Paint grayPaint = new Paint();

	
	float posX;
	float posY;
	boolean isDown = false;
	
	Timer updateTimer;
	int mPeriod = 48;
	
	Plane plane;
//	Line line;
	Rocket rocketA;
	RocketB rocketB;
	Cloud cloud;
	Cactus cactus;
	Heart heart;
	ArrayList<Rocket> rocketAs = new ArrayList<Rocket>(10);
	ArrayList<RocketB> rocketBs = new ArrayList<RocketB>(10);
	ArrayList<Bang> bangs = new ArrayList<Bang>(10);
	
	ArrayList<Cloud> clouds = new ArrayList<Cloud>(5);
	ArrayList<Cactus> cactuses = new ArrayList<Cactus>(5);
	ArrayList<Heart> hearts = new ArrayList<Heart>(5);
	
	Background bg;
	
	StartStation startStation;
	EndStation endStation;
	
	Shield shield;
	Shelter shelter = null;
	
	public int shieldNum = 1;
	public int position = 0;
	public int state = C.GAME_PAUSE;
	
	int updateRocketA = C.ROCKETA_UPDATE;
	int updateRocketB = C.ROCKETB_UPDATE;
	int updateHeart = C.HEART_UPDATE;
	int updateCloud = C.CLOUD_UPDATE;
	int updateCactus = C.CACTUS_UPDATE;
	int updateInc = C.UPDATE_INC;
	
	long lastTime = System.currentTimeMillis();
	long currTime = System.currentTimeMillis();
	int fps;
	
	public GameView(Context context){
		super(context);
		this.context = context;
		
//		initGame();
//		startTimer();
	}
	
	public void setFrame(GameFrame gf){
		this.gameFrame = gf;
	}
	
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	protected void onDraw(Canvas canvas){
//		Canvas canvas = getHolder().lockCanvas();
		if(canvas == null) return;

		if(state == C.GAME_START){
			bg.draw(canvas);
			startStation.draw(canvas);
			plane.draw(canvas);
		}
		else if(state == C.GAME_END){
			bg.draw(canvas);
			endStation.draw(canvas);
			plane.draw(canvas);
			
			heart.draw(canvas);
			for(int i=0; i<hearts.size(); i++)
				hearts.get(i).draw(canvas);
			
			cactus.draw(canvas);
			for(int i=0; i<cactuses.size(); i++)
				cactuses.get(i).draw(canvas);
			
			cloud.draw(canvas);
			for(int i=0; i<clouds.size(); i++)
				clouds.get(i).draw(canvas);
			
			rocketA.draw(canvas);
			for(int i=0;i<rocketAs.size();i++)
				rocketAs.get(i).draw(canvas);
			rocketB.draw(canvas);
			for(int i=0;i<rocketBs.size();i++)
				rocketBs.get(i).draw(canvas);
			
			shield.draw(canvas);
//			shelter.draw(canvas);
		}
		else if(state == C.GAME_PLAY){
			bg.draw(canvas);
	//		line.draw(canvas);
			plane.draw(canvas);
			
			heart.draw(canvas);
			for(int i=0; i<hearts.size(); i++)
				hearts.get(i).draw(canvas);
			
			cactus.draw(canvas);
			for(int i=0; i<cactuses.size(); i++)
				cactuses.get(i).draw(canvas);
			
			cloud.draw(canvas);
			for(int i=0; i<clouds.size(); i++)
				clouds.get(i).draw(canvas);
			
			rocketA.draw(canvas);
			for(int i=0;i<rocketAs.size();i++)
				rocketAs.get(i).draw(canvas);
			rocketB.draw(canvas);
			for(int i=0;i<rocketBs.size();i++)
				rocketBs.get(i).draw(canvas);
			
			for(int i=0; i<bangs.size(); i++)
				bangs.get(i).draw(canvas);
			
			shield.draw(canvas);
			shelter.draw(canvas);
		}
		else if(state == C.GAME_PAUSE){
			bg.draw(canvas);
	//		line.draw(canvas);
			plane.draw(canvas);

			heart.draw(canvas);
			for(int i=0; i<hearts.size(); i++)
				hearts.get(i).draw(canvas);
			
			cactus.draw(canvas);
			for(int i=0; i<cactuses.size(); i++)
				cactuses.get(i).draw(canvas);
			
			cloud.draw(canvas);
			for(int i=0; i<clouds.size(); i++)
				clouds.get(i).draw(canvas);
			
			rocketA.draw(canvas);
			for(int i=0;i<rocketAs.size();i++)
				rocketAs.get(i).draw(canvas);
			rocketB.draw(canvas);
			for(int i=0;i<rocketBs.size();i++)
				rocketBs.get(i).draw(canvas);
			
			shield.draw(canvas);
			shelter.draw(canvas);
			
			canvas.drawRect(0, 0, C.SCR_W, C.SCR_H, grayPaint);
		}

		// show fps
//		currTime = System.currentTimeMillis();
//		fps = (int)(1000/(currTime - lastTime));
//		lastTime = currTime;
//		canvas.drawText("fps: " + fps, C.SCR_W, C.SCR_H, grayPaint);

	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				posX = event.getX();
				posY = event.getY();
				isDown = true;	// Log.d("DOWN X,Y", posX+ " , " + posY);
				break;
			case MotionEvent.ACTION_MOVE:
				posX = event.getX();
				posY = event.getY();
				isDown = true;	// Log.d("MOVE X,Y", posX+ " , " + posY);
				break;
			case MotionEvent.ACTION_UP:
				isDown = false;
				break;
			default:
				break;
		}
		return true;
	}
	
	public void startTimer(){
		updateTimer = new Timer();
		updateTimer.schedule(new UpdataTask(), 0, mPeriod);
	}
	
	public void stopTimer(){
		if(updateTimer!=null){
			try{
			updateTimer.cancel();
			updateTimer = null;
			System.gc();
			}catch(Exception e){;}
		}
	}

	private class UpdataTask extends TimerTask{
		@Override
		public void run() {
			//TODO
			if(state == C.GAME_START){
				startAnime();
				if(startStation.notExist()
					&& plane.canPlay()){
					state = C.GAME_PLAY;
					C.PLANE_POSX = (int) plane.X;	Log.d("ACC_X", C.ACC_X + "");
					updatePos();
				}
			}
			
			else if(state == C.GAME_PLAY){
				updatePos();
				updateBangs();
				checkCollision();
				updateInfo();
				updateItems();
				if(plane.Life <= 0){
					gameOver();
					updateTimer.cancel();
				}
				if(position >= C.STAGE_LENGTH){
					state = C.GAME_END;
				}
			}
			
			else if(state == C.GAME_END){
				endAnime();
				if(plane.hasLand(
						(int)(endStation.X + endStation.drawRect.width()/2),
						C.SCR_H/4*3-10)){
					gameWin();
					updateTimer.cancel();
				}
			}
			
			postInvalidate();
		}
	}
	
	public void updateItems(){
		if(position == updateRocketA){
			rocketAs.add(SceneManager.newRocket(C.SCR_W + rand.nextInt(C.SCR_W/2), rand.nextInt(C.SCR_H)));
			updateRocketA += C.ROCKETA_UPDATE + updateInc;
			updateInc += C.UPDATE_INC;
			Log.d("rocketA", rocketAs.size() + "  next: " + updateRocketA);
		}
		if(position == updateRocketB){
			rocketBs.add(SceneManager.newRocketB(C.SCR_W + rand.nextInt(C.SCR_W/2), rand.nextInt(C.SCR_H)));
			updateRocketB += C.ROCKETB_UPDATE + updateInc;
			Log.d("rocketB", rocketBs.size() + "  next: " + updateRocketB);
		}
		if(position == updateCloud){
			clouds.add(SceneManager.newCloud(C.SCR_W, 100 + rand.nextInt(50)));
			updateCloud += C.CLOUD_UPDATE + updateInc;
			Log.d("clouds", clouds.size() + "  next: " + updateCloud);
		}
		if(position == updateCactus){
			cactuses.add(SceneManager.newCactus(C.SCR_W*2 , C.SCR_H));
			updateCactus += C.CACTUS_UPDATE + updateInc;
			Log.d("cactuses", cactuses.size() + "  next: " + updateCactus);
		}
		if(position == updateHeart){
			hearts.add(SceneManager.newHeart(C.SCR_W*2, C.SCR_H/2));
			updateHeart += C.HEART_UPDATE + updateInc;
			updateInc += C.UPDATE_INC;
			Log.d("hearts", hearts.size() + "  next: " + updateHeart);
		}
	}
	
	public void updateInfo(){
		gameFrame.setLife(plane.Life);
		
		if(C.score>C.SCORE_MAX) C.score = C.SCORE_MAX;
		gameFrame.setScore(C.score * 10);
		
		updateSeekBar();
	}
	
	public void updateBangs(){
		for(int i=0; i<bangs.size(); i++)
			bangs.get(i).update();
	
		for(int i=bangs.size()-1; i>=0; i--)
			if(bangs.get(i).hasGone())
				bangs.remove(i);
	}
	
	public void endAnime(){
		bg.update();
		plane.endAnime();
		endStation.update();
		
		heart.endAnime();
		for(int i=0; i<hearts.size(); i++)
			hearts.get(i).endAnime();
		
		cloud.endAnime();
		for(int i=0; i<clouds.size(); i++)
			clouds.get(i).endAnime();
		
		cactus.endAnime();
		for(int i=0; i<cactuses.size(); i++)
			cactuses.get(i).endAnime();
		
		rocketA.endAnime();
		for(int i=0;i<rocketAs.size();i++)
			rocketAs.get(i).endAnime();
		rocketB.endAnime();
		for(int i=0;i<rocketBs.size();i++)
			rocketBs.get(i).endAnime();
		
		shield.update();
	}
	
	public void startAnime(){
		bg.update();
		plane.startAnime();
		startStation.update();
	}
	
	public void initGame(){
		C.score = 0;
		this.state = C.GAME_START;
		position = 0;
		shieldNum = 1;
		updateRocketA = C.ROCKETA_UPDATE;
		updateRocketB = C.ROCKETB_UPDATE;
		updateHeart = C.HEART_UPDATE;
		updateCloud = C.CLOUD_UPDATE;
		updateCactus = C.CACTUS_UPDATE;
		updateInc = C.UPDATE_INC;
		
		rocketAs = new ArrayList<Rocket>(10);
		rocketBs = new ArrayList<RocketB>(10);
		bangs = new ArrayList<Bang>(10);
		
		clouds = new ArrayList<Cloud>(5);
		cactuses = new ArrayList<Cactus>(5);
		hearts = new ArrayList<Heart>(5);
		
		grayPaint.setColor(0x80000000);
		grayPaint.setTextSize(20);
		grayPaint.setTextAlign(Align.RIGHT);
		
		// TODO
		plane = SceneManager.newPlane(C.SCR_W/3, 150);
//		line = new Line(bmpLine, plane.Y);
		bg = SceneManager.newBackground();
		cloud = SceneManager.newCloud(C.SCR_W, 200);
		cactus = SceneManager.newCactus(C.SCR_W*2 , C.SCR_H);
		heart = SceneManager.newHeart(C.SCR_W*2, C.SCR_H/2);
		
		rocketA = SceneManager.newRocket(C.SCR_W, 200);
		for(int i=0; i<C.ROCKETA_NUM; i++){
			rocketAs.add(SceneManager.newRocket(C.SCR_W + rand.nextInt(C.SCR_W/2), rand.nextInt(C.SCR_H)));
		}
		rocketB = SceneManager.newRocketB(C.SCR_W, 250);
		for(int i=0; i<C.ROCKETB_NUM; i++){
			rocketBs.add(SceneManager.newRocketB(C.SCR_W + rand.nextInt(C.SCR_W/2), rand.nextInt(C.SCR_H)));
		}
		startStation = SceneManager.newStartStation();
		endStation = SceneManager.newEndStation();
		
		shield = SceneManager.newShield(-100, 0);
		shelter = SceneManager.newShelter();
		
		plane.X = startStation.drawRect.right - plane.drawRect.width();
		plane.Y = startStation.drawRect.top + startStation.drawRect.height()/2;
		plane.Life = C.PLANE_LIFE;
	}
	
	public void touchUpdate(){
		if(!isDown){
			plane.Acc = 0;
			if(plane.SpeedY > C.ZERO) plane.SpeedY -= C.PLANE_DEC;
			if(plane.SpeedY < -C.ZERO) plane.SpeedY += C.PLANE_DEC;
//			Log.d("plane SpeedY", "" + plane.SpeedY);
			return;
		}
		
		if(posY < plane.Y + plane.drawRect.height()/2){
			plane.Acc = -C.PLANE_ACC;
			if(plane.SpeedY > 0) plane.Acc -=C.PLANE_DEC;
		}
		else if(posY > plane.Y + plane.drawRect.height()/2){
			plane.Acc = C.PLANE_ACC;
			if(plane.SpeedY < 0) plane.Acc +=C.PLANE_DEC;
		}
	}
	
	public void updateSeekBar(){
		if(position % 10 == 0 && C.STAGE_ID > 0)
			gameFrame.setSeekBar(position);
	}
	
	public void updatePos(){
		position ++;
		
		touchUpdate();
		
		bg.update();
		plane.update();
//		line.update();
		heart.update();
		for(int i=0; i<hearts.size(); i++)
			hearts.get(i).update();
		
		cloud.update();
		for(int i=0; i<clouds.size(); i++)
			clouds.get(i).update();
		
		cactus.update();
		for(int i=0; i<cactuses.size(); i++)
			cactuses.get(i).update();
		
		rocketA.update((int)plane.Y);
		for(int i=0;i<rocketAs.size();i++)
			rocketAs.get(i).update((int)plane.Y);
		rocketB.update((int)plane.Y);
		for(int i=0;i<rocketBs.size();i++)
			rocketBs.get(i).update((int)plane.Y);
		
		shield.update();
		shelter.update(plane.drawRect.centerX(), plane.drawRect.centerY());
	}
	
	public void checkCollision(){
		if(Rect.intersects(shield.drawRect, plane.collisionRect)){
			addShield();
		}
		
		if(Rect.intersects(cloud.collisionRect, plane.collisionRect)){
			hitCloud(cloud);
		}
		for(int i=0; i<clouds.size(); i++){
			if(Rect.intersects(clouds.get(i).collisionRect, plane.collisionRect)){
				hitCloud(clouds.get(i));
			}
		}
		
		if(Rect.intersects(cactus.collisionRect, plane.collisionRect)){
			hitCactus(cactus);
		}
		for(int i=0; i<cactuses.size(); i++){
			if(Rect.intersects(cactuses.get(i).collisionRect, plane.collisionRect)){
				hitCactus(cactuses.get(i));
			}
		}
		
		if(Rect.intersects(heart.drawRect, plane.collisionRect)){
			hitHeart(heart);
		}
		for(int i=0; i<hearts.size(); i++){
			if(Rect.intersects(hearts.get(i).drawRect, plane.collisionRect)){
				hitHeart(hearts.get(i));
			}
		}
				
		if(shelter.enable){
			if(Rect.intersects(rocketA.collisionRect, shelter.hitRect)){
				hitRocketA(rocketA);
			}
			for(int i=0;i<rocketAs.size();i++){
				if(Rect.intersects(rocketAs.get(i).collisionRect, shelter.hitRect)){
					hitRocketA(rocketAs.get(i));
				}
			}
			
			if(Rect.intersects(rocketB.collisionRect, shelter.hitRect)){
				hitRocketB(rocketB);
			}
			for(int i=0;i<rocketBs.size();i++){
				if(Rect.intersects(rocketBs.get(i).collisionRect, shelter.hitRect)){
					hitRocketB(rocketBs.get(i));
				}
			}
		}
		else{
			if(Rect.intersects(rocketA.collisionRect, plane.collisionRect)){
				hitRocketA(rocketA);
			}
			for(int i=0;i<rocketAs.size();i++){
				if(Rect.intersects(rocketAs.get(i).collisionRect, plane.collisionRect)){
					hitRocketA(rocketAs.get(i));
				}
			}
			
			if(Rect.intersects(rocketB.collisionRect, plane.collisionRect)){
				hitRocketB(rocketB);
			}
			for(int i=0;i<rocketBs.size();i++){
				if(Rect.intersects(rocketBs.get(i).collisionRect, plane.collisionRect)){
					hitRocketB(rocketBs.get(i));
				}
			}
		}
	}
	
	public void addShield(){
		if(shieldNum < C.SHIELD_MAXNUM){
			shieldNum ++;
			shield.reset();
			gameFrame.setShield(shieldNum);
			Log.d("SHIELD", "" + shieldNum);
		}
	}
	
	public void enableShelter(){
		if(shieldNum <= 0) return;
		shieldNum --;
//		shield.reset();
		shelter.enable = true;
		shelter.update(plane.drawRect.centerX(), plane.drawRect.centerY());
		SoundManager.play("shelter");
	}
	
	public void hitRocketA(Rocket rocketA){
		if(!shelter.enable) plane.Life --;
		rocketA.reset((int)plane.Y + plane.drawRect.height()/2);
		C.score ++;
		bangs.add(SceneManager.newBang(rocketA.collisionRect.left, rocketA.collisionRect.centerY()));
		SoundManager.play("bang0");
	}
	
	public void hitRocketB(RocketB rocketB){
		if(!shelter.enable) plane.Life -= 2;
		rocketB.reset((int)plane.Y + plane.drawRect.height()/2);
		C.score ++;
		bangs.add(SceneManager.newBang(rocketB.collisionRect.left, rocketB.collisionRect.centerY()));
		SoundManager.play("bang1");
	}
	
	public void hitHeart(Heart heart){
		plane.Life += C.HEART_ADD_LIFE;
		if(plane.Life > C.PLANE_MAXLIFE) plane.Life = C.PLANE_MAXLIFE;
		heart.reset();
		C.score +=2;
	}
	
	public void hitCloud(Cloud cloud){
		plane.Life -= 2;
		cloud.reset();
		C.score ++;
		bangs.add(SceneManager.newBang(cloud.collisionRect.left, cloud.collisionRect.centerY()));
		SoundManager.play("thunder");
	}
	
	public void hitCactus(Cactus cactus){
		plane.Life -= 1;
//		cactus.reset();
		C.score ++;
		bangs.add(SceneManager.newBang(cactus.collisionRect.centerX(), cactus.collisionRect.top));
		SoundManager.play("bang1");
	}
	
	public void gameOver(){
//		gameFrame.showMsg("GameOver");
		gameFrame.gameOver();
	}
	
	public void gameWin(){
//		gameFrame.showMsg("You WIN !!!");
		gameFrame.gameWin();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) { }

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized(this){
			if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
				double accX = event.values[0];
				double accY = event.values[1];
				double accZ = event.values[2];
				double magnitude = Math.sqrt(accX*accX + accY*accY + accZ*accZ);
				double cosX = accX/magnitude;
				double cosY = accY/magnitude;
//				double cosZ = accZ/magnitude;
				
				if(state == C.GAME_START){
					C.ACC_X = (C.ACC_X + cosX)/2;	// У׼Ĭ��״̬
					C.ACC_Y = (C.ACC_Y + cosY)/2;	
				}
				if(state == C.GAME_PLAY){
					plane.Y += (cosX-C.ACC_X)*C.ACC_X_FACTOR;
					plane.X += (cosY-C.ACC_Y)*C.ACC_Y_FACTOR;
					int halfWidth = plane.drawRect.width()/3;
					if(plane.X > C.PLANE_POSX + halfWidth) plane.X = C.PLANE_POSX + halfWidth;
					if(plane.X < C.PLANE_POSX - halfWidth) plane.X = C.PLANE_POSX - halfWidth;
					
					int halfHeight = plane.drawRect.height()/2;
					if(plane.Y > C.SCR_H - halfHeight) plane.Y = C.SCR_H - halfHeight;
					if(plane.Y < -halfHeight) plane.Y = -halfHeight;
				}
			}
		}
	}
	
	public void release(){
		BitmapManager.release();
		SoundManager.release();
		Log.d("BitmapManager", "released");
	}

}
