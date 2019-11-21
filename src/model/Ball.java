package model;

import java.awt.Color;

public class Ball {
	
	public final static int PIXEL = 1;
	public final static String IZQ = "izq"; 
	public final static String DER   = "der"; 
	public final static String AR   = "ar"; 
	public final static String AB   = "ab";
	
	
	
	private int diameter;
	private int posX;
	private int posY;
	private int waitT;
	private String direction;
	private int bounces;
	private boolean stop;
	/**
	 * @param diameter
	 * @param posX
	 * @param posY
	 * @param waitT
	 * @param direction
	 * @param bounces
	 * @param color
	 * @param stop
	 */
	public Ball(int diameter, int posX, int posY, int waitT, String direction, int bounces, boolean stop) {
		super();
		this.diameter = diameter;
		this.posX = posX;
		this.posY = posY;
		this.waitT = waitT;
		this.direction = direction;
		this.bounces = bounces;
		
		this.stop = stop;
	}
	/**
	 * @return the diameter
	 */
	public int getDiameter() {
		return diameter;
	}
	/**
	 * @param diameter the diameter to set
	 */
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}
	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	/**
	 * @return the waitT
	 */
	public int getWaitT() {
		return waitT;
	}
	/**
	 * @param waitT the waitT to set
	 */
	public void setWaitT(int waitT) {
		this.waitT = waitT;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the bounces
	 */
	public int getBounces() {
		return bounces;
	}
	/**
	 * @param bounces the bounces to set
	 */
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	/**
	 * @return the stop
	 */
	public boolean isStop() {
		return stop;
	}
	/**
	 * @param stop the stop to set
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
	
	
	
	private void changeScenary(int ancho, int alto) {
		if(posX+diameter>alto){
			direction = IZQ;
			posX = alto-diameter;
			bounces++;
		}
		if(posX<0){
			direction = DER;
			posX=0;
			bounces++;
		}
		if(posY+diameter>alto){
			direction = AR;
			posY = alto-diameter;
			bounces++;
		}
		if(posY<0){
			direction = AB;
			posY=0;
			bounces++;
		}
	}
	
	public boolean stoped(int x, int y){
		boolean estaAt = false;
		if(posX<=x && x<=posX+diameter && posY<=y && y<=posY+diameter){
			estaAt = true;
		}
		return estaAt;
	}
	
	public void changeStop(boolean atr){
		stop = atr;
	}
	
	
	public void moveBall(int ancho, int alto){
		switch(direction){
			case IZQ:
				posX -= PIXEL;
			break;
			case AB:
				posY += PIXEL;
			break;
		
			case AR:
				posY -= PIXEL;
			break;
			
			case DER:
				posX += PIXEL;
			break;
		
		}
		changeScenary(ancho, alto);
	}
	

	
}
