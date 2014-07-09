import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;


public class TileMap {

	private int x;
	private int y;
	
	private int tileSize;
	private int [][] map;
	private int mapWidth;
	private int mapHeight;
	
	
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
			
			String delimiter = " ";//delimitador usado no mapa
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
				//seja 0 (ZERO), entao o espaco fica preto
				if(rc == 0){					
					g.setColor(Color.BLACK);						
				}
				//se for 1, o espaco ficara branco
				if(rc == 1){					
					g.setColor(Color.WHITE);
				}
				//desenha um retangulo na tela.
				//posicao x, posicao y da tela e tamanho do retangulo
								
				g.fillRect(x + col * tileSize, y + row * tileSize, tileSize, tileSize);
								
			}
		}
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void setX(int i){x = i;}
	public void setY(int i){y = i;}
	
	public int getColTile(int x){
		return x / tileSize;
	}
	public int getRowTile(int y){
		return y / tileSize;
	}
	
	public int getTile(int row, int col){
		return map[row][col];
	}
	
	public int getTileSize(){
		return tileSize;
	}
	
}
