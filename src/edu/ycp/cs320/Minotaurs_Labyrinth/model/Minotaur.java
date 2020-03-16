package edu.ycp.cs320.Minotaurs_Labyrinth.model;

import edu.ycp.CS320_Minotaurs_Labyrinth.classes.Enemy;

public class Minotaur {
	
	int[][] map = new int[3][3];
	int currentPosX = 1;
	int currentPosY = 1;
	String errorMessage;
	String message;
	
	//fills map w/ 0 for empty, 1 for player, 3 for non-enterable room
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
		map[1][2]=4;
	}
	
	//currently sets player to middle of the map
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
	
	//resets the location as model does not persist between posts
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
		map[1][2]=4;
		
		currentPosX = posX;
		currentPosY = posY;
		map[posX][posY] = 1;
	}
	
	//prints what type of error it is
	public String getError() {
		return errorMessage;
	}
	
	public String getMessage() {
		return message;
	}
	
	//moves the player by changing curr pos vals after checking the location they want to move to
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
		if( currentPosY + 1 != 3 && map[currentPosX][currentPosY+1]!=3 && map[currentPosX][currentPosY+1]!=4){
			map[currentPosX][currentPosY]=0;
			map[currentPosX][currentPosY+1]=1;
			currentPosY+=1;
		}else if(map[currentPosX][currentPosY+1]==4){
			message = "Sorry, this room is locked right now!";
		}else {
			errorMessage="Sorry thats an invalid move";
		}

	}
	
	public Boolean isEnemyAlive(Enemy target) {
		if(target.getHP() <= 0) {
			return false;
		}else{
			return true;
		}
	}
}