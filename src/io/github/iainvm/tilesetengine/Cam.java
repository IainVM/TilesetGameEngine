package io.github.iainvm.tilesetengine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Cam {
	
	//offsets from the top left of the map
	private int offX;
	private int offY;
	private int offZ;
	//how zoomed in the map is
	private int zoom;
	//the speed the camera can move
	private int speed;
	
	
	public Cam(int offX, int offY, int offZ, int zoom, int speed){
		this.offX = offX;
		this.offY = offY;
		this.offZ = offZ;
		this.zoom = zoom;
		this.speed = speed;
	}
	
	public void render(Map map){
		//used to render

		//clear previous render	
	    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
	    //render map
		for(int i = 0; i < map.getDims().get(0); i++){
			//render the layer
			map.renderLayer(i, this);
			
		}
		
		//update display with current buffer
		Display.update();
	}

	public void changeOffset(int x, int y, int z){
		//used for controlling the camera
		this.offX += x;
		this.offY += y;
		this.offZ += z;
	}
	
	public void setOffset(int x, int y, int z){
		//sued for resetting the camera
		this.offX = x;
		this.offY = y;
		this.offZ = z;
	}
	
	public int getOffX(){
		return offX;
	}
	
	public int getOffY(){
		return offY;
	}
	
	public int getOffZ(){
		return offZ;
	}
	
	public int getZoom(){
		return zoom;
	}
	
	public int getSpeed(){
		return this.speed;
	}
}
