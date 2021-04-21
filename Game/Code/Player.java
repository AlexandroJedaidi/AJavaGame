package main;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
	
	Handler handler;
	
	private BufferedImage player_image;

	public Player(int x,int y,ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		player_image = ss.grabImage(1,1,32,32);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,32,32);
	}
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();
			
	}
	
	private void collision() {
		for(int i = 0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() != ID.Player && tempObject.getId() != ID.Finish) {			
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
					HUD.damage = true;
				}
			}else if(tempObject.getId() == ID.Finish) {
				if(getBounds().intersects(tempObject.getBounds())) {
					Level1.completed = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		//g.setColor(Color.white);
		//g.fillRect((int)x,(int) y, 32, 32);
		g.drawImage(player_image,(int)x,(int)y,null);
		
	}	


}
