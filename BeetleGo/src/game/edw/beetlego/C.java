package game.edw.beetlego;

import game.edw.beetlego.data.Config;
import android.graphics.Rect;

public class C {
	public static boolean hasSound = true;
	public static boolean hasMusic = true;
	public static boolean hasGravity = true;
	public static boolean hasDaynight = false;
	public static int SCR_W = 640;
	public static int SCR_H = 480;
	public static Rect SCR_RECT = new Rect(0, 0, 640, 480);
	
	public static int GAME_PAUSE = 0;
	public static int GAME_START = 1;
	public static int START_TIME = 500;
	public static int GAME_PLAY = 2;
	public static int GAME_END = 4;
	public static int END_TIME = 500;
	public static int GAME_OVER = 8;
	public static int STAGE_LENGTH = 1500;
	public static int STAGE_ID = 0;
	public static int STAGE_NUM = 2;
	public static int STAGE_MAX = 6;
	public static int STAGE_INFO[] = {Integer.MAX_VALUE, 500, 1000, 1500, 2000, 3000, 4000}; 
	
	public static int GAME_STATE = GAME_PAUSE;
	
	public static float ROCKETA_SPEEDX = 5;
	public static int ROCKETA_NUM = 2;
	public static int ROCKETA_UPDATE = 200;
	public static float ROCKETB_SPEEDX = 8;
	public static int ROCKETB_NUM = 1;
	public static float ROCKETB_SPEEDY = 0.5f;
	public static int ROCKETB_UPDATE = 300;
	
	public static float CLOUD_SPEEDX = 3;
	public static int CLOUD_UPDATE = 350;
	
	public static float CACTUS_SPEEDX = 4;
	public static int CACTUS_UPDATE = 800;
	
	public static float HEART_SPEEDX = 10;
	public static float HEART_SPEEDY = 10;
	public static int HEART_ADD_LIFE = 2;
	public static int HEART_UPDATE = 500;
	
	public static int UPDATE_INC = 50;
	
	public static int PLANE_POSX = 150;
	public static float PLANE_SPEEDY_MAX = 18.0f;
	public static float PLANE_ACC = 1.0f;
	public static float PLANE_DEC = 1.2f;
	public static int PLANE_LIFE = 6;
	public static int PLANE_MAXLIFE = 10;
	public static float ZERO = 1e-5f;
	
	public static int score = 0;
	public static int SCORE_MAX = 999999;
	
	public static double ACC_X = 0.6;
	public static double ACC_Y = 0.0;
	public static double ACC_X_FACTOR = 25;
	public static double ACC_Y_FACTOR = 20;
	
	public static final float SHIELD_MAXNUM = 3;
	public static final float SHIELD_SPEEDX = 18;
	public static final float SHIELD_SPEED_RADIUS = 50;
	public static final float SHIELD_SPEED_ANGLE = 0.3f;
	public static final float SHIELD_SPEED_SHAKE = 2f;
	public static final int SHELTER_TIME = 150;
	
	public static final long DAY_TIME = 800;
	
	public static final int NIGHT_HUE = 35;
	public static final float NIGHT_CONTRAST = 0.6f;
	public static final int NIGHT_SATURATION = 80;
	public static final int NIGHT_BRIGHTNESS = -60;
	
	public static String LAST_NAME = "player";
	public static final int RANK_NUM = 5;
	public static final int HOW_NUM = 4;
	
	public static void initConfig(Config config){
    	C.STAGE_NUM = config.getTotalStage();
    	C.hasSound = config.isHasSound();
    	C.hasMusic = config.isHasMusic();
    	C.hasGravity = config.isHasGravity();
    	C.LAST_NAME = config.getLastName();
    }
}
