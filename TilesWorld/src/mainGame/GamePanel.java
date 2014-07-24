package mainGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import map.TileMap;
import entidades.Player;


public class GamePanel extends JPanel implements KeyListener{

	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 720;
	public static final int TILE_SIZE = 32;
	
	//32 colunas e 22 linhas
	
	private boolean debug;
	
	private Graphics2D g2d;
	private TileMap map;
	private Player player;
	
	public GamePanel(){
		super();
		this.map = new TileMap(GAME_WIDTH, GAME_HEIGHT, TILE_SIZE);
		this.player = new Player();
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}
	
	
	public void update(){		
		player.update();
		map.setPlayerPosition(player.getPlayerX(), player.getPlayerY());
	}
	
	public void draw(Graphics2D g){
		this.map.draw(g);
		this.player.draw(g);
	}
	
	
	public void gameLoop(Graphics g){
		this.g2d = (Graphics2D) g;
		while(true){
			this.update();
			this.draw(g2d);
		}
	}
	
	public void keyPressed(KeyEvent e){
		int code = e.getKeyCode();
		//calcular andando p/direita
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
			player.setDireita(true);
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
			player.setEsquerda(true);			
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
			player.setPulando(true);
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
			player.setCaindo(true);
	}
	public void keyTyped(KeyEvent e){
		
	}
	public void keyReleased(KeyEvent e){
		e.getKeyCode();
	}
	
	public void setDebug(boolean debug){
		this.debug = debug;
		map.setDebug(this.debug);		
	}


	
	
}
