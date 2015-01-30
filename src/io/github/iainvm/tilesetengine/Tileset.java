package io.github.iainvm.tilesetengine;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Tileset {

	//standard info about the tileset
	private final String FILENAME;
	private final int WIDTH;
	private final int HEIGHT;
	private final int TWIDTH;
	private final int THEIGHT;
	private Texture texture;
	
	public Tileset(){
		//
		FILENAME = "default";
		this.load();
		this.WIDTH = texture.getImageWidth();
		this.HEIGHT = texture.getImageHeight();
		this.TWIDTH = 8;
		this.THEIGHT = 8;
	}
	
	public Tileset(String mapName){
		FILENAME = mapName;
		this.load();
		this.WIDTH = texture.getImageWidth();
		this.HEIGHT = texture.getImageHeight();
		this.TWIDTH = this.WIDTH/64;
		this.THEIGHT = this.HEIGHT/64;
	}
	
	private void load(){
		try {
			// load texture from PNG file
			this.texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("maps/" + FILENAME + "/" + FILENAME + "t.png"));
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void renderAll(){
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(this.WIDTH,0);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(this.WIDTH,this.HEIGHT);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(0,this.HEIGHT);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	public void renderTile(int tileNum, int posx, int posy, Cam cam){
		float x = (tileNum%this.TWIDTH)/(float) this.TWIDTH;
		float y = (tileNum/this.THEIGHT)/(float) this.THEIGHT;
		float dx = ((tileNum%this.TWIDTH)+1)/((float) this.TWIDTH);
		float dy = ((tileNum/this.THEIGHT)+1)/((float) this.THEIGHT);
		int width = this.WIDTH/this.TWIDTH;
		int height = this.HEIGHT/this.THEIGHT;
		int offsetX = posx * width + cam.getOffX();
		int offsetY = posy * height + cam.getOffY();
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(x,y);
			GL11.glVertex2f(offsetX,offsetY);
			GL11.glTexCoord2f(dx,y);
			GL11.glVertex2f(offsetX + width,offsetY + y);
			GL11.glTexCoord2f(dx,dy);
			GL11.glVertex2f(offsetX + width,offsetY + height);
			GL11.glTexCoord2f(x,dy);
			GL11.glVertex2f(offsetX + x,offsetY + height);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
