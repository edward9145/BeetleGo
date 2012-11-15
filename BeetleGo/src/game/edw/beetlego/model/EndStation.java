package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class EndStation {
	
	public Random rand = new Random();
	public float X = 0;
	public float Y = 0;
	public float SpeedX = 4;
	public int ID = 0;
	public Rect bmpRect = new Rect(0,0,640,320);
	public Rect drawRect = new Rect(0,0, 150, 225);
	
	public Bitmap bmp;
	
	public EndStation(Bitmap b){
		bmp = b;
		bmpRect = new Rect(0,0, bmp.getWidth(), bmp.getHeight());
		drawRect = new Rect(0, 0, C.SCR_H/3*2*bmp.getWidth()/bmp.getHeight(), C.SCR_H/3*2);
		X = C.SCR_W;
		Y = C.SCR_H - drawRect.height();
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bmp, bmpRect, drawRect, null);
	}
	
	public void update(){
		if(X > C.SCR_W - drawRect.width()){
			X -= SpeedX;
			drawRect.offsetTo((int)X, (int)Y);
		}
	}
	
//	public static void release(){
//		if(bmp == null) return;
//		bmp.recycle();
//		bmp = null;
//	}
}
