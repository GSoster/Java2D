package com.miniTennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	
	//constante
	private static final int DIAMETER = 30;
	
	int x = 0;
	int y = 0;
	int xa = 1;//posição para onde se desloca
	int ya = 1;//posição para onde se desloca
	private Game game;
	
	
	public Ball(Game game){
		this.game = game;
	}
	
	
	
	void move(){
		boolean changeDirection = true;
		if(x + xa < 0){//se bater na borda esquerda
			xa = 1;//passa a se mover para a direita
		}else if(x + xa > game.getWidth() - DIAMETER){//se bater na borda direta
			xa = -1;// passa a se mover para a esquerda 
		}else if(y + ya < 0){//se bater na borda superior
			ya = 1;//passa a se mover p/baixo
		}else if(y + ya > game.getHeight() - DIAMETER){//se bater na borda inferior
			ya = -1;//passa a se mover p/cima
			//game.gameOver();
		}else{
			changeDirection = false;
		}
		
		if(collision()){
			ya = -1;//passa a se mover para cima
			y = game.racquet.getTopY() - DIAMETER;//posiciona a bola na borda superior da raquete
		}
		if(changeDirection)
				Sound.BALL.play();
		
		x = x + xa;
		y = y + ya;
	}
	
	/**
	 * Método que 'desenha' o objeto (bola) na tela.
	 * @param g
	 */
	public void paint(Graphics2D g){
		g.fillOval(x, y, DIAMETER, DIAMETER);//posicao na tela e tamanho da bola
	}
	
	/**
	 * Retorna um rectangle para que seja feita a verificacao de colisao atraves
	 * do metodo intersects do rectangle
	 * @return
	 */
	public Rectangle getBounds(){
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	/**
	 * Metodo que verifica se um retangulo entrou no espaco de outro, ou seja
	 * verifica se houve colisao
	 * @return true caso tenha invadido, false caso nao tenha
	 */
	public boolean collision(){
		return game.racquet.getBounds().intersects(this.getBounds());
	}
}
