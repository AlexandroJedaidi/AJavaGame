package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private int timer = 100;

	Random r = new Random();
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(moveOver(mx,my,210,150,200,64) && game.GameState == STATE.Menu) {
			game.GameState = STATE.Game;
			handler.clearEnemies();
			handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
			//AudioPlayer.getMusic("music").stop();
			//AudioPlayer.getSound("menu_sound").play();
			
		}
		
		if(moveOver(mx,my,210,350,200,64) && game.GameState == STATE.Menu) {
			System.exit(0);
		}

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean moveOver(int mx, int my,int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
		}
		return false;
		
	}
	
	public void tick() {
		
		handler.removeObjectRefID(ID.MenuParticle);

		if(timer == 100) {
			timer = 0;
			if(handler.object.size() <= 50)
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.MenuParticle));
			 
		}
		timer++;
		
	}
	
	public void render(Graphics g) {
				
		Font f = new Font("Arial", 1, 50);
		Font f2 = new Font("arial", 1, 30);
		
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("Menu", 240, 70);
		
		g.setFont(f2);
		g.setColor(Color.white);
		g.drawRect(210, 150, 200, 64);
		g.drawString("Play", 270, 190);
		
		g.setColor(Color.white);
		g.drawRect(210, 250, 200, 64);
		g.drawString("Help", 270, 290);
		
		g.setColor(Color.white);
		g.drawRect(210, 350, 200, 64);
		g.drawString("Quit", 270, 390);
		
		g.setColor(Color.black);
		g.drawRect(208, 148, 205, 268);
		
	}

}
