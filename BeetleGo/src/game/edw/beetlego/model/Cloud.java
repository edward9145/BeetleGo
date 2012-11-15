package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Cloud {
	public float X = 0;
	public float Y = 0;
	public float Speed = C.CLOUD_SPEEDX;
	public float Acc = 0.08f;
	public int ID = 0;
	public Rect bmpRect = new Rect(0,0,85,95);
	public Rect drawRect = new Rect(0,0,100,112);
	public Rect collisionRect = new Rect(0,0,32,48);
	public static int BMP_NUM = 3;
	public Bitmap []bmp = new Bitmap[3];
	
	public Cloud(float x, float y){
		this.X = x;
		this.Y = y;
	}
	
	public Cloud(Bitmap []bmp, float x, float y){
		this.X = x;
		this.Y = y;
		this.bmp = bmp;
		bmpRect = new Rect(0,0, bmp[0].getWidth(), bmp[0].getHeight());
		update();
	}
	
	public void draw(Canvas canvas){
		if(Rect.intersects(drawRect, C.SCR_RECT))
			canvas.drawBitmap(bmp[ID/2], bmpRect, drawRect, null);
		//canvas.drawLine(collisionRect.left, collisionRect.top, collisionRect.right, collisionRect.bottom, new Paint());
	}

	public void update(){
		ID ++;
		ID %= (BMP_NUM*2);
		X -= Speed;
		Speed += Acc;
		if(X < -drawRect.width()){
			reset();
		}
		drawRect.offsetTo((int)X, (int)Y);
		collisionRect.offsetTo((int)X+34, (int)Y+56);
	}
	
	public void update(int centerY){
		ID ++;
		ID %= (BMP_NUM*2);
		X -= Speed;
		Speed += Acc;
		if(X < -drawRect.width()){
			reset(centerY);
		}
		drawRect.offsetTo((int)X, (int)Y);
		collisionRect.offsetTo((int)X+34, (int)Y+56);
	}
	
	public void reset(){
		Random rand = new Random();
		X = C.SCR_W + rand.nextInt(1)*C.SCR_W + rand.nextInt(C.SCR_W);
		Y = new Random().nextInt(C.SCR_H/2) - drawRect.height()/2;
		Speed = C.CLOUD_SPEEDX;
	}
	
	public void reset(int center){
		Random rand = new Random();
		X = C.SCR_W + rand.nextInt(C.SCR_W/2);
		int sign = (rand.nextInt()%2==0) ? 1:-1;
		Y = center + sign * rand.nextInt(200);
		Speed = C.CLOUD_SPEEDX;
	}
	
	public void endAnime(){
		if(X > -drawRect.width() && X < C.SCR_W)
			update();
	}
	
//	public static void release(){
//		if(bmp == null) return;
//		for(int i=0; i<BMP_NUM; i++){
//			bmp[i].recycle();
//			bmp[i] = null;
//		}
//		bmp = null;
//	}
}
