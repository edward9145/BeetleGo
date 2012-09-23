package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Heart {
	public float X = 0;
	public float Y = 0;
	public float SpeedX = C.HEART_SPEEDX;
	public float SpeedY = C.HEART_SPEEDY;
	public int SignY = 1;
	public float Acc = 0.1f;
	public float AccY = 1f;
	public int ID = 0;
	public Rect bmpRect = new Rect(0,0,30,28);
	public Rect drawRect = new Rect(0,0,40,40);
	public static Bitmap bmp;
	
	public Heart(float x, float y){
		this.X = x;
		this.Y = y;
	}
	
	public Heart(Bitmap bmp, float x, float y){
		this.X = x;
		this.Y = y;
		this.bmp = bmp;
		bmpRect = new Rect(0,0, bmp.getWidth(), bmp.getHeight());
		update();
	}
	
	public void draw(Canvas canvas){
		if(Rect.intersects(drawRect, C.SCR_RECT))
			canvas.drawBitmap(bmp, bmpRect, drawRect, null);
	}

	public void update(){
		X -= SpeedX;
		SpeedX += Acc;
		SpeedY += (SignY)*AccY;
		Y += SpeedY;
		
		if(SpeedY > C.HEART_SPEEDY
		|| SpeedY < -C.HEART_SPEEDY)
			SignY *= -1;
		
		if(X < -drawRect.width()){
			reset();
		}
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public void update(int centerY){
		X -= SpeedX;
		SpeedX += Acc;
		if(X < -drawRect.width()){
			reset(centerY);
		}
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public void reset(){
		Random rand = new Random();
		X = C.SCR_W + rand.nextInt(1)*C.SCR_W + rand.nextInt(C.SCR_W);
		Y = C.SCR_H/10 + new Random().nextInt(C.SCR_H/5*4);
		SpeedX = C.HEART_SPEEDX;
	}
	
	public void reset(int center){
		Random rand = new Random();
		X = rand.nextInt(3)*C.SCR_W + rand.nextInt(C.SCR_W);
		int sign = (rand.nextInt()%2==0) ? 1:-1;
		Y = center + sign * rand.nextInt(200);
		SpeedX = C.HEART_SPEEDX;
	}
	
	public void endAnime(){
		if(X > -drawRect.width() && X < C.SCR_W)
			update();
	}
}
