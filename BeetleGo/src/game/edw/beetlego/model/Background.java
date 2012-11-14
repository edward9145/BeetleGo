package game.edw.beetlego.model;

import game.edw.beetlego.C;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
	
	public Random rand = new Random();
	public float X = 0;
	public float Y = 0;
	public int ID = 0;
	public Rect bmpRect0 = new Rect(0,0,640,320);
	public Rect drawRect0 = new Rect(0,0,C.SCR_W,C.SCR_H);
	public Rect bmpRectGrass = new Rect(0,0,640,81);
	public Rect drawRectGrass = new Rect(0,0,C.SCR_W, C.SCR_H/5);
	public Rect bmpRectCloud = new Rect(0,0,640,320);
	public Rect drawRectCloud = new Rect(0,0,C.SCR_W, C.SCR_H);
	
	public static int BMP_NUM = 4;
	public static Bitmap []bmp = new Bitmap[BMP_NUM];
	
	public int GRASS_ID = 0;
	public static int GRASS_NUM = 2;
	public static Bitmap []bmpGrass = new Bitmap[GRASS_NUM];
	
	public static int CLOUD_NUM = 2;
	public static Bitmap[] bmpCloud = new Bitmap[CLOUD_NUM];
	public static float[] cloudSpeed = {1, 4, 4};
	public static float[] cloudX = {C.SCR_W, C.SCR_W, C.SCR_W};
	
	public Rect[] bmpCloudLeft = new Rect[CLOUD_NUM];
	public Rect[] drawCloudLeft = new Rect[CLOUD_NUM];
	public Rect[] bmpCloudRight = new Rect[CLOUD_NUM];
	public Rect[] drawCloudRight = new Rect[CLOUD_NUM];
	
	
	
	public Background(Bitmap[] bg, Bitmap[] grass, Bitmap[] cloud){
		bmp = bg;
		bmpGrass = grass;
		bmpCloud = cloud;
		
		bmpRect0 = new Rect(50,0, bmp[0].getWidth()-50, bmp[0].getHeight());
		bmpRectGrass = new Rect(0, 0, 700, bmpGrass[0].getHeight());
		drawRectGrass.offsetTo(0, C.SCR_H-C.SCR_H/5);
		
		bmpRectCloud = new Rect(0, 0, bmpCloud[0].getWidth(), bmpCloud[0].getHeight());
		drawRectCloud = new Rect(0, 0, C.SCR_W, C.SCR_W * bmpCloud[0].getHeight() / bmpCloud[0].getWidth());
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bmp[ID/3], bmpRect0, drawRect0, null);
		canvas.drawBitmap(bmpGrass[GRASS_ID/3], bmpRectGrass, drawRectGrass, null);
		
	
//		canvas.drawBitmap(bmpCloud[0], bmpRectCloud, drawRectCloud, null);
		
//¿¨Âí¿Ë¾íÖá !!
		for(int i=0; i<CLOUD_NUM; i++){
			canvas.drawBitmap(bmpCloud[i], bmpCloudRight[i], drawCloudLeft[i], null);
			canvas.drawBitmap(bmpCloud[i], bmpCloudLeft[i], drawCloudRight[i], null);
		}
		
	}
	
	public void update(){
		ID ++;
		ID %= BMP_NUM*3;
		
		GRASS_ID ++;
		GRASS_ID %= GRASS_NUM*3;
		
		bmpRectGrass.offsetTo(rand.nextInt(200), 0);
		
		for(int i=0; i<CLOUD_NUM; i++){
			cloudX[i] -= cloudSpeed[i];
			
			if(cloudX[i]<0) 
				cloudX[i] = C.SCR_W;
			
			drawCloudLeft[i] = new Rect(0, 0, (int)cloudX[i], drawRectCloud.height());
			drawCloudRight[i] = new Rect((int)cloudX[i], 0, C.SCR_W, drawRectCloud.height());
			bmpCloudLeft[i] = new Rect(0, 0, (int)((C.SCR_W-cloudX[i])*bmpRectCloud.width()/C.SCR_W) , bmpRectCloud.height());
			bmpCloudRight[i] = new Rect((int)((C.SCR_W-cloudX[i])*bmpRectCloud.width()/C.SCR_W), 0, bmpRectCloud.width(), bmpRectCloud.height());
		}
	}
	
	public static void release(){
		if(bmp == null) return;
		for(int i=0; i<BMP_NUM; i++){
			bmp[i].recycle();
			bmp[i] = null;
		}
		bmp = null;
		
		for(int i=0; i<GRASS_NUM; i++){
			bmpGrass[i].recycle();
			bmpGrass[i] = null;
		}
		bmpGrass = null;
		
		for(int i=0; i<CLOUD_NUM; i++){
			bmpCloud[i].recycle();
			bmpCloud[i] = null;
		}
		bmpCloud = null;
	}
}
