package main;

import java.util.Random;

public class Level1 {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	private int ticker;
	private int bosstick;
	public static boolean completed = false;
	private int x = 0;
	
	public Level1(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 50) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1 );
			if (hud.getLevel() == 2 || hud.getLevel() == 12 || hud.getLevel() == 22 || hud.getLevel() == 32 || hud.getLevel() == 42)
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.BasicEnemy));
			if(hud.getLevel() == 4 || hud.getLevel() == 14 /*|| hud.getLevel() == 24|| hud.getLevel() == 34*/ || hud.getLevel() == 44) 
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.FastEnemy));
			if(hud.getLevel() == 5 || hud.getLevel() == 15 || hud.getLevel() == 25 || hud.getLevel() == 35 || hud.getLevel() == 45) {
				handler.addObject(new Wall(0,0,ID.Wall,handler));
				handler.addObject(new Wall(0,640/12*9/2 + 32,ID.Wall,handler));
			}
			if(hud.getLevel() == 7) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50),ID.SmartEnemy, handler));
			}
			if(hud.getLevel() == 50) {
				bosstick = 1;
				ticker = 1000;
				handler.clearEnemies();
				handler.addObject(new EnemyBoss(Game.WIDTH / 2 - 48,- 32,ID.EnemyBoss,handler));		
				handler.addObject(new Barricade(0, 0, ID.Barricade));
			}
			if(hud.getLevel() % 10 == 0)
				x += 10;
		}
		
		if(bosstick == 1 && !(ticker == 0)) {
			ticker--;
		} else if(bosstick == 1 && ticker == 0){
			handler.clearEnemies();
			handler.addObject(new Finish(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Finish));
			bosstick = 0;
		}
				
	}

}
