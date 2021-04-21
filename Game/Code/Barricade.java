package main;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Barricade extends GameObject {
	

	public Barricade(int x, int y, ID id) {
		super(x, y, id);
		velX=0;
		velY=0;
	

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,Game.WIDTH,166);
	}


	public void tick() {
		//x += velX;
		//y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 40) velY *= -1;
		
		if(x<= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int)x,(int) y, Game.WIDTH, 166);
		
		
	}

}
