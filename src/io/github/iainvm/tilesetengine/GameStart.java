package io.github.iainvm.tilesetengine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GameStart {
	
	public static void main(String[] args) {
		
		//Create Display
		createDisplay();
		
		//TODO: Should have some menu stuff around here...
		
		//Entry point of the game
		gameStart(args);
//		Game.mapMaker();
	}
	
	private static void createDisplay(){
		//TODO: Calculate window size dependent on monitor...
		//Set a window size.
    	int width = 800;
    	int height = 600;
    	
    	//attempt to create the Display
        try {
		    Display.setDisplayMode(new DisplayMode(width, height));
		    Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(0);
		}
        GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		GL11.glClearColor(0.0f, 1.0f, 1.0f, 0.0f);   

 
        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
    private static void gameStart(String[] args) {
    	//TODO: args passed for passing map to load
    	Game game;
    	if(args.length == 0){
        	game = new Game();
    	}else{
    		game = new Game(args[0]);
    	}
    	
    	//Play the Game
		game.play();
		
		//close the display and destroy it's memory
		Display.destroy();
	}
}
