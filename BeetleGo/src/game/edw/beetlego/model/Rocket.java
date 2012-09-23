package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Rocket {
	public float X = 0;
	public float Y = 0;
	public float Speed = C.ROCKETA_SPEEDX;
	public float Acc = 0.2f;
	public int ID = 0;
	public Rect bmpRect = new Rect(0,0,74,32);
	public Rect drawRect = new Rect(0,0,80,37);
	public Rect collisionRect = new Rect(0,0,60,37);
	public static int BMP_NUM = 3;
	public static Bitmap []bmp = new Bitmap[3];
	
	public Rocket(float x, float y){
		this.X = x;
		this.Y = y;
	}
	
	public Rocket(Bitmap []bmp, float x, float y){
		this.X = x;
		this.Y = y;
		this.bmp = bmp;
		bmpRect = new Rect(0,0, bmp[0].getWidth(), bmp[0].getHeight());
		update();
	}
	
	public void draw(Canvas canvas){
		if(Rect.intersects(drawRect, C.SCR_RECT))
			canvas.drawBitmap(bmp[ID], bmpRect, drawRect, null);
	}

	public void update(){
		ID ++;
		ID %= BMP_NUM;
		X -= Speed;
		Speed += Acc;
		if(X < -drawRect.width()){
			X = C.SCR_W;
			Y = new Random().nextInt(50) + 80;
			Speed = 10;
		}
		drawRect.offsetTo((int)X, (int)Y);
		collisionRect.offsetTo((int)X, (int)Y);
	}
	
	public void update(int centerY){
		ID ++;
		ID %= BMP_NUM;
		X -= Speed;
		Speed += Acc;
		if(X < -drawRect.width()){
			reset(centerY);
		}
		drawRect.offsetTo((int)X, (int)Y);
		collisionRect.offsetTo((int)X, (int)Y);
	}
	
	public void reset(){
		X = C.SCR_W;
		Y = new Random().nextInt(50) + 200;
		Speed = C.ROCKETA_SPEEDX;
	}
	
	public void reset(int center){
		Random rand = new Random();
		X = C.SCR_W + rand.nextInt(C.SCR_W/2);
		int sign = (rand.nextInt()%2==0) ? 1:-1;
		Y = center + sign * rand.nextInt(200);
		Speed = C.ROCKETA_SPEEDX;
	}
	
	public void endAnime(){
		if(X > -drawRect.width() && X < C.SCR_W)
			update();
	}
}
