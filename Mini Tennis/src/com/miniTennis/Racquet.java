package com.miniTennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
public class Racquet{
		//	constantes
		private static final int Y = 330;
		private static final int WIDTH = 60;
		private static final int HEIGHT = 20;
		
		int x = 0;
		int xa = 0;
		private Game game;
		
		public Racquet(Game game){
			this.game = game;
		}
		
		/**
		 * Verifica se a movimentacao nao acabara batendo em nenhuma das bordas (dereita/esquerda)
		 * caso nao haja empecilho a movimentacao ocorre.
		 */
		public void move(){
			if(x + xa > 0 && x + xa < game.getWidth() - 60){
				x = x + xa;
			}
		}
		
		/**
		 * Exibe um retangulo (Representacao grafica da racquet) com os seguintes atributos:
		 * Posicao x = posicao x da tela
		 * 330 (canto inferior da janela do jogo) = equivale a posicao y da tela
		 * 60 = comprimento do objeto
		 * 10 = altura do objeto
		 * @param g
		 */
		public void paint(Graphics2D g){
			g.fillRect(x, Y, WIDTH, HEIGHT);
		}
		
		/**
		 * Caso a tecla pare de ser precionada, o vetor de movimento passa a ser zero.
		 * @param e
		 */
		public void keyReleased(KeyEvent e ){
			xa = 0;
		}
		
		/**
		 * Verifica se a tecla precionada foi 'direita' ou 'esquerda'
		 * e seta a direcao do movimento (xa) de acordo.
		 * Cabe salientar que a classe Racquet so se move na horizontal
		 * portanto nao temos propriedade y
		 * @param e
		 */
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				xa = -1;
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				xa = 1;
			}
		}
		
		/**
		 * Usaremos o metodo intersect de um Rectangle para verificar
		 * se houve colisao, por isso retornamos um rectangle, para que a
		 * ball faça essa checagem.
		 * @return
		 */
		public Rectangle getBounds(){
			return new Rectangle(x, Y, WIDTH, HEIGHT);
		}
	
		/**
		 * Retorna a borda superior da raquete.
		 * @return
		 */
		public int getTopY() {
			return Y;
		}
}
