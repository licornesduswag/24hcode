/*
 * The MIT License
 *
 * Copyright 2016 Romain Porte (MicroJoe) microjoe at mailoo.org.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package fr.licornesduswag.ui;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.licornesduswag.hcode.data.Acte;
import fr.licornesduswag.hcode.data.Action;
import fr.licornesduswag.hcode.data.ActionDeplacement;
import fr.licornesduswag.hcode.data.Contenu;
import fr.licornesduswag.hcode.data.ImageStore;
import fr.licornesduswag.hcode.data.Personnage;
import fr.licornesduswag.hcode.data.Piece;
import fr.licornesduswag.hcode.data.PieceGenerator;
import fr.licornesduswag.hcode.data.Replique;
import fr.licornesduswag.hcode.data.Scene;
import fr.licornesduswag.hcode.data.Serializer;
import fr.licornesduswag.hcode.data.Texte;
import fr.licornesduswag.hcode.utils.StringSeparator;

/**
 *
 * @author Maximusk
 */
public class MainKeliane extends BasicGame {
	TrueTypeFont font;
	Keyboard k = null;
	boolean test;
	boolean piece;
	boolean transition;
	boolean easterEgg;
	ImageStore is = new ImageStore();
	Object elem;
	String str = "";
	int acte;
	private float x = 300, y = 300;
	private int direction = 2;
	private boolean moving = false;
	int scene;
	Piece p;// = PieceLoader.load("../pieces/html/medecinMalgresLui.xml");
	PieceGenerator pg = null;
	HashMap<Personnage, Animation[]> spriteSheets = new HashMap<Personnage, Animation[]>(10);
	Iterator it = null;

	private ActionDeplacement currentDeplacement = null;
	private ArrayList<ActionDeplacement> toutLesDeplacementsDuMonde = new ArrayList<>();
	private double currentPerc = 0;
	int posDeBaseX=50;
	int posDeFinX=500;
	int posDeBaseY=50;
	int posDeFinY=500;


