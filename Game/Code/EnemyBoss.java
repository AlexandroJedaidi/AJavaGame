package main;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBoss extends GameObject {
	
	Random r = new Random();
	Handler handler;
	
	private int timer = 50;
	private int timer2 = 20;
	
	private BufferedImage boss_image;

	public EnemyBoss(int x, int y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX=0;
		velY=2;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		boss_image = ss.grabImage(2,1,96,96);

	}
	
	public Rectangle getBounds() {
		return new Rectangle ((int)x,(int)y,96,96);
	}


	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) {
			velY=0;
		} else
			timer--;
		
		if(timer <= 0) timer2--;
		if(timer2<= 0) {
			if(velX == 0) velX = 2;
			if(velX > 0) velX += 0.005f;
			else if(velX < 0) velX -= 0.005f;
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new EnemyBullet((int) x + 37,(int) y + 80,ID.BasicEnemy,handler));
		}
		
		
		//if(y <= 0 || y >= Game.HEIGHT - 16) velY *= -1;	
		if(x<= 0 || x >= Game.WIDTH - 96) velX *= -1;
		
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x,(int) y, 96, 96);
		g.drawImage(boss_image,(int)x,(int)y,null);
		
	}

}
