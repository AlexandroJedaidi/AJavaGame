package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {

	public FastEnemy(int x, int y, ID id) {
		super(x, y, id);
		velX=2;
		velY=9;

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,8,8);
	}


	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;
		
		if(x<= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x,(int) y, 8, 8);
		
	}

}
