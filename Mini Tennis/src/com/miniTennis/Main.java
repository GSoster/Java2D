package com.miniTennis;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		//Prepara a janela
		JFrame frame = new JFrame("Mini Tennis");		
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Cria um jogo e adiciona a janela
		Game game = new Game();
		frame.add(game);
		
		//Game loop
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
