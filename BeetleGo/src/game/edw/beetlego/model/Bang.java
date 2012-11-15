package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bang {
	
	public float X = 0;
	public float Y = 0;
	
	public int bangTime = 10;
	public int ID = 0;	
	public Rect bmpRect = new Rect(0,0,50,50);
	public Rect drawRect = new Rect(0,0,45,45);
	
	public static int BMP_NUM = 3;
	public Bitmap []bmp = new Bitmap[BMP_NUM];
		
	public Bang(Bitmap[] bmp, float x, float y){
		this.ID = new Random().nextInt(3);
		this.X = x;
		this.Y = y;
		this.bmp = bmp;
		bmpRect = new Rect(0,0, bmp[0].getWidth(), bmp[0].getHeight());
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public void draw(Canvas canvas){
		if(Rect.intersects(drawRect, C.SCR_RECT))
			canvas.drawBitmap(bmp[ID], bmpRect, drawRect, null);
	}

	public void update(){
		bangTime --;
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public boolean hasGone(){
		return bangTime <=0 ;
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
