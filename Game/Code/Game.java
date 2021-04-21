package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -240840600533728354L;	
	public static final int WIDTH = 640,HEIGHT = WIDTH /12*9;	
	private Thread thread;	
	private boolean running = false;	
	private Handler handler;	
	private HUD hud;
	private Level1 spawner;
	private Menu menu;
	private DeathScreen dc;
	public static boolean paused = false;
	
	public enum STATE {
		Menu, Game, DeathScreen
	};
	
	public STATE GameState = STATE.Menu;
	
	public static BufferedImage sprite_sheet;
	
	public Game() {
		
		handler = new Handler();
		menu = new Menu(this, handler);
		
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
	
		menu = new Menu(this, handler);
		
		//AudioPlayer.load();
		
		//AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT,"Game", this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		
		hud = new HUD(this,handler);
		
		dc = new DeathScreen(this);
		this.addKeyListener(dc);
		
		spawner = new Level1(handler,hud);

	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    while(delta >=1){
                                tick();
                                delta--;
                            }
                            if(running)
                                render();
                            frames++;
                            
                            if(System.currentTimeMillis() - timer > 1000){
                                timer += 1000;
                                //System.out.println("FPS: "+ frames);
                                frames = 0;
                            }
        }
                stop();
    }
		
	private void tick() {
		//this.requestFocus();
		if(GameState == STATE.Game) {
			if(!paused) {
				handler.tick();
				hud.tick();
				spawner.tick();
			}
		}else if(GameState == STATE.Menu) {
			handler.tick();
			menu.tick();
		}
		else if(GameState == STATE.DeathScreen) {
			handler.tick();
			dc.tick();
		}
	}
	
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		handler.render(g);
		
		if(paused) {
			g.setColor(Color.white);
			g.drawString("Paused", 100, 100);
		}
		
		if(GameState == STATE.Game) 
			hud.render(g);
		else if(GameState == STATE.Menu)
			menu.render(g);
		else if(GameState == STATE.DeathScreen)
			dc.render(g);

		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else 
			return var;
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
