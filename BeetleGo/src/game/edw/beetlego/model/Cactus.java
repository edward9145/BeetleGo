package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Cactus {
	public float X = 0;
	public float Y = 0;
	public float Speed = C.CACTUS_SPEEDX;
	public float Acc = 0.05f;
	public int ID = 0;
	public Rect bmpRect = new Rect(0,0,42,98);
	public Rect drawRect = new Rect(0,0,64,150);
	public Rect collisionRect = new Rect(0,0,60,140);

	public Bitmap bmp;
	
	public Cactus(float x, float y){
		this.X = x;
		this.Y = y;
	}
	
	public Cactus(Bitmap bmp, float x, float y){
		this.X = x;
		this.Y = y;
		this.bmp = bmp;
		bmpRect = new Rect(0,0, bmp.getWidth(), bmp.getHeight());
		update();
	}
	
	public void draw(Canvas canvas){
		if(Rect.intersects(drawRect, C.SCR_RECT))
			canvas.drawBitmap(bmp, bmpRect, drawRect, null);
		//canvas.drawLine(collisionRect.left, collisionRect.top, collisionRect.right, collisionRect.bottom, new Paint());
	}

	public void update(){
		X -= Speed;
		Speed += Acc;
		if(X < -drawRect.width()){
			reset();
		}
		drawRect.offsetTo((int)X, (int)Y);
		collisionRect.offsetTo((int)X+2, (int)Y+10);
	}
	
	public void reset(){
		Random rand = new Random();
		X = 2*C.SCR_W + rand.nextInt(1)*C.SCR_W + rand.nextInt(C.SCR_W);
		Y = C.SCR_H - drawRect.height() 
			+ new Random().nextInt(drawRect.height()/5);
		Speed = C.CACTUS_SPEEDX;
	}
	
	public void endAnime(){
		if(X > -drawRect.width() && X < C.SCR_W)
			update();
	}
	
//	public static void release(){
//		if(bmp == null) return;
//		bmp.recycle();
//		bmp = null;
//	}
}
