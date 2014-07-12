import java.awt.image.*;

public class Animation {

	
	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	
	public void setFrames(BufferedImage[] images){
		frames = images;
		if(currentFrame >= frames.length){currentFrame = 0;}
	}
	
	public void setDelay(long d){this.delay = d;}
	
	public void update(){
		if(delay == -1){return;} //se delay for  = -1 nao tem animacao (provavelmente
		//o frame tem apenas 1 sprite como idle 
		
		//tempo que passou do inicio da ultima animacao/frame ate o momento atual
		long elapsed = (System.nanoTime() - startTime) / 100000;
		if(elapsed > delay){
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length){currentFrame = 0;}
	}
	
	public BufferedImage getImage(){
		return this.frames[currentFrame];
	}
	
}
