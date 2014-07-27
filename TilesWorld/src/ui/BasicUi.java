package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import mainGame.GamePanel;

public class BasicUi {
	
	private Graphics2D g2d;
	//lista de teclas de skills para ver qual foi precionada.
	private boolean shift;
	
	public BasicUi(){
		this.shift = false;
	}
	
	public void draw(Graphics g){
		g2d = (Graphics2D) g.create();
		g2d.setColor(Color.GRAY);		
		BasicStroke bs = new BasicStroke(5);
		g2d.setStroke(bs);
		
		//desenha a caixa externa
		g2d.drawRect(GamePanel.GAME_WIDTH - GamePanel.GAME_WIDTH + 5, GamePanel.GAME_HEIGHT - 50, GamePanel.GAME_WIDTH - 10, 30);
		
		//opcao shift (correr)
		if(shift)
			g2d.setColor(Color.RED);
		
		g2d.drawString("SHIFT", 10, GamePanel.GAME_HEIGHT - 30);
		g2d.drawRect(GamePanel.GAME_WIDTH - GamePanel.GAME_WIDTH + 3, GamePanel.GAME_HEIGHT - 50, GamePanel.TILE_SIZE * 2, 30);
		
		
		g2d.dispose();		
	}
	
	public void update(){
		
	}
	
	public boolean isShift(){
		return this.shift;
	}
	public void setShift(boolean s){		
		this.shift = s;
	}
}
