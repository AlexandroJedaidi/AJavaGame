package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private GameObject player;
	private float diffX;
	private float diffY;
	private float distance;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i  = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,16,16);
	}


	public void tick() {
		x += velX;
		y += velY;
		
		diffX = x - player.getX() - 16;
		diffY = y - player.getY() - 16;
		distance = (float) Math.sqrt((x-player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));
		
		velX = ((-2/distance) * diffX);
		velY = ((-2/distance) * diffY);
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

}
