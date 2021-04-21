package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Game.STATE;

public class HUD {
	
	public static float HEALTH = 100;
	public static boolean death = false;
	private float greenValue = 255;
	private int score = 0;
	private int level = 1;
	private Handler handler;
	private Game game;
	public static boolean damage = false;
	private Color color = Color.black;
	private int ticker = 0;
	
	public HUD(Game game, Handler handler) {
		this.handler = handler;
		this.game = game;
	}
	
	public void tick() {
		
		HEALTH = Game.clamp(HEALTH,0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH*2;
		
		if(HUD.HEALTH <= 0.001f) {
			for (int i = 0; i < handler.object.size();++i) {
				handler.removeObject(handler.object.get(i));
			}
			this.resetLevel();
			//AudioPlayer.getSound("menu_sound").stop();
			//AudioPlayer.getSound("death_sound").play();
			
			game.GameState = STATE.DeathScreen;
		}
		
		if(Level1.completed == true) {
			for (int i = 0; i < handler.object.size() ;++i) {
				handler.removeObject(handler.object.get(i));
			}
			this.resetLevel();
			Level1.completed = false;
			AudioPlayer.getSound("menu_sound").stop();
			game.GameState = STATE.Menu;
		}
		
		if(damage == true){
			damage = false;
			color = Color.red;
		}
		
		ticker++;
		
		if(ticker == 50) {
			ticker = 0;
			color = Color.black;
		}
		
		score++;		
	}
	
	public void render(Graphics g) {
		
		g.setColor(color);
		g.setFont(new Font("Arial", 1 , 25));
		g.drawString("Damage!", Game.WIDTH - 115, Game.HEIGHT - 35);
		g.setFont(new Font("Arial", 1 , 14));
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75,(int)greenValue,0));
		g.fillRect(15, 15, (int) (HEALTH * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		g.drawString("Score: " + score,15,64);
		g.drawString("Wave: " + level,15,80);
		
		
		
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void resetLevel() {
		this.level = 1;
		this.score = 0;
		HEALTH = 100;
	}

}
