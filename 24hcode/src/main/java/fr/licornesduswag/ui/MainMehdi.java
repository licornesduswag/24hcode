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
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.licornesduswag.hcode.data.Acte;
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
public class MainMehdi extends BasicGame {

	/*
	 * Les personnages de Medecin malgres lui avec leur sprites associées
	 */
	Personnage sganarelle = new Personnage("SGANARELLE", "SganarelleFace.png", "", 0, 2, 0);
	Personnage martine = new Personnage("MARTINE", "MartineFace.png", "",0, 2, 1 );
	Personnage robert = new Personnage("M. ROBERT", "RobertFace.png", "", 1, 2, 0);
	Personnage valere = new Personnage("VALÈRE", "ValereFace.png", "",0, 3, 0 );
	Personnage lucas = new Personnage("LUCAS", "LucasFace.png", "", 0, 2, 0);
	Personnage geronte = new Personnage("GÉRONTE", "GeronteFace.png", "", 1, 2, 0);
	Personnage jacqueline = new Personnage("JACQUELINE", "JacquelineFace.png", "", 2, 3, 0);
	Personnage lucinde = new Personnage("LUCINDE", "LucindeFace.png", "", 0, 1, 1);
	Personnage leandre = new Personnage("LÉANDRE", "LeandreFace.png", "", 1, 2, 0);
	Personnage thibault = new Personnage("THIBAULT", "ThibaultFace.png", "", 1, 2, 0);
	Personnage perrin = new Personnage("PERRIN", "PerrinFace.png", "", 1, 2, 0);

	/*
	 * Le personnage courant, celui qui parle
	 */
	Personnage persoCourrant = sganarelle;

	TrueTypeFont font;
	Keyboard k = null;
	boolean test;
	boolean piece;
	boolean transition = true;
	boolean easterEgg;
	ImageStore is = new ImageStore();
	Object elem;
	String str = "";
	String[] lines;
	int acte;
	int scene;


	private int direction = 2;
	private boolean moving = false;

	Piece p = null;//PieceLoader.load("../pieces/html/medecinMalgresLui.xml");
	PieceGenerator pg = null;//new PieceGenerator(p);
	int nbActe = 0;
	int nbScene;

	HashMap<Personnage, Animation[]> spriteSheets = new HashMap<Personnage, Animation[]>(10);


	private ArrayList<ActionDeplacement> toutLesDeplacementsDuMonde = new ArrayList<>();
	int posDeBaseX=50;
	int posDeFinX=500;
	int posDeBaseY=50;
	int posDeFinY=500;

