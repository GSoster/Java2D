package com.miniTennis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	int speed = 1;
	int maxSpeed = 7;
	int score = 0;
	private int getScore(){
		return this.score;
	}
	

	/**
	 * Recebe quais sao as teclas precionadas pelo player e repassa
	 * para a classe Racquet que trabalhara com essa informacao
	 */
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
	
	/**
	 * Atualiza a movimentacao dos dois componentes do jogo:	
	 * A racquet e a bola
	 */
	public void move() {
		ball.move();
		racquet.move();
	}

	/**
	 * Exibe uma janela popup e termina o jogo
	 */
	public void gameOver(){
		JOptionPane.showMessageDialog(this,"Sua pontuacao foi: "+ this.getScore(), "Game OVer!", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	/**
	 * O antialiasing serve para deixar as bordas dos elementos mais suaves
	 * o metodo tambem chama o metodo paint da bola e raquete para que 
	 * a imagem de ambas seja atualizada na tela.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		//score:
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
		
	}

	
}