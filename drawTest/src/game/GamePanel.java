package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;

public class GamePanel extends JPanel {

	private Graphics2D g2d;
	private Point2D pontoInicial;
	private Point2D pontoSegurado;	
	private boolean desenha;	
	private int contColor;
	ArrayList<Line2D> listaLinhas;
	ArrayList<Color> listaCores;
	
	public GamePanel(){
		super();
				
		iniciarElementos();		
		setBackground(Color.WHITE);
		
		
		this.addMouseListener(new MouseAdapter() {  
			 public void mousePressed(MouseEvent e) {  
				 pontoInicial = e.getPoint();
				 pontoSegurado = pontoInicial;				 
				 desenha = true;
				 //JOptionPane.showMessageDialog(null, "clicou");  
				 }
			 
			 public void mousedReleased(MouseEvent e){
				 pontoInicial = e.getPoint();					 
			 }
				});
		
			
		this.addMouseMotionListener(new CustomMouseMotionListener());
	}
	
	
	private void iniciarElementos(){
		desenha = false;		
		listaLinhas = new ArrayList<Line2D>();
		listaCores = new ArrayList<Color>();
		
		listaCores.add(Color.BLACK);
		listaCores.add(Color.BLUE);
		listaCores.add(Color.RED);
		listaCores.add(Color.YELLOW);
		
		contColor = listaCores.size() -1;
		
	}
	
	public void update(){		
		if(desenha)
			listaLinhas.add(new Line2D.Double(pontoInicial.getX(),pontoInicial.getY(),pontoSegurado.getX(),pontoSegurado.getY()));
	
	}
	
	public void draw(Graphics g) {			
		g2d = (Graphics2D) g;	
		//as linhas abaixo sao responsaveis pela variacao de cor
		g2d.setColor(listaCores.get(contColor));
		contColor--;
		if(contColor <= 0)
			contColor = listaCores.size() -1;
		if (desenha){
			for(Line2D linha : listaLinhas){
				g2d.draw(linha);
			}
		}
			/*g2d.draw(new Line2D.Double(pontoInicial.getX(),pontoInicial.getY(), Game.PANEL_WIDTH / 2,	Game.PANEL_HEIGHT / 2));*/
	}

	class CustomMouseMotionListener implements MouseMotionListener {

	      public void mouseDragged(MouseEvent e) {
	    	  pontoInicial = e.getPoint();
	    	  pontoSegurado = e.getPoint();
	         //System.out.println("Mouse Dragged: ("+e.getX()+", "+e.getY() +")");
	      }

	      public void mouseMoved(MouseEvent e) {
	    	  //System.out.println("Mouse Moved: ("+e.getX()+", "+e.getY() +")");
	      }    
	   }  
	
	
}
