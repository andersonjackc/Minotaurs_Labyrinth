package edu.ycp.cs320.Minotaurs_Labyrinth.model;

public class Minotaur {
	
	int[][] map = new int[3][3];
	int currentPosX = 1;
	int currentPosY = 1;
	String errorMessage;
	
	public void initMap() {
		
		for (int x = 0; x <= 2; x++) {
			for (int y = 0; y <= 2; y++) {
				map[x][y] = 0;
			}
		}
		
		map[0][0]=3;
		map[0][2]=3;
		map[2][0]=3;
		map[2][2]=3;
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
	
	public String getError() {
		return errorMessage;
	}
	public void moveNorth() {
		if(map[currentPosX-1][currentPosY] != 3){
			map[currentPosX][currentPosY]=0;
			map[currentPosX-1][currentPosY]=1;
			currentPosX+=-1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}
	}
	
	public void moveSouth() {
		if(map[currentPosX+1][currentPosY]!=3){
			map[currentPosX][currentPosY]=0;
			map[currentPosX+1][currentPosY]=1;
			currentPosX+=1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}
	}
	
	public void moveWest() {
		if(map[currentPosX][currentPosY-1]!=3){
			map[currentPosX][currentPosY]=0;
			map[currentPosX][currentPosY-1]=1;
			currentPosY+=-1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}
	}
	
	public void moveEast() {
		if(map[currentPosX][currentPosY+1]!=3){
			map[currentPosX][currentPosY]=0;
			map[currentPosX][currentPosY+1]=1;
			currentPosY+=1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}

	}
}