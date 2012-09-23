package game.edw.beetlego.data;

import java.io.Serializable;

public class Config implements Serializable {

	private int totalStage = 2;
	private boolean hasSound = true;
	private boolean hasMusic = true;
	private boolean hasGravity = true;
	private String lastName = "player";

	public Config(){ }
	public Config(int total, boolean sound, boolean music, boolean gravity, String name){
		this.totalStage = total;
		this.setHasSound(sound);
		this.setHasMusic(music);
		this.setHasGravity(gravity);
		this.setLastName(name);
	}
	
	public void setTotalStage(int num){
		if(num<2) num = 2;
		else if(num>6) num = 6;
		totalStage = num;
	}
	
	public int getTotalStage(){
		return totalStage;
	}
	public boolean isHasSound() {
		return hasSound;
	}
	public void setHasSound(boolean hasSound) {
		this.hasSound = hasSound;
	}
	public boolean isHasMusic() {
		return hasMusic;
	}
	public void setHasMusic(boolean hasMusic) {
		this.hasMusic = hasMusic;
	}
	public boolean isHasGravity() {
		return hasGravity;
	}
	public void setHasGravity(boolean hasGravity) {
		this.hasGravity = hasGravity;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
