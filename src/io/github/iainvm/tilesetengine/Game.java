package io.github.iainvm.tilesetengine;

public class Game{
	
	Map map;
	public Game(){
	}
	
	public void start(){
		this.map = new Map("default");
		
	}
	
	public void createTestMap(){
		//test data for testing read and writing files
		this.map = new Map();
		this.map.setName("default");
		this.map.setDims(1, 2, 2);
		this.map.setTile(0, 0, 0, 1);
		this.map.setTile(0, 0, 1, 1);
		this.map.setTile(0, 1, 0, 2);
		this.map.setTile(0, 1, 1, 1);
		this.map.saveMap();
	}
}
