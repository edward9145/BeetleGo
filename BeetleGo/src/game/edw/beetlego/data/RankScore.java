package game.edw.beetlego.data;

import game.edw.beetlego.C;

import java.io.Serializable;

public class RankScore implements Serializable {

	private int[] scores = new int[C.RANK_NUM];
	private String[] names = new String[C.RANK_NUM];
	
	public RankScore(){
		for(int i=0;i<C.RANK_NUM;i++){
			scores[i] = 0;
			names[i] = "player";
		}
	}
	
	public int setScore(int score){
		for(int i=0;i<C.RANK_NUM;i++){
			if(scores[i]<score){
				for(int j=C.RANK_NUM-1;j>i;j--){
					scores[j]=scores[j-1];
				}
				scores[i]=score;
				return i;
				//break;
			}
		}
		return -1;
	}
	
	public int setScore(int score, String name){
		for(int i=0;i<C.RANK_NUM;i++){
			if(scores[i]<score){
				for(int j=C.RANK_NUM-1;j>i;j--){
					scores[j] = scores[j-1];
					names[j] = names[j-1];
				}
				scores[i] = score;
				names[i] = name;
				return i;
				//break;
			}
		}
		return -1;
	}
	
	public int getScore(int index){
		if(index<0 || index>=scores.length) return 0;
		return scores[index];
	}
	
	public String getName(int index){
		if(index<0 || index>=scores.length) return "";
		return names[index];
	}
	
	public int testRank(int score){
		for(int i=0;i<C.RANK_NUM;i++){
			if(scores[i]<score)
				return i;
		}
		return -1;
	}
}
