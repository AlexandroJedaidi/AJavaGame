package main;



public abstract class Level {
	
	protected Handler handler;
	protected HUD hud;
	//private Random r = new Random();
	protected int scoreKeep = 0;
	protected int ticker;
	protected int bosstick;
	protected static boolean completed = false;
	
	protected Level (Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public abstract void tick();
	
	public void completed() {
		if(bosstick == 1 && !(ticker == 0)) {
			ticker--;
		} else if(bosstick == 1 && ticker == 0){
			handler.clearEnemies();
			handler.addObject(new Finish(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Finish));
		}
	}
	
	

}
