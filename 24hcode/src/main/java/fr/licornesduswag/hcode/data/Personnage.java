package fr.licornesduswag.hcode.data;

import java.io.Serializable;

/**
 * Classe définissant un personnage et ses sprites
 * @author Kelian Duval (Spritix) 
 */

public class Personnage implements Serializable{
	private String nom;
	private String sprite_face;
	private String sprite_aventure;
	
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
