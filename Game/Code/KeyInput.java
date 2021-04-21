package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game.STATE;

public class KeyInput implements KeyListener {
	
	public Handler handler;
	
	private boolean uP = false;
	private boolean dP = false;
	private boolean lP = false;
	private boolean rP = false;
	
	private Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Player) {
				
				if(key == KeyEvent.VK_W) { 
					uP = true;
					tempObject.setVelY(-5);				
				}
				if(key == KeyEvent.VK_S) {
					dP = true;
					tempObject.setVelY(5);
				}
				if(key == KeyEvent.VK_A) {
					lP = true;
					tempObject.setVelX(-5);
				}
				if(key == KeyEvent.VK_D) {
					rP = true;
					tempObject.setVelX(5);
				}				
			}
		}	
		if (key == KeyEvent.VK_P) {
			if (game.GameState == STATE.Game) {
				if(Game.paused)
					Game.paused = false;
				else Game.paused = true;
			}
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);	
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Player) {
				if(key == KeyEvent.VK_W) {
					uP = false;
					if(dP) {
						tempObject.setVelY(5);
					} else
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_S) {
					dP = false;
					if(uP) {
						tempObject.setVelY(-5);
					} else
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_A) {
					lP = false;
					if(rP) {
						tempObject.setVelX(5);
					} else
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_D) {
					rP = false;
					if(lP) {
						tempObject.setVelX(-5);
					} else
					tempObject.setVelX(0);
				}
				
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		/**
		int key = e.getKeyCode();
		for(int i = 0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Player) {
				
				if(key == KeyEvent.VK_W) tempObject.setY(tempObject.getVelY() - 5);
				if(key == KeyEvent.VK_S) tempObject.setY(tempObject.getVelY() - 5);
				if(key == KeyEvent.VK_A) tempObject.setX(tempObject.getVelX() - 5);
				if(key == KeyEvent.VK_D) tempObject.setX(tempObject.getVelX() - 5);
				
			}
		}*/
		
	}

}
