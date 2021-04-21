package main;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {
	
	BufferedImage basic_image;

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		velX=5;
		velY=5;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		basic_image = ss.grabImage(1,2,16,16);

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,16,16);
	}


	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 40) velY *= -1;
		
		if(x<= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int) y, 16, 16);
		g.drawImage(basic_image,(int)x,(int)y,null);
		
	}

}
