
import java.awt.image.*;

public class Tile {

	private BufferedImage image;
	private boolean blocked;//se pode ou nao passar pelo tile
	
	
	public Tile(BufferedImage image, boolean blocked){
		this.image = image;
		this.blocked = blocked;
	}
	
	public BufferedImage getImage(){return image;}
	public boolean isBlocked(){return this.blocked;}
	
}
