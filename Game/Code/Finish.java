package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Finish extends GameObject {

	public Finish(int x, int y, ID id) {
		super(x, y, id);
		

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,16,16);
	}


	public void tick() {
		
		
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRect((int)x,(int) y, 32, 32);
		g.drawString("<-Finish", (int)x + 40, (int)y + 16);
		
	}

}
