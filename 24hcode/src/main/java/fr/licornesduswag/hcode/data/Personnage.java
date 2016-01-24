package fr.licornesduswag.hcode.data;

import java.io.Serializable;

/**
 * Classe définissant un personnage et ses sprites
 * @author Kelian Duval (Spritix) 
 */

public class Personnage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String sprite_face;
	private String sprite_aventure;
	private int x1;
	private int x2;
	private int y;
	
	private float xMap;
	private float yMap;
	private int poseDeBaseX=0;
	private int poseDeBaseY=0;
	private double currentPerc=0;
	
	
	
	/**
	 * 
	 * @param nom
	 * @param sprite_face
	 * @param sprite_aventure
	 * @param x1 pr sprite
	 * @param x2
	 * @param y
	 */
	public Personnage(String nom, String sprite_face, String sprite_aventure, int x1, int x2, int y) {
		super();
		this.nom = nom;
		this.sprite_face = sprite_face;
		this.sprite_aventure = sprite_aventure;
		this.x1 = x1;
		this.x2 = x2;
		this.y = y;
	}
	
	/**
	 * supersuper important
	 * @param nom
	 * @param sprite_aventure
	 * @param xMap
	 * @param yMap
	 */
	public Personnage(String nom, String sprite_aventure, int xMap, int yMap) {
		super();
		this.nom = nom;
		this.sprite_aventure = sprite_aventure;
		this.poseDeBaseX = xMap;
		this.poseDeBaseY = yMap;
		this.xMap = xMap;
		this.yMap = yMap;
	}
	
	public Personnage(String nom, String sprite_face, String sprite_aventure) {
		super();
		this.nom = nom;
		this.sprite_face = sprite_face;
		this.sprite_aventure = sprite_aventure;
	}
	
	
	/**
	 * @return the x1
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(int x1) {
		this.x1 = x1;
	}

	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Personnage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSprite_face() {
		return sprite_face;
	}
	public void setSprite_face(String sprite_face) {
		this.sprite_face = sprite_face;
	}
	public String getSprite_aventure() {
		return sprite_aventure;
	}
	public void setSprite_aventure(String sprite_aventure) {
		this.sprite_aventure = sprite_aventure;
	}
	/**
	 * @return the xMap
	 */
	public float getxMap() {
		return xMap;
	}


	/**
	 * @param xMap the xMap to set
	 */
	public void setxMap(float xMap) {
		this.xMap = xMap;
	}


	/**
	 * @return the yMap
	 */
	public float getyMap() {
		return yMap;
	}


	/**
	 * @param yMap the yMap to set
	 */
	public void setyMap(float yMap) {
		this.yMap = yMap;
	}


	/**
	 * @return the poseDeBaseX
	 */
	public int getPoseDeBaseX() {
		return poseDeBaseX;
	}


	/**
	 * @param poseDeBaseX the poseDeBaseX to set
	 */
	public void setPoseDeBaseX(int poseDeBaseX) {
		this.poseDeBaseX = poseDeBaseX;
	}


	/**
	 * @return the poseDeBaseY
	 */
	public int getPoseDeBaseY() {
		return poseDeBaseY;
	}


	/**
	 * @param poseDeBaseY the poseDeBaseY to set
	 */
	public void setPoseDeBaseY(int poseDeBaseY) {
		this.poseDeBaseY = poseDeBaseY;
	}


	/**
	 * @return the currentPerc
	 */
	public double getCurrentPerc() {
		return currentPerc;
	}


	/**
	 * @param currentPerc the currentPerc to set
	 */
	public void setCurrentPerc(double currentPerc) {
		this.currentPerc = currentPerc;
	}


	@Override
	public String toString() {
		return "Personnage [nom=" + nom + ", sprite_face=" + sprite_face + ", sprite_aventure=" + sprite_aventure + "]";
	}
	
	
}
