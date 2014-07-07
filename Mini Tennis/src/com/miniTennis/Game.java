package com.miniTennis;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);

	
	public Game(){
		addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e){}
			
			public void keyReleased(KeyEvent e){
				racquet.keyReleased(e);
			}
			
			public void keyPressed(KeyEvent e){
				racquet.keyPressed(e);
			}
			
		});
		setFocusable(true);
		
	}
	
	public void move() {
		ball.move();
		racquet.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
	}

	
}