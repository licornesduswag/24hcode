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
import java.util.Iterator;

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

import fr.licornesduswag.hcode.SAX.PieceLoader;
import fr.licornesduswag.hcode.data.Acte;
import fr.licornesduswag.hcode.data.Action;
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
public class Main extends BasicGame {
	
	
	Personnage sganarelle = new Personnage("SGANARELLE", "SganarelleFace.png", "");
	Personnage martine = new Personnage("MARTINE", "LucindeFace.png", "");
	
	Personnage persoCourrant = sganarelle;
	
	/*Personnage robert = new Personnage("M. ROBERT", "", "");
	Personnage valere = new Personnage("VALÈRE", "", "");
	Personnage lucas = new Personnage("LUCAS", "", "");
	Personnage geronte = new Personnage("GÉRONTE", "", "");
	Personnage jacqueline = new Personnage("JACQUELINE", "", "");
	*/
	
	
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
	int scene;
	
	
	Piece p = PieceLoader.load("../pieces/html/medecinMalgresLui.xml");
	int nbActe = p.getActes().size();
	int nbScene;
	
	PieceGenerator pg = new PieceGenerator(p);
	Iterator<Object> it = pg.iterator();
	public Main() {
		super("24hcode");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		k = new Keyboard(gc);
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("Ressources/Fonts/paper.ttf");
			new Serializer().fromZip("Ressources/piece.zip", is);
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(32f); // set font size
			font = new TrueTypeFont(awtFont, true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
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
					switch(elem.getClass().getName()){
					case("fr.licornesduswag.hcode.data.Acte"):
						Acte a = (Acte)elem;
						str = "Acte " + a.getNumero();
						transition = true;
						acte = a.getNumero();
						nbScene = a.getScenes().size();
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
						if(r.getParleur().equals("MARTINE")){
							persoCourrant = martine;
						}else if(r.getParleur().equals("SGANARELLE")){
							persoCourrant = sganarelle;
						}else{
							persoCourrant = sganarelle;
						}
						
						for (Contenu c : r.getContenu()){
							if(c.getClass().getName().equals("fr.licornesduswag.hcode.data.Texte")){
								
								Texte t = (Texte) c;
								str += (t.getTexte() + " ");
								System.out.println(str);
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
					case("fr.licornesduswag.hcode.data.Action"):
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
				
				grphcs.drawString("Acte " + acte+"/"+nbActe, 10, 10);
				grphcs.drawString("Scene " + scene+"/"+nbScene, 710, 10);
				grphcs.setColor(Color.white);
				
				
//				Image fg = is.getImage(acte+"_"+scene+"_fg.png");
//				if(!(fg == null)){
//					fg.draw();
//				}
				grphcs.fillRect(0, 450,800, 150);
				grphcs.setColor(Color.black);
				grphcs.drawImage(new SpriteSheet(new Image("../sprites/Medecin malgre lui/persos/"+persoCourrant.getSprite_face()), 96, 96).getSprite(Math.random() < 0.5 ? 0 : 1, 1), 704, 504);
				grphcs.drawString(StringSeparator.separeString(str, 85), 10, 455);
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
			AppGameContainer appgc = new AppGameContainer(new Main());
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