	public MainKeliane() {
		super("24hcode");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		k = new Keyboard(gc);
		currentDeplacement = new ActionDeplacement(45, 45, "martine", "martine bouge");
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("Ressources/Fonts/paper.ttf");
			p=new Serializer().fromZip("Ressources/piece_actions.zip", is).getPiece();
			pg = new PieceGenerator(p);
			it = pg.iterator();
			System.out.println(p+"\n\n\n");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(32f); // set font size
			font = new TrueTypeFont(awtFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p.toutLesPersonnagesDuMonde = new HashMap<>();
		Personnage martine = new Personnage("martine", "MartineChar",200,300);
		Personnage sgana = new Personnage("sganarelle", "SganarelleChar",100,500);
		
		p.toutLesPersonnagesDuMonde.put("martine", martine);
		p.toutLesPersonnagesDuMonde.put("sganarelle", sgana);
		

		ArrayList<Personnage> personnages = new ArrayList();
		personnages.add(martine);
		personnages.add(sgana);
		p.setPersonnages(personnages);

		for(int i =0; i<p.toutLesPersonnagesDuMonde.size(); i++)
		{
			SpriteSheet spriteSheet = new SpriteSheet("../sprites/Medecin malgre lui/Persos/"+ p.getPersonnages().get(i).getSprite_aventure() +".png", 32, 32);
			Animation[] animations = new Animation[8];
			for(int j=0; j<8; j++)
			{
				animations[j] = new Animation();
			}
			animations[4].addFrame(spriteSheet.getSprite(0, 3), 500);
			animations[4].addFrame(spriteSheet.getSprite(2, 3), 500);
			animations[5].addFrame(spriteSheet.getSprite(0, 1), 500);
			animations[5].addFrame(spriteSheet.getSprite(2, 1), 500);
			animations[6].addFrame(spriteSheet.getSprite(0, 0), 500);
			animations[6].addFrame(spriteSheet.getSprite(2, 0), 500);
			animations[7].addFrame(spriteSheet.getSprite(0, 2), 500);
			animations[7].addFrame(spriteSheet.getSprite(2, 2), 500);
			animations[0].addFrame(spriteSheet.getSprite(1, 3), 500);
			animations[1].addFrame(spriteSheet.getSprite(1, 1), 500);
			animations[2].addFrame(spriteSheet.getSprite(1, 0), 500);
			animations[3].addFrame(spriteSheet.getSprite(1, 2), 500);
			spriteSheets.put(p.getPersonnages().get(i), animations);
		}
	}
	
	float interpLineaire(int p0,int p1,float currentPerc)
	{
		//System.out.println(p0+(p1-p0)*currentPerc);
		return p0+(p1-p0)*currentPerc;
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//if 50ms
		if (!toutLesDeplacementsDuMonde.isEmpty())
		{
			for (ActionDeplacement a : toutLesDeplacementsDuMonde)
			{
				String nomPerso = a.getPersonnage().toLowerCase();
				System.out.println(nomPerso);
				Personnage p = Piece.toutLesPersonnagesDuMonde.get(nomPerso.toLowerCase());
			
				System.out.println(p);
				System.out.println(p.getCurrentPerc());
				p.setCurrentPerc(p.getCurrentPerc()+0.05);
				System.out.println(p.getCurrentPerc());
				
				p.setxMap(interpLineaire(p.getPoseDeBaseX(), a.getX(), (float)p.getCurrentPerc()));
				p.setyMap(interpLineaire(p.getPoseDeBaseY(), a.getY(), (float)p.getCurrentPerc()));
			}
			for (String s : Piece.toutLesPersonnagesDuMonde.keySet())
			{
				Personnage p = Piece.toutLesPersonnagesDuMonde.get(s);
				if (p.getCurrentPerc()>1)
				{
					toutLesDeplacementsDuMonde.remove(0);
					p.setCurrentPerc(0);
				}
			}
			//toutLesDeplacementsDuMonde.remove(0);

		}
		/*if (currentDeplacement != null && currentPerc < 1) {
			currentPerc += 0.05;
			System.out.println(currentPerc + " current");
			x = interpLineaire(posDeBase, posDeFin, (float)currentPerc);
		} else if (currentDeplacement != null) {
			posDeBase = posDeFin;
			currentDeplacement = null;
		}*/

		if(k.easterEgg()){
			easterEgg = true;
		}
		else{
			easterEgg = false;
			if(k.keyDown() && !test && !piece){
				piece = true;
			}
			else if(k.keyDown() && !test){
				if(it.hasNext()){
					elem = it.next();
					System.out.println(elem);
					switch(elem.getClass().getName()){
					case("fr.licornesduswag.hcode.data.Acte"):
						Acte a = (Acte)elem;
					str = "Acte " + a.getNumero();
					transition = true;
					acte = a.getNumero();
					break;
					case("fr.licornesduswag.hcode.data.Scene"):
						Scene s = (Scene)elem;
					scene = s.getNumero();
					str = "Scène " + scene;
					transition = true;

					break;
					case("fr.licornesduswag.hcode.data.Replique"):
						Replique r = (Replique)elem;
					str = r.getParleur() + " : ";
					for (Contenu c : r.getContenu()){
						if(c.getClass().getName().equals("fr.licornesduswag.hcode.data.Texte")){
							Texte t = (Texte) c;
							str += (t.getTexte() + " ");
						}
						else{
							Action act = (Action)c;
							str += (" * " + act.getDescription() + " * ");
						}
					}
					transition = false;						
					break;
					case("fr.licornesduswag.hcode.data.Texte"):
						break;
					case("fr.licornesduswag.hcode.data.ActionDeplacement"):
						ActionDeplacement d = (ActionDeplacement) elem;
					//System.out.println(d);
					currentDeplacement = d;
					toutLesDeplacementsDuMonde.add(d);
					currentPerc = 0;
					posDeFinX = d.getX();
					posDeFinY = d.getY();
					break;
					default:
						str = elem.getClass().getName(); 
						transition = false;
					}	        		
				}
				else{
					str = "Fin.";
				}
			}
			test = !k.keyUp();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics grphcs) throws SlickException {

		//System.out.println(spriteSheets.get(p.toutLesPersonnagesDuMonde.get("Martine"))+"\n");
		grphcs.drawAnimation(spriteSheets.get(p.toutLesPersonnagesDuMonde.get("martine"))[direction + (moving ? 4 : 0)], p.toutLesPersonnagesDuMonde.get("martine").getxMap()-16, p.toutLesPersonnagesDuMonde.get("martine").getyMap()-30);
		grphcs.drawAnimation(spriteSheets.get(p.toutLesPersonnagesDuMonde.get("sganarelle"))[direction + (moving ? 4 : 0)], p.toutLesPersonnagesDuMonde.get("sganarelle").getxMap()-16, p.toutLesPersonnagesDuMonde.get("sganarelle").getyMap()-100);
		if(easterEgg){
			Image img = new Image("Ressources/Images/Unicorn.jpg");
			img.draw();
		}
		else{
			if(!piece){
				grphcs.setBackground(Color.darkGray);
				grphcs.setColor(Color.white);
				grphcs.setFont(font);
				grphcs.drawString("24h du code", 10, 10);
				grphcs.drawString("Espace pour voir la pièce", 10, 550);
			}
			else if (transition){
				grphcs.setBackground(Color.black);
				grphcs.setColor(Color.lightGray);
				grphcs.setFont(font);
				grphcs.drawString(str, 400, 250);
			}
			else{
				Image bg = is.getImage(acte+"_"+scene+"_bg.jpg");
				if(!(bg == null)){
					bg.draw();
				}
				grphcs.setBackground(Color.lightGray);
				grphcs.setColor(Color.black);
				grphcs.drawString("Acte " + acte, 10, 10);
				grphcs.drawString("Scene " + scene, 710, 10);
				grphcs.setColor(Color.black);
				grphcs.drawString(StringSeparator.separeString(str, 85), 10, 48);
				Image fg = is.getImage(acte+"_"+scene+"_fg.png");
				if(!(fg == null)){
					fg.draw();
				}
			}
		}
	}

	public static void main(String[] args){
		try{
			AppGameContainer appgc = new AppGameContainer(new MainKeliane());
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}