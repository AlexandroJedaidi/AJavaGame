package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall extends GameObject {
	
	Handler handler;

	public Wall(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX=5;
		

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,16,640/12*9/2 - 50);
	}


	public void tick() {
		
		x += velX;
	
		if(x<= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
		
		
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 640/12*9/2 - 50);
		
	}

}
