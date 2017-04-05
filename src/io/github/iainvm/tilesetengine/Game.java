package io.github.iainvm.tilesetengine;

import java.util.Scanner;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Game{
	
	//stores the map information
	private Map map;
	//handles rendering
	private Cam cam = new Cam(0,0,0,0,5);
	
	
	public Game(){
		//Just loads up the default map used for testing
		this.map = new Map("test");
		System.out.println('TEST');
	}	
	
	public Game(String name){
		//Loads up the map and it's info from the name given
		this.map = new Map(name);
	}
	
	public void play(){

		//play the game code while the display haven't been requested to close
		while (!Display.isCloseRequested()) {
			//run the game computation
			this.gameLoop();
			//render the info to screen
			cam.render(this.map);
			
		}
	}
	
	
	private void gameLoop(){
		//all game logic should be contained in this method
		
		//get keyboard inputs
		this.keyboardControls();
		
	}
	
	private void keyboardControls(){
		int s = this.cam.getSpeed();
		
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
        	this.cam.changeOffset(0, -s, 0);
        }
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
        	this.cam.changeOffset(-s, 0, 0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
        	this.cam.changeOffset(0, s, 0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        	this.cam.changeOffset(s, 0, 0);
		}
	}
	
	public static void mapMaker(){
		//Used to make new maps easily with a bad looking CLI
		//To access this (I have no idea)
		Scanner scn = new Scanner(System.in);
		System.out.println("Map Maker CLI");
		System.out.print("Enter the Name of the map: ");
		String mapName = scn.next();
		System.out.println("Enter the dimensions of the map: ");
		System.out.print("Width: ");
		int width = scn.nextInt();
		System.out.print("Height: ");
		int height = scn.nextInt();
		System.out.print("Layers: ");
		int layers = scn.nextInt();
		

		Map newMap = new Map(mapName, layers, height, width);
		
		for(int z = 0; z < layers; z++){
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					
					int val = scn.nextInt();
					
					newMap.setTile(z, y, x, val);
				}
			}
		}
		
		newMap.saveMap();
		
		scn.close();
	}
}