	Iterator<Object> it = null;
	public MainMehdi() {
		super("24hcode");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		k = new Keyboard(gc);


		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("Ressources/Fonts/paper.ttf");
			//new Serializer().fromZip("Ressources/piece.zip", is);

			p=new Serializer().fromZip("Ressources/piece_actions.zip", is).getPiece();
			nbActe= p.getActes().size();
			pg = new PieceGenerator(p);
			it = pg.iterator();


			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(32f); // set font size
			font = new TrueTypeFont(awtFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Piece.toutLesPersonnagesDuMonde = new HashMap<>();
		Personnage martine = new Personnage("martine", "MartineChar",-20,100);
		Personnage sgana = new Personnage("sganarelle", "SganarelleChar",-50,100
				);

		Piece.toutLesPersonnagesDuMonde.put("martine", martine);
		Piece.toutLesPersonnagesDuMonde.put("sganarelle", sgana);

		ArrayList<Personnage> personnages = new ArrayList<Personnage>();
		personnages.add(martine);
		personnages.add(sgana);
		p.setPersonnages(personnages);

		for(int i =0; i<Piece.toutLesPersonnagesDuMonde.size(); i++)
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
					p.setPoseDeBaseX((int)p.getxMap());
					p.setPoseDeBaseY((int)p.getyMap());
					
				}
			}
			//toutLesDeplacementsDuMonde.remove(0);

		}


		if(k.easterEgg()){
			easterEgg = true;
		}
		else{
			easterEgg = false;
			if(k.keyDown() && !test && !piece){
				piece = true;
				Music baton = new Music("Ressources/coups.ogg");

				baton.play();
				
			}
			else if(k.keyDown() && !test){
				if(it.hasNext()){
					if(!AffichageTexte.isDefil()){
						do{
							elem = it.next();
						}while(elem.getClass().getName().equals("fr.licornesduswag.hcode.data.Texte")&& it.hasNext());						
						switch(elem.getClass().getName()){
						case("fr.licornesduswag.hcode.data.Acte"):
							
	
							Acte a = (Acte)elem;
							str = "Acte " + a.getNumero();
							transition = true;
							acte = a.getNumero();
							nbScene = a.getScenes().size();
						break;
						case("fr.licornesduswag.hcode.data.Scene"):
							Music applause = new Music("Ressources/Applause.ogg");
						applause.play();
						Music fond = new Music("Ressources/pokemon.ogg");
						fond.play();
							Scene s = (Scene)elem;
						scene = s.getNumero();
						str = "Scène " + scene;
						transition = true;						
						break;
						case("fr.licornesduswag.hcode.data.Replique"):
							AffichageTexte.reset();
						Replique r = (Replique)elem;
						str = r.getParleur() + " : ";
						switch (r.getParleur()) {
						case "MARTINE":
							persoCourrant = martine;
							break;
						case "SGANARELLE":
							persoCourrant = sganarelle;
							break;
						case "M. ROBERT":
							persoCourrant = robert;
							break;
						case "VALÈRE": 
							persoCourrant = valere;
							break;
						case "LUCAS":
							persoCourrant = lucas;
							break;
						case "GÉRONTE":
							persoCourrant = geronte;
							break;
						case "JACQUELINE" :
							persoCourrant = jacqueline;
							break;
						case "LUCINDE":
							persoCourrant = lucinde;
							break;
						case "LÉANDRE":
							persoCourrant = leandre;
							break;
						case "THIBAUT":
							persoCourrant = thibault;
							break;
						case "PERRIN":
							persoCourrant = perrin;
							break;
						default:
							break;
						}
						for (Contenu c : r.getContenu()){
							if(c.getClass().getName().equals("fr.licornesduswag.hcode.data.Texte")){
								Texte t = (Texte) c;
								str += (t.getTexte() + " ");
							}
						}
						str = StringSeparator.separeString(str, 75);
						lines = str.split("\n");
						transition = false;
						break;
						case("fr.licornesduswag.hcode.data.ActionDeplacement"):
							ActionDeplacement d = (ActionDeplacement) elem;
						//System.out.println(d);
						toutLesDeplacementsDuMonde.add(d);
						posDeFinX = d.getX();
						posDeFinY = d.getY();
						break;
						default:
						}    
					}
					else{
						AffichageTexte.nextLine();
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
		
		if(easterEgg){
			Image img = new Image("Ressources/Images/Unicorn.jpg");
			img.draw();
		}
		else{
			if(!piece){
				grphcs.setBackground(Color.black);
				grphcs.setColor(Color.white);
				grphcs.setFont(font);
				grphcs.drawString("24h du code", 350, 100);
				grphcs.drawString("Espace pour voir la piece", 250, 550);
			}
			else if (transition){
				grphcs.setBackground(Color.black);
				grphcs.setColor(Color.lightGray);
				grphcs.setFont(font);
				grphcs.drawString(str, 365, 250);
			}
			else{
				
				grphcs.setBackground(Color.lightGray);
				Image bg = is.getImage(acte+"_"+scene+"_bg.jpg");
				if(!(bg == null)){
					bg.draw();
				}
				grphcs.drawAnimation(spriteSheets.get(Piece.toutLesPersonnagesDuMonde.
						get("martine"))[direction + (moving ? 4 : 0)], Piece.toutLesPersonnagesDuMonde.
						get("martine").getxMap()-16, Piece.toutLesPersonnagesDuMonde.
						get("martine").getyMap()-30);
				grphcs.drawAnimation(spriteSheets.get(Piece.toutLesPersonnagesDuMonde.
						get("sganarelle"))[direction + (moving ? 4 : 0)], Piece.toutLesPersonnagesDuMonde.
						get("sganarelle").getxMap()-16, Piece.toutLesPersonnagesDuMonde.
						get("sganarelle").getyMap()-30);
				grphcs.setColor(Color.black);
				grphcs.drawString("Acte " + acte+"/"+nbActe, 10, 10);
				grphcs.drawString("Scene " + scene+"/"+nbScene, 710, 10);
				grphcs.setColor(Color.white);
				/*Image fg = is.getImage(acte+"_"+scene+"_fg.png");
				if(!(fg == null)){
					fg.draw();
				}*/
				grphcs.fillRect(0, 450,800, 150);
				grphcs.setColor(Color.black);
				if(lines != null){
					AffichageTexte.affiche(lines, grphcs);
				}
				grphcs.drawImage(new SpriteSheet(new Image("../sprites/Medecin malgre lui/persos/"+persoCourrant.getSprite_face()), 96, 96)
						.getSprite(Math.random() < 0.5 ? persoCourrant.getX1() : persoCourrant.getX2(), persoCourrant.getY()), 704, 504);
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		try{
			AppGameContainer appgc = new AppGameContainer(new MainMehdi());
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
