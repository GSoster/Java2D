package mainGame;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		
		JFrame window = new JFrame("TilesWorld");
		window.setSize(GamePanel.GAME_WIDTH, GamePanel.GAME_HEIGHT);		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);//centraliza 
		window.setLocationRelativeTo(null);				
		GamePanel game = new GamePanel();
		window.add(game);				
		window.setVisible(true);
		
		Graphics g = window.getGraphics();
		
		//game.setDebug(true);
		
		while(true){
			game.gameLoop(g);
		}

	}

}
