package game.edw.beetlego.model;

import game.edw.beetlego.C;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Plane {
	public float X = 0;
	public float Y = 0;
	public int ID = 0;
	public int Life = C.PLANE_LIFE;
	public float SpeedX = 0;
	public float SpeedY = 0;
	public float Acc = 0;
	
	public Rect bmpRect = new Rect(0,0,118,109);
	public Rect drawRect = new Rect(0,0,130,120);
	public Rect collisionRect = new Rect(0,0,80,52);
	public static int BMP_NUM = 3;
	public static Bitmap []bmp = new Bitmap[BMP_NUM];
	
	public Plane(float x, float y){
		this.X = x;
		this.Y = y;
	}
	
	public Plane(Bitmap []bmp, float x, float y){
		this.X = x;
		this.Y = y;
		this.bmp = bmp;
		bmpRect = new Rect(0,0, bmp[0].getWidth(), bmp[0].getHeight());
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bmp[ID], bmpRect, drawRect, null);
		//canvas.drawLine(collisionRect.left, collisionRect.top, collisionRect.right, collisionRect.bottom, new Paint());
	}
	
	public void update(){
		ID ++;
		ID %= BMP_NUM;
		
		SpeedY += Acc;
		if(SpeedY > 0 && SpeedY > C.PLANE_SPEEDY_MAX) SpeedY = C.PLANE_SPEEDY_MAX;
		if(SpeedY < 0 && SpeedY < -C.PLANE_SPEEDY_MAX) SpeedY = -C.PLANE_SPEEDY_MAX;
		Y += SpeedY;
		
		drawRect.offsetTo((int)X, (int)Y);
		collisionRect.offsetTo((int)X + 38, (int)Y + 50);
	}
	
	public void startAnime(){
		if( Y > C.SCR_H/4)
			Y -= 3;
		if(X > C.SCR_W/5)
			X -= 3;
		ID ++;
		ID %= BMP_NUM;
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public boolean canPlay(){
		return X <= C.SCR_W/5;
	}
	
	public void endAnime(){
		if( Y < C.SCR_H/4 *3)
			Y += 4;
		else if( Y > C.SCR_H/4 *3)
			Y -= 4;
		
		if(X < C.SCR_W - 50)
			X += 5;
		ID ++;
		ID %= BMP_NUM;
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public boolean hasLand(int endX, int endY){
		return (X >= endX) && (Y > endY);
	}
	
	public static void release(){
		if(bmp == null) return;
		for(int i=0; i<BMP_NUM; i++){
			bmp[i].recycle();
			bmp[i] = null;
		}
		bmp = null;
	}
}
