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
	
	public void setPosition(int posX, int posY) {
		for(int i=0; i<=2; i++) {
			for(int j=0; j<=2; j++) {
				map[i][j] = 0;
			}
		}
		
		map[0][0]=3;
		map[0][2]=3;
		map[2][0]=3;
		map[2][2]=3;
		
		currentPosX = posX;
		currentPosY = posY;
		map[posX][posY] = 1;
	}
	
	
	public String getError() {
		return errorMessage;
	}
	public void moveNorth() {
		if(currentPosX - 1 != -1 && map[currentPosX-1][currentPosY] != 3){
			map[currentPosX][currentPosY]=0;
			map[currentPosX-1][currentPosY]=1;
			currentPosX+=-1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}
	}
	
	public void moveSouth() {
		if( currentPosX + 1 != 3 && map[currentPosX+1][currentPosY]!=3){
			map[currentPosX][currentPosY]=0;
			map[currentPosX+1][currentPosY]=1;
			currentPosX+=1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}
	}
	
	public void moveWest() {
		if( currentPosY - 1 != -1 && map[currentPosX][currentPosY-1]!=3 ){
			map[currentPosX][currentPosY]=0;
			map[currentPosX][currentPosY-1]=1;
			currentPosY+=-1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}
	}
	
	public void moveEast() {
		if( currentPosY + 1 != 3 && map[currentPosX][currentPosY+1]!=3){
			map[currentPosX][currentPosY]=0;
			map[currentPosX][currentPosY+1]=1;
			currentPosY+=1;
		}
		else {
			errorMessage="Sorry thats an invalid move";
		}

	}
}