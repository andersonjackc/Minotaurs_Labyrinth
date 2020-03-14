package edu.ycp.cs320.Minotaurs_Labyrinth.model;

public class Minotaur {
	
	int map[][] = new int[3][3];
	int currentPosX=1;
	int currentPosY=1;
	
	public void initMap() {
		for (int x = 0; x < 2; x++) {
			for (int y = 0; x < 2; y++) {
				map[x][y] =0;
			}
		}
	}
	
	public void initPlayer() {
		map[1][1]=1;
		currentPosX=1;
		currentPosY=1;
	}
	
	public int[][] getMap(){
		return map;
	}
	
	public int getValue(int posX, int posY) {
		return map[posX][posY];
	}
	
	public int getPosX() {
		return currentPosX;
	}
	
	public int getPosY() {
		return currentPosY;
	}
	
	public void moveNorth() {
		if(currentPosX-1 >= 0){
			map[currentPosX][currentPosY]=0;
			map[currentPosX-1][currentPosY]=1;
			currentPosX+=-1;
		}
	}
	
	public void moveSouth() {
		if(currentPosX+1 <= 2){
			map[currentPosX][currentPosY]=0;
			map[currentPosX+1][currentPosY]=1;
			currentPosX+=1;
		}
	}
	
	public void moveWest() {
		if(currentPosY-1 >= 0){
			map[currentPosX][currentPosY]=0;
			map[currentPosX][currentPosY-1]=1;
			currentPosY+=-1;
		}
	}
	
	public void moveEast() {
		if(currentPosY+1 <= 2){
			map[currentPosX][currentPosY]=0;
			map[currentPosX][currentPosY+1]=1;
			currentPosY+=1;
		}
	}
}
