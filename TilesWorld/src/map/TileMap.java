package map;

import java.awt.Color;
import java.awt.Graphics2D;

public class TileMap {

	private int numOfRows;// y
	private int numOfCols;// x
	private int tileSize;
	private int map[][];
	private boolean debug;

	private int lastPlayerYPosition;
	private int lastPlayerXPostition;

	// ////////////////////////
	// 0 -> ceu/background (azul)
	// 1 -> grama (bloco verde)

	// ////////////////////////

	public TileMap(int width, int height, int tileSize) {
		this.tileSize = tileSize;
		this.numOfCols = width / tileSize;
		this.numOfRows = height / tileSize;
		loadMap();
	}

	public TileMap(int width, int height) {
		this.numOfCols = width;
		this.numOfRows = height;
	}

	// metodo responsavel por carregar o mapa
	public void loadMap() {
		// mapa [y][x]
		map = new int[numOfRows][numOfCols];
		for (int x = 0; x < numOfCols; x++) {
			map[19][x] = 1;
			map[20][x] = 1;
			map[21][x] = 1;

		}
	}

	public void setPlayerPosition(int x, int y) {
		lastPlayerXPostition = x;
		lastPlayerYPosition = y;
		this.map[y][x] = -1;
	}

	// Metodo responsavel gerar a parte grafica
	public void draw(Graphics2D g) {
		// percorre o y
		for (int y = 0; y < numOfRows; y++) {
			// percorre o x
			for (int x = 0; x < numOfCols; x++) { 
				switch (map[y][x]) {
				// azul (backGround)
				case 0:
					g.setColor(Color.BLUE);
					g.fillRect(x * tileSize, y * tileSize, tileSize,
							tileSize);
					break;
				// Verde (grama)
				case 1:
					g.setColor(Color.GREEN);
					g.fillRect(x * tileSize, y * tileSize, tileSize ,
							tileSize );
					break;
				}
				//Debugging
				if (this.debug) {
					g.setColor(Color.RED);
					g.drawRect(x * tileSize, y * tileSize, tileSize, tileSize);
				}
			}
		}
		
	}

	// Getters e Setters

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public int getNumOfCols() {
		return this.numOfCols;
	}

	public int getNumOfRows() {
		return this.numOfRows;
	}

}
