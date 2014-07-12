import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;


public class TileMap {

	private int x;
	private int y;
	
	private int tileSize;
	private int [][] map;
	private int mapWidth;
	private int mapHeight;
	
	private BufferedImage tileSet;
	private Tile[][] tiles;
	
	//campos p/que o mapa seja exibido apenas ate o limite dos sprites
	private int minX;
	private int minY;
	private int maxX = 0;
	private int maxY = 0;
	
	
	
	public TileMap(String s, int tileSize){
		this.tileSize = tileSize;		
		//ler do arquivo
		try{
			
			BufferedReader br = new BufferedReader(new FileReader(s));			
			//A primeira linha do arquivo de mapa deve ser a largura
			mapWidth = Integer.parseInt(br.readLine());
			//A segunda linha do arquivo de mapa deve ser a altura
			mapHeight = Integer.parseInt(br.readLine());
			
			map = new int [mapHeight][mapWidth];
			
			minX = GamePanel.WIDTH - mapWidth * tileSize;
			minY = GamePanel.HEIGHT- mapHeight * tileSize;
			
			String delimiter = "\\s+";//delimitador usado no mapa (qualquer espaco em branco)
			for(int row = 0; row < mapHeight; row++){//le as colunas
				String line = br.readLine();//le a linha inteira de determinada coluna
				String[] tokens = line.split(delimiter);
				for(int col = 0; col < mapWidth; col++){//le as linhas
					//adicionamos essa informação ao objeto mapa.
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update(){}
	
	public void draw(Graphics2D g){		
		for(int row = 0; row < mapHeight; row++){
			for(int col = 0; col < mapWidth; col++){
				//rc = rowCollun
				int rc = map[row][col];				
				//caso a informacao do nosso arquivo de mapa
				//nesta determinada posicao do array bidimensional
				int r = rc / tiles[0].length;
				int c = rc % tiles[0].length;
				
				g.drawImage(
						tiles[r][c].getImage(),
						x + col * tileSize,
						y + row * tileSize,
						null
						);
				
								
			}
		}
	}
	
	public void loadTiles(String nomeArquivo){
		try{
			
			tileSet = ImageIO.read(new File(nomeArquivo));
			int numTilesAcross = (tileSet.getWidth()+1) / (tileSize + 1);//+1 porque tem 1 pixel de borda entre os tiles.
			tiles = new Tile[2][numTilesAcross];//[2] = o arquivo tem 2 linhas
			
			BufferedImage subImage;
			for(int col = 0; col < numTilesAcross; col++){
				//a funcao getSubImage retorna uma parte da imagem , no nosso caso um tile
				subImage = tileSet.getSubimage(col * tileSize + col,
						0,
						tileSize,
						tileSize);
				tiles[0][col] = new Tile(subImage, false);//false = nao blocked, pode caminhar
				subImage = tileSet.getSubimage(col * tileSize + col,
						tileSize + 1,
						tileSize,
						tileSize);
				tiles[1][col] = new Tile(subImage, true);//nao pode caminhar, blocked
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Getters e Setters
	public int getX(){return x;}
	public int getY(){return y;}	
	public void setX(int i){
		x = i;
		if(x > maxX){
			x = maxX;
		}
		if(x < minX){
			x = minX;
		}
	}
	public void setY(int i){
		y = i;
		if(y > maxY){
			y = maxY;
		}
		if(y < minY){
			y = minY;
		}
	}
	public int getColTile(int x){return x / tileSize;}
	public int getRowTile(int y){return y / tileSize;}
	public int getTile(int row, int col){return map[row][col];}
	public int getTileSize(){return tileSize;}
	public boolean isBlocked(int row, int col){
		int rc = map[row][col];
		int r = rc / tiles[0].length;
		int c = rc % tiles[0].length;
		return tiles[r][c].isBlocked();
	}
	
	
}
