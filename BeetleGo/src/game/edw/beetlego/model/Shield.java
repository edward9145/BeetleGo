package game.edw.beetlego.model;

import java.util.Random;

import game.edw.beetlego.C;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Shield extends BasicSprite{
	
	public float Radius = C.SHIELD_SPEED_RADIUS;
	public float SpeedX = C.SHIELD_SPEEDX;
	public float Shake = C.SHIELD_SPEED_SHAKE;
	public float SpeedAngle = C.SHIELD_SPEED_ANGLE;
	Random rand = new Random();
	float theta = 0;
	float centerX;
	float centerY;
		
	public Shield(Bitmap bmp, int rectNum, Rect drawRect, float x, float y) {
		super(bmp, rectNum, drawRect);
		this.centerX = x;
		this.centerY = y;
	}
	
	public void update(){
		theta += C.SHIELD_SPEED_ANGLE;
		centerX -= SpeedX;
		X = centerX + (float)Math.cos(theta) * Radius;
		if(rand.nextInt()%2 == 0) centerY += Shake;
		else centerY -= Shake;
		Y = centerY + (float)Math.sin(theta) * Radius;
		
		if(X < -drawRect.width()){
			reset();
		}
		drawRect.offsetTo((int)X, (int)Y);
	}
	
	public void reset(){
		centerX = 2*C.SCR_W + rand.nextInt(2)*C.SCR_W + rand.nextInt(C.SCR_W);	// X = (2+rand.nextInt(2))*C.SCR_W + rand.nextInt(C.SCR_W);
		int sign = (rand.nextInt()%2==0) ? 1:-1;
		centerY = C.SCR_H/2 + sign * rand.nextInt(C.SCR_H/4);
		SpeedX = C.SHIELD_SPEEDX;
	}
}
