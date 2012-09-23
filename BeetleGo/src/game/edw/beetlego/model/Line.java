package game.edw.beetlego.model;

import game.edw.beetlego.C;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Line {
	public float X = 0;
	public float Y = 0;
	public int ID = 0;
	public Rect bmpRect = new Rect(0,0,683,37);
	public Rect drawRect = new Rect(0,0,C.SCR_W,40);
	public static int BMP_NUM = 3;
	public static Bitmap bmp;
	
	public Line(float y){
		this.Y = y;
	}
	
	public Line(Bitmap bmp, float y){
		this.Y = y;
		this.bmp = bmp;
		
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bmp, bmpRect, drawRect, null);
	}
	
	public void update(){
		
		drawRect.offsetTo((int)X, (int)Y);
	}
	
}
