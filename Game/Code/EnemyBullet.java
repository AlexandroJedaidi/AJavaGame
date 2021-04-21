package main;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBullet extends GameObject {
	
	Handler handler;
	Random r = new Random();
	
	BufferedImage basic_image;

	public EnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX=(r.nextInt(5 - -5) + -5);
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
		
		//if(y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;
		if(x<= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int) y, 16, 16);
		g.drawImage(basic_image,(int)x,(int)y,null);
		
	}

}
