package com.miniTennis;

import java.awt.Graphics2D;

public class Ball {

	int x = 0;
	int y = 0;
	int xa = 1;//posição para onde se desloca
	int ya = 1;//posição para onde se desloca
	private Game game;
	
	
	public Ball(Game game){
		this.game = game;
	}
	
	void move(){
		if(x + xa < 0){//se bater na borda esquerda
			xa = 1;//passa a se mover para a direita
		}else if(x + xa > game.getWidth() - 30){//se bater na borda direta
			xa = -1;// passa a se mover para a esquerda 
		}
		
		if(y + ya < 0){//se bater na borda superior
			ya = 1;//passa a se mover p/baixo
		}else if(y + ya > game.getHeight() - 30){//se bater na borda inferior
			ya = -1;//passa a se mover p/cima
		}
		
		x = x + xa;
		y = y + ya;
	}
	
	
	public void paint(Graphics2D g){
		g.fillOval(x, y, 30, 30);//posicao na tela e tamanho da bola
	}
	
	
}
