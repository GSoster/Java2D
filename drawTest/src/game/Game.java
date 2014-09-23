package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JFrame implements ActionListener{

	final static int PANEL_WIDTH = 400;
	final static int PANEL_HEIGHT = 400;
	private JPanel painelGeral;	
	private GamePanel gamePanel;
	
	public Game(){
		super("Draw!");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(PANEL_WIDTH,PANEL_HEIGHT);
		setLocationRelativeTo(null);
		
		iniciarElementos();
		
		
		setVisible(true);			
	}
	
	public void iniciarElementos(){
		painelGeral = new JPanel();
		
		gamePanel = new GamePanel();
		//painelGeral.add(gamePanel);
		
		//this.add(painelGeral);
		this.add(gamePanel);		
	}
	
	public void iniciar(){
		while(true){
			gamePanel.update();
			gamePanel.draw(this.getGraphics());
		}
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
	
}
