package game.edw.beetlego.model;

import java.util.Random;

import game.edw.beetlego.C;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Shield extends BasicSprite{
	
	public float SpeedX = C.SHIELD_SPEEDX;
	public float SpeedY = C.SHIELD_SPEEDY;
	Random rand = new Random();
		
	public Shield(Bitmap bmp, int rectNum, Rect drawRect, float x, float y) {
		super(bmp, rectNum, drawRect);
		this.X = x;
		this.Y = y;
	}
	
	public void update(){
		X -= SpeedX;
		if(rand.nextInt()%2 == 0) Y += SpeedY;
		else Y -= SpeedY;
		
		if(X < -drawRect.width()){
			reset();
		}
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public void reset(){
		X = 2*C.SCR_W + rand.nextInt(2)*C.SCR_W + rand.nextInt(C.SCR_W);	// X = (2+rand.nextInt(2))*C.SCR_W + rand.nextInt(C.SCR_W);
		int sign = (rand.nextInt()%2==0) ? 1:-1;
		Y = C.SCR_H/2 + sign * rand.nextInt(C.SCR_H/4);
		SpeedX = C.SHIELD_SPEEDX;
		SpeedY = C.SHIELD_SPEEDY + rand.nextInt(2);
	}
}
