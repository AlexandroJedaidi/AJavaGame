package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
	
	Random r = new Random();
	private Color color;
	private int timer = 0;

	public MenuParticle(int x, int y, ID id) {
		super(x, y, id);
		velX=r.nextInt(7)+1;
		velY=r.nextInt(7)+1;

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,16,16);
	}


	public void tick() {
		
		timer++;
		
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 40) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
			
		if(timer%100 == 0)
			color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
		
	}
	
	public void bounce() {
		
		if(y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;
		if(x<= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		if((x >= 208 - 16 && x <= 413 + 16) && (y >= 148 - 16 && y <= 416)) {
			velY *= -1;
			velX *= -1;
		}
			
	}

	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

}
