import java.awt.Color;
import java.awt.Graphics2D;


public class Player {

	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private int width;//tamanho do player
	private int height;//tamanho do player
	
	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;
	
	private double moveSpeed;//aceleracao
	private double maxSpeed;
	private double maxFallingSpeed;
	private double stopSpeed;
	private double jumpStart;//-y é pra cima
	private double gravity;
	
	private TileMap tileMap;
	
	public Player(TileMap tm){
		this.tileMap = tm;
		width = 20;
		height = 20;
		
		moveSpeed = 0.6;
		maxSpeed = 4.2;
		stopSpeed = 0.30;
		jumpStart = -11.0;
		gravity = 0.64;
		
	}
	
	public void setLeft(boolean b){left = b;}
	public void setRight(boolean b){right = b;}
	
	public void setJumping(boolean b){
		if(!falling){
			jumping = true;
		}
	}
	
	///////////////////////////////////////////
	
	public void update(){
		
		//determine next position
		if(left){
			dx -= moveSpeed;
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		}else if(right){
			dx += moveSpeed;
			if(dx > maxSpeed){
				dx = maxSpeed;
			}
		}else{//parado
			if(dx > 0){
				dx -= stopSpeed;
				if(dx < 0){
					dx = 0;
				}
			}else if(dx < 0){
				dx += stopSpeed;
				if(dx > 0){
					dx = 0;
				}
			}
		}
		
		if(jumping){
			dy = jumpStart;
			falling = true;
			jumping = false;
		}
		
		if(falling){
			dy += gravity;
			if(dy > maxFallingSpeed){
				dy = maxFallingSpeed;
			}
		}else{
			dy = 0;
		}
		
		//check collisions
		int currentCol = tileMap.getColTile((int)x);
		int currentRow = tileMap.getRowTile((int)y);
		//destino TO y e TO x
		double tox = x + dx;
		double toy = y + dy;
		
		double tempx = x;
		double tempy = y;
		
		
	}
	
	public void draw(Graphics2D g){
		//posicoes x e y do tileMap
		int tx = tileMap.getX();
		int ty = tileMap.getY();
		
		g.setColor(Color.RED);
		g.fillRect(
				(int)(tx + x - width / 2),
				(int)(ty + y - height / 2),
				width,
				height				
				);
	}
	
}
