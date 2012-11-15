package game.edw.beetlego.model;

import game.edw.beetlego.C;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class BasicSprite implements IDrawable{
	
	public float X = 0;
	public float Y = 0;
	
	public int ID = 0;
	public Rect hitRect = null;
	public Rect drawRect;
	
	Bitmap bmp = null;
	Rect[] bmpRect = null;
	
	public BasicSprite(Bitmap bmp, int rectNum, Rect drawRect){
		this.X = -bmp.getWidth();
		this.Y = -bmp.getHeight();
		this.bmp = bmp;
		this.bmpRect = new Rect[rectNum];
		int rectWidth = bmp.getWidth()/rectNum;
		int rectHeight = bmp.getHeight();
		for(int i=0; i<rectNum; i++){
			bmpRect[i] = new Rect(i*rectWidth, 0, (i+1)*rectWidth, rectHeight);
		}
		this.drawRect = drawRect;
		
		Log.d("Sprite", bmp.getWidth() + "," + bmp.getHeight());
		for(int i=0; i<bmpRect.length; i++)
			Log.d("Sprite bmpRect", bmpRect[i].left + "," + bmpRect[i].top  + "," + bmpRect[i].right  + "," + bmpRect[i].bottom);
		Log.d("Sprite drawRect", drawRect.left + "," + drawRect.top  + "," + drawRect.right  + "," + drawRect.bottom);
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(Rect.intersects(drawRect, C.SCR_RECT))
			canvas.drawBitmap(bmp, bmpRect[ID], drawRect, null);
		
//		if(hitRect != null) canvas.drawLine(hitRect.left, hitRect.top, hitRect.right, hitRect.bottom, new Paint());
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

}
