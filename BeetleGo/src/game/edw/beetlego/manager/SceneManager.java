package game.edw.beetlego.manager;

import android.graphics.Rect;
import game.edw.beetlego.model.Background;
import game.edw.beetlego.model.Bang;
import game.edw.beetlego.model.Cactus;
import game.edw.beetlego.model.Cloud;
import game.edw.beetlego.model.EndStation;
import game.edw.beetlego.model.Heart;
import game.edw.beetlego.model.Plane;
import game.edw.beetlego.model.Rocket;
import game.edw.beetlego.model.RocketB;
import game.edw.beetlego.model.Shelter;
import game.edw.beetlego.model.Shield;
import game.edw.beetlego.model.StartStation;

public class SceneManager {
	
	
	public static void load(){
		
	}
	
	public void genItems(){
		
	}
	
	public static Background newBackground(){
		return new Background(
				BitmapManager.bmpBG, 
				BitmapManager.bmpGrass, 
				BitmapManager.bmpBgCloud);
	}
	
	public static Shield newShield(float x, float y){
		Rect drawRect = new Rect(0, 0, 60, 74);
		return new Shield(BitmapManager.bmpShield, 1, drawRect, x, y);
	}
	
	public static Shelter newShelter() {
		Rect drawRect = new Rect(0, 0, 150, 114);
		return new Shelter(BitmapManager.bmpShelter, 3, drawRect);
	}
	
	public static Cloud newCloud(float x, float y){
		return new Cloud(BitmapManager.bmpCloud, x, y);
	}
	
	public static Cactus newCactus(float x, float y){
		return new Cactus(BitmapManager.bmpCactus, x, y);
	}
	
	public static Heart newHeart(float x, float y){
		return new Heart(BitmapManager.bmpHeart, x, y);
	}
	
	public static Bang newBang(float x, float y){
		return new Bang(BitmapManager.bmpBangs, x, y);
	}
	
	public static Plane newPlane(float x, float y){
		return new Plane(BitmapManager.bmpPlane, x, y);
	}
	
	public static Rocket newRocket(float x, float y){
		return new Rocket(BitmapManager.bmpRocketA, x, y);
	}
	
	public static RocketB newRocketB(float x, float y){
		return new RocketB(BitmapManager.bmpRocketB, x, y);
	}
	
	public static StartStation newStartStation(){
		return new StartStation(BitmapManager.bmpStart);
	}
	
	public static EndStation newEndStation(){
		return new EndStation(BitmapManager.bmpEnd);
	}
	
}
