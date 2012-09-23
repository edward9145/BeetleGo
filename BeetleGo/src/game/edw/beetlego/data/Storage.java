package game.edw.beetlego.data;

import game.edw.beetlego.C;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class Storage {
	
	public static boolean resetScore(Context context){
		RankScore rankScore = new RankScore();
		try {
			OutputStream os = context.openFileOutput("score.dat", Activity.MODE_PRIVATE);  
			ObjectOutputStream oos = new ObjectOutputStream(os); 
			oos.writeObject(rankScore);
			oos.flush();
	        oos.close();
	        return true;
		} catch (Exception e) {
			Log.d("EX", "save score failed.");
			e.printStackTrace();
		}		
		return false;
	}
	public static boolean resetConfig(Context context){
		Config conf = new Config();
		C.initConfig(conf);
		try {
			OutputStream os = context.openFileOutput("config.dat", Activity.MODE_PRIVATE);  
			ObjectOutputStream oos = new ObjectOutputStream(os); 
			oos.writeObject(conf);
			oos.flush();
	        oos.close();
	        
	        return true;
		} catch (Exception e) {
			Log.d("EX", "save conf failed.");
			e.printStackTrace();
		}
		return false;
	}
	
	public static int saveScore(RankScore rankScore, int score, String name,  Context context){
		int index = rankScore.setScore(score, name);
		if(index >= 0){	// "Congratulation! You are in TOP 5"
			try {
				OutputStream os = context.openFileOutput("score.dat", Activity.MODE_PRIVATE);  
				ObjectOutputStream oos = new ObjectOutputStream(os); 
				oos.writeObject(rankScore);
				oos.flush();
		        oos.close();
			} catch (Exception e) {
				Log.d("EX", "save score failed.");
				e.printStackTrace();
			}
		}
		return index;
	}
	
	public static RankScore loadScore(Context context){
		RankScore rankScore = new RankScore();
		try {
        	InputStream is = context.openFileInput("score.dat");
            ObjectInputStream ois = new ObjectInputStream(is);
			rankScore = (RankScore) ois.readObject();
			ois.close();
		} catch (Exception e) {
			Log.d("EX", "load score failed.");
			e.printStackTrace();
		}
		return rankScore;
	}
	
	public static boolean saveConfig(Config conf, Context context){
		try {
			OutputStream os = context.openFileOutput("config.dat", Activity.MODE_PRIVATE);  
			ObjectOutputStream oos = new ObjectOutputStream(os); 
			oos.writeObject(conf);
			oos.flush();
	        oos.close();
	        
	        return true;
		} catch (Exception e) {
			Log.d("EX", "save conf failed.");
			e.printStackTrace();
		}
		return false;
	}
	
	public static Config loadConfig(Context context){
		Config conf = new Config();
		try {
        	InputStream is = context.openFileInput("config.dat");
            ObjectInputStream ois = new ObjectInputStream(is);
			conf = (Config) ois.readObject();
			ois.close();
		} catch (Exception e) {
			Log.d("EX", "load conf failed.");
			e.printStackTrace();
		}
		return conf;
	}
}
