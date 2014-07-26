package entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import mainGame.GamePanel;

public class Player {

	private int x;// posicao atual x
	private int	y;// posicao atual y
	private int dx;// posicao da direcao x
	private int dy;// posicao da direcao y
	private int maxX;
	private int minX;
	private int maxY;
	private int minY;

	private int velocidade = 1;
	private boolean direita;
	private boolean esquerda;
	private boolean pulando;
	private boolean caindo;
	private boolean correndo;

	

	private Graphics2D g2d;

	// Metodos

	public Player() {
		this.x = 30;
		this.y = 11;
		maxX = (int)GamePanel.GAME_WIDTH / GamePanel.TILE_SIZE -1;
		maxY = (int)GamePanel.GAME_HEIGHT / GamePanel.TILE_SIZE -1;
		minX = 0;
		minY = 0;
		this.correndo = false;
	}

	public void calcMovimento() {
		if(correndo)
			velocidade = 2;
		else
			velocidade = 1;
		if (direita) {
			dx = x + velocidade;
			if(dx > maxX){
				dx = maxX;
			}
			x = dx;
			direita = false;
		}
		if (esquerda) {
			dx = x - velocidade;
			if(dx < minX){
				dx = minX;
			}
			x = dx;
			esquerda = false;
		}
		if (pulando) {
			dy = y - velocidade;
			if(dy < minY){
				dy = minY;
			}
			y = dy;
			pulando = false;
		}
		if (caindo) {
			dy = y + velocidade;
			if(dy > maxY){
				dy = maxY;
			}
			y = dy;
			caindo = false;
		}
	}

	public void update(){
		calcMovimento();
	}
	
	public void draw(Graphics g) {
		this.g2d = (Graphics2D) g.create();

		g2d.setColor(Color.RED);
		g2d.fillRect(x * GamePanel.TILE_SIZE, y * GamePanel.TILE_SIZE,
				GamePanel.TILE_SIZE, GamePanel.TILE_SIZE );

	}
	
	//Getters e Setters
	public int getVelocidade() {return velocidade;}
	public void setVelocidade(int velocidade) {	this.velocidade = velocidade;}
	public boolean isDireita() {return direita;}
	public void setDireita(boolean direita) {this.direita = direita;}
	public boolean isEsquerda() {return esquerda;}
	public void setEsquerda(boolean esquerda) {	this.esquerda = esquerda;}
	public boolean isPulando() {return pulando;}
	public void setPulando(boolean pulando) {this.pulando = pulando;}
	public boolean isCaindo() {	return caindo;}
	public void setCaindo(boolean caindo) {	this.caindo = caindo;}
	public boolean isCorrendo() {	return correndo;}
	public void setCorrendo(boolean correndo) {	this.correndo = correndo;}
	public int getPlayerX() {return x;}
	public int getPlayerY() {return y;}

}
