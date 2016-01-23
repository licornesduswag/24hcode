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
	private int x;
	private int y;
	
	public Personnage(String nom, String sprite_face, String sprite_aventure, int x, int y) {
		super();
		this.nom = nom;
		this.sprite_face = sprite_face;
		this.sprite_aventure = sprite_aventure;
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Personnage(String nom, String sprite_face, String sprite_aventure) {
		super();
		this.nom = nom;
		this.sprite_face = sprite_face;
		this.sprite_aventure = sprite_aventure;
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
	@Override
	public String toString() {
		return "Personnage [nom=" + nom + ", sprite_face=" + sprite_face + ", sprite_aventure=" + sprite_aventure + "]";
	}
	
	
}
