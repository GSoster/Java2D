import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;;


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
	
	
	//corners do player
	private boolean topLeft;
	private boolean topRight;
	private boolean bottomLeft;
	private boolean bottomRight;
	
	
	//animacoes
	private Animation animation;
	private BufferedImage[] idleSprites;
	private BufferedImage[] walkingSprites;
	private BufferedImage[] jumpingSprites;
	private BufferedImage[] fallingSprites;
	private boolean facingLeft; //todos os sprites foram feitos olhando p/esquerda
	//por isso caso estejam indo para outra direcao precisamos tratar
	
	
	public Player(TileMap tm){
		
		this.tileMap = tm;
		
		width = 22;//tamanho do sprite do nosso player
		height = 22;//tamanho do sprite do nosso player
		
		moveSpeed = 0.6;
		maxSpeed = 4.2;
		maxFallingSpeed  = 12;
		stopSpeed = 0.30;
		jumpStart = -11.0;
		gravity = 0.64;
		
		try{
			idleSprites = new BufferedImage[1];//so temos um sprite p/essa animacao
			fallingSprites = new BufferedImage[1];//so temos um sprite p/essa animacao
			jumpingSprites = new BufferedImage[1];//so temos um sprite p/essa animacao
			walkingSprites = new BufferedImage[6];//temos 6 sprite p/essa animacao
			//lendo os sprites:
			idleSprites[0] = ImageIO.read(new File("src/graphics/player/kirbyidle.gif"));
			fallingSprites[0] = ImageIO.read(new File("src/graphics/player/kirbyfall.gif"));
			jumpingSprites[0] = ImageIO.read(new File("src/graphics/player/kirbyjump.gif"));
			//como os sprites de walking sao 6, precisamos percorrer um loop para preencher cada um
			//usaremos subImage para isso (da mesma forma que p/o mapa)
			
			BufferedImage image = ImageIO.read(new File("src/graphics/player/kirbywalk.gif"));
			for(int i = 0; i < walkingSprites.length; i++){
				walkingSprites[i] = image.getSubimage(
						i * width + i,
						0,
						width,
						height);
			}
			animation = new Animation();
			facingLeft = false;//para saber p /onde o player esta olhando
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	//Seta a posicao do player
	public void setx(int x){
		this.x = x;
	}
	//seta a posicao do player
	public void sety(int y){
		this.y = y;
	}	
	public void setLeft(boolean b){left = b;}
	public void setRight(boolean b){right = b;}
	
	public void setJumping(boolean b){
		if(!falling){
			jumping = true;
		}
	}
	
	public void calculateCorners(double x, double y){
		int leftTile = tileMap.getColTile((int) (x - width / 2));
		int rightTile = tileMap.getColTile((int) (x + width / 2) -1 );//para nao exceder o limit (OutOfBounds)
		int topTile = tileMap.getRowTile((int) (y - height / 2));
		int bottomTile = tileMap.getRowTile((int) (y + height / 2) -1);//mesma coisa que o de cima.
		topLeft = tileMap.isBlocked(topTile, leftTile);//verifica se nao eh igual a zero pq o ZERO eh o tile bloqueado
		topRight = tileMap.isBlocked(topTile, rightTile);
		bottomLeft = tileMap.isBlocked(bottomTile, leftTile);
		bottomRight = tileMap.isBlocked(bottomTile, rightTile);
		
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
		
		//Calculando movimento na vertical!
		calculateCorners(x, toy);//x e o y destino
		if(dy < 0){//movendo p/cima
			if(topLeft || topRight){//se o topo do player estiver bloqueado
				dy = 0;//para(stop) de se mover
				tempy = currentRow * tileMap.getTileSize() + height / 2;
			}else{
				tempy += dy; //livre para se mover p/cima
			}
		}
		if(dy > 0){//movendo p/baixo
			if(bottomLeft || bottomRight){//se os cantos inferiores estiverem bloqueados
				dy = 0; //para(stop) de se mover
				falling = false;
				tempy = (currentRow + 1) * tileMap.getTileSize() - height / 2;
			}else{
				tempy += dy;//livra para se mover
			}
		}
		
		//Calculando movimento na horizontal
		calculateCorners(tox, y);
		if(dx < 0){//indo p/a esquerda
			if(topLeft || bottomLeft){
				dx = 0;//para(stop) de se mover
				tempx = currentCol * tileMap.getTileSize() + width / 2;//para do lado direito do campo bloqueado
			}else{				
				tempx += dx;//livre para se mover na direcao x
			}
		}
		if(dx > 0){//indo p/a direita
			if(topRight || bottomRight){
				dx = 0;//para(stop) de se mover
				tempx = (currentCol + 1) * tileMap.getTileSize() - width / 2;//parar do lado esquerdo do campo bloqueado
			}else{
				tempx += dx;//livre para se mover na direcao x
			}
		}
		
		if(!falling){//se nao estiver caindo
			calculateCorners(x, y + 1);//+1 para checar o chao
				if(!bottomLeft && !bottomRight){//se nao tivermos nada sob os pes do player
					falling = true;//entao estamos caindo.
				}
			}
		x = tempx;
		y = tempy;
		
		//Scrolling!!
		//movendo o mapa!
		//o codigo abaixo serve para manter o player no centro da tela.
		tileMap.setX((int)(GamePanel.WIDTH / 2 - this.x));
		tileMap.setY((int)(GamePanel.HEIGHT / 2 - this.y));
		
		//########################  ANIMACOES  ####################
		//animacao
		//sprite animation
		
		//se estiver indo p/direita ou esquerda, usa a a animacao de caminhar
		if(left || right){
			animation.setFrames(walkingSprites);
			animation.setDelay(100);
			
		}else{//se estiver parado, usa a animacao de parado (idle)
			animation.setFrames(idleSprites);
			animation.setDelay(-1);//p/nao trocar de animacao ate que receba nova ordem
		}
		
		if(dy < 0){//movendo p/cima
			animation.setFrames(jumpingSprites);
			animation.setDelay(-1);
		}
		if(dy > 0 && falling){//movendo p/baixo
			animation.setFrames(fallingSprites);
			animation.setDelay(-1);
		}
		
		if(dy == 0 && falling){
			animation.setFrames(fallingSprites);
			animation.setDelay(-1);
		}
		
		animation.update();
		
		//setando a variavel facingLeft
		if(dx > 0){
			facingLeft = false;
		}if(dx < 0){
			facingLeft = true;
		}
	}
	
	public void draw(Graphics2D g){
		//posicoes x e y do tileMap
		int tx = tileMap.getX();
		int ty = tileMap.getY();
		
		//como nossos sprites estao virados p/esquerda
		//caso o player esteja olhando p/esquerda, basta carregar os sprites
		if(facingLeft){
			g.drawImage(
					animation.getImage(),
					(int)(tx + x - width / 2),
					(int)(ty + y - height / 2),
					null
					);
		}else{
			//caso o player esteja olhando p/direita, eh necessario tratar os sprites
			g.drawImage(
					animation.getImage(),
					(int)(tx + x - width / 2 + width),//tratando imagem p/ficar virara p/direita
					(int)(ty + y - height/ 2),//tratando imagem p/ficar virara p/direita
					-width,
					height,
					null
					);
		}
	}
	
}
