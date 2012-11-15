package game.edw.beetlego.manager;

import game.edw.beetlego.R;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class BitmapManager {

	public static Bitmap bmpPlane[] = new Bitmap[3];
	public static Bitmap bmpBG[] = new Bitmap[4];
	public static Bitmap bmpGrass[] = new Bitmap[2];
	public static Bitmap bmpBgCloud[] = new Bitmap[2];
	public static Bitmap bmpCloud[] = new Bitmap[3];
//	public static Bitmap bmpLine;
	public static Bitmap bmpRocketA[] = new Bitmap[3];
	public static Bitmap bmpRocketB[] = new Bitmap[3];
	public static Bitmap bmpCactus;
	public static Bitmap bmpBangs[] = new Bitmap[3];
	public static Bitmap bmpHeart;	
	public static Bitmap bmpStart, bmpEnd;
	
	public static Bitmap bmpShield, bmpShelter;
	
	public static LinkedList<Bitmap> bmpList = new LinkedList<Bitmap>();
	
	public static void loadBitmaps(Context context) {
		bmpShield = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.shield);
		bmpShelter = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.shelter);
		bmpList.add(bmpShield);
		bmpList.add(bmpShelter);
		
		bmpBangs[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.bang0);
		bmpBangs[1] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.bang1);
		bmpBangs[2] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.bang2);
		bmpList.add(bmpBangs[0]);
		bmpList.add(bmpBangs[1]);
		bmpList.add(bmpBangs[2]);
		
		bmpPlane[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.plane0);
		bmpPlane[1] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.plane1);
		bmpPlane[2] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.plane2);
		bmpList.add(bmpPlane[0]);
		bmpList.add(bmpPlane[1]);
		bmpList.add(bmpPlane[2]);
		
//		bmpLine = BitmapFactory.decodeResource(
//				context.getResources(), R.drawable.line);
//		bmpList.add(bmpLine);
		
		bmpGrass[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.grass0);
		bmpGrass[1] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.grass1);
		bmpBgCloud[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.bgcloud2_0);
		bmpBgCloud[1] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.bgcloud2_1);
		bmpBG[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.background0);
		bmpBG[1] = BitmapFactory.decodeResource(
					context.getResources(), R.drawable.background1);
		bmpBG[2] = BitmapFactory.decodeResource(
					context.getResources(), R.drawable.background2);
		bmpBG[3] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.background3);
		bmpList.add(bmpGrass[0]);
		bmpList.add(bmpGrass[1]);
		bmpList.add(bmpBgCloud[0]);
		bmpList.add(bmpBgCloud[1]);
		bmpList.add(bmpBG[0]);
		bmpList.add(bmpBG[1]);
		bmpList.add(bmpBG[2]);
		bmpList.add(bmpBG[3]);
		
		bmpCloud[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.cloud0);
		bmpCloud[1] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.cloud1);
		bmpCloud[2] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.cloud2);
		bmpList.add(bmpCloud[0]);
		bmpList.add(bmpCloud[1]);
		bmpList.add(bmpCloud[2]);
		
		bmpCactus = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.cactus0);
		bmpList.add(bmpCactus);
		
		bmpHeart = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.health); 
		bmpList.add(bmpHeart);
		
		bmpRocketA[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.rocket_a0);
		bmpRocketA[1] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.rocket_a1);
		bmpRocketA[2] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.rocket_a2);
		bmpList.add(bmpRocketA[0]);
		bmpList.add(bmpRocketA[1]);
		bmpList.add(bmpRocketA[2]);
		
		bmpRocketB[0] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.rocket_b0);
		bmpRocketB[1] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.rocket_b1);
		bmpRocketB[2] = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.rocket_b2);
		bmpList.add(bmpRocketB[0]);
		bmpList.add(bmpRocketB[1]);
		bmpList.add(bmpRocketB[2]);
		
		bmpStart = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.start);
		bmpList.add(bmpStart);
		bmpEnd = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.end);
		bmpList.add(bmpEnd);
	}

	public static void release() {
		for(Bitmap bmp : bmpList){
			if(bmp!=null && !bmp.isRecycled()){
				bmp.recycle();
				bmp = null;
			}
		}
		
		/*
		for(int i=0; i<bmpPlane.length; i++){
			if(bmpPlane[i]!=null && !bmpPlane[i].isRecycled()){
				bmpPlane[i].recycle();
				bmpPlane[i] = null;
			}
		}
		
		for(int i=0; i<bmpBG.length; i++){
			if(bmpBG[i]!=null && !bmpBG[i].isRecycled()){
				bmpBG[i].recycle();
				bmpBG[i] = null;
			}
		}
		
		for(int i=0; i<bmpGrass.length; i++){
			if(bmpGrass[i]!=null && !bmpGrass[i].isRecycled()){
				bmpGrass[i].recycle();
				bmpGrass[i] = null;
			}
		}
		
		for(int i=0; i<bmpBgCloud.length; i++){
			if(bmpBgCloud[i]!=null && !bmpBgCloud[i].isRecycled()){
				bmpBgCloud[i].recycle();
				bmpBgCloud[i] = null;
			}
		}
		
		for(int i=0; i<bmpCloud.length; i++){
			if(bmpCloud[i]!=null && !bmpCloud[i].isRecycled()){
				bmpCloud[i].recycle();
				bmpCloud[i] = null;
			}
		}
		
		for(int i=0; i<bmpRocketA.length; i++){
			if(bmpRocketA[i]!=null && !bmpRocketA[i].isRecycled()){
				bmpRocketA[i].recycle();
				bmpRocketA[i] = null;
			}
		}
		
		for(int i=0; i<bmpRocketB.length; i++){
			if(bmpRocketB[i]!=null && !bmpRocketB[i].isRecycled()){
				bmpRocketB[i].recycle();
				bmpRocketB[i] = null;
			}
		}
		
		for(int i=0; i<bmpBangs.length; i++){
			if(bmpBangs[i]!=null && !bmpBangs[i].isRecycled()){
				bmpBangs[i].recycle();
				bmpBangs[i] = null;
			}
		}
		
//		bmpLine;
//		if(bmpLine!=null && !bmpLine.isRecycled()){
//			bmpLine.recycle();
//			bmpLine = null;
//		}
		
		if(bmpCactus!=null && !bmpCactus.isRecycled()){
			bmpCactus.recycle();
			bmpCactus = null;
		}
		if(bmpHeart!=null && !bmpHeart.isRecycled()){
			bmpHeart.recycle();
			bmpHeart = null;
		}
		if(bmpStart!=null && !bmpStart.isRecycled()){
			bmpStart.recycle();
			bmpStart = null;
		}
		if(bmpEnd!=null && !bmpEnd.isRecycled()){
			bmpEnd.recycle();
			bmpEnd = null;
		}
		*/
		
	}

}
