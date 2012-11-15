package game.edw.beetlego.model;

import game.edw.beetlego.C;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Shelter extends BasicSprite{
	
	public boolean enable = false;
	public int time = C.SHELTER_TIME;
	
	public Shelter(Bitmap bmp, int rectNum, Rect drawRect) {
		super(bmp, rectNum, drawRect);
		hitRect = new Rect(0, 0, 120, 80);
	}
	
	public void draw(Canvas canvas){
		if(!enable) return;
		super.draw(canvas);
	}
	
	public void update(float x, float y){
		if(!enable) return;
		
		time --;
		if(time < C.SHELTER_TIME/5)
			ID = (time/2)%2 + 1;
		else
			ID = (time/2)%2;
		
		X = x-drawRect.width()/2;
		Y = y-drawRect.height()/2;
		drawRect.offsetTo((int)X + 6, (int)Y + 14);
		hitRect.offsetTo((int)X + 25, (int)Y + 25);
		
		if(time <= 0){
			reset();
		}
	}
	
	public void reset(){
		time = C.SHELTER_TIME;
		enable = false;
	}
}
