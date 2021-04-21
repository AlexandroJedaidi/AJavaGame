package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game.STATE;

public class DeathScreen extends KeyAdapter {

	private Game game;

	public DeathScreen(Game game) {
		this.game = game;

	}
	
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_X && game.GameState == STATE.DeathScreen) {
			//AudioPlayer.getSound("death_sound").stop();
			//AudioPlayer.getMusic("music").loop();
			game.GameState = STATE.Menu;	
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
		g.setColor(Color.red);
		g.drawString("You died!", Game.WIDTH / 2 - 35, Game.HEIGHT / 2 - 20);
		g.drawString("Press 'x' to continue", Game.WIDTH / 2 - 60, Game.HEIGHT / 2 - 5);
		
	}

}
