package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class StartStation {
	
	public Random rand = new Random();
	public float X = 0;
	public float Y = 0;
	public float SpeedX = 5;
	public int ID = 0;
	public Rect bmpRect = new Rect(0,0,640,320);
	public Rect drawRect = new Rect(0,0, 150, 225);
	
	public static Bitmap bmp;
	
	public StartStation(Bitmap b){
		bmp = b;
		bmpRect = new Rect(0,0, bmp.getWidth(), bmp.getHeight());
		drawRect = new Rect(0, 0, C.SCR_H/3*2*bmp.getWidth()/bmp.getHeight(), C.SCR_H/3*2);
		X = C.SCR_W/4;
		Y = C.SCR_H - drawRect.height();
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bmp, bmpRect, drawRect, null);
	}
	
	public void update(){
		X -= SpeedX;
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public boolean notExist(){
		return X < -drawRect.width();
	}
}
