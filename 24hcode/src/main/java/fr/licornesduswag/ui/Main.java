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

import fr.licornesduswag.hcode.SAX.PieceLoader;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import fr.licornesduswag.hcode.data.Acte;
import fr.licornesduswag.hcode.data.Contenu;
import fr.licornesduswag.hcode.data.Dialogue;
import fr.licornesduswag.hcode.data.ImageStore;
import fr.licornesduswag.hcode.data.Personnage;
import fr.licornesduswag.hcode.data.Piece;
import fr.licornesduswag.hcode.data.PieceIterator;
import fr.licornesduswag.hcode.data.Replique;
import fr.licornesduswag.hcode.data.Scene;
import fr.licornesduswag.hcode.data.Serializer;
import fr.licornesduswag.hcode.data.Texte;

/**
 *
 * @author Maximusk
 */
public class Main extends BasicGame {
    TrueTypeFont font;
    Keyboard k = null;
    boolean test;
    boolean piece;
    boolean transition;
    boolean easterEgg;
    Object elem;
    String str = "";
    int acte;
    int scene;
    PieceIterator pi = new PieceIterator(PieceLoader.load("../pieces/html/romeoEtLaptiteCatin.xml"));
    public Main() {
        super("24hcode");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
    	ImageStore is;
        gc.setShowFPS(false);
        k = new Keyboard(gc);
	try {
		InputStream inputStream	= ResourceLoader.getResourceAsStream("Ressources/Fonts/paper.ttf");
 
		Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(32f); // set font size
		font = new TrueTypeFont(awtFont, true);
		
		Piece piece = creerPiece();
        is = getSpriteFromZip(piece);
        is.printAll(); 
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
	           elem = pi.next();
	           if(elem == null){
	               str = "Fin";
	               return;
	           }
	           switch(elem.getClass().getName()){
	                case("fr.licornesduswag.hcode.data.Acte"):
	                   Acte a = (Acte)elem;
	                   str = "Acte " + a.getNumero();
	                   transition = true;
	                   acte = a.getNumero();
	                   break;
	                case("fr.licornesduswag.hcode.data.Scene"):
	                   Scene s = (Scene)elem;
	                   str = "Scène " + s.getNumero();
	                   transition = true;
	                   scene = s.getNumero();
	                   break;
	                default:
	                   str = "Autre"; 
	                   transition = false;
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
	        	grphcs.setBackground(Color.white);
	        	grphcs.setColor(Color.black);
	        	grphcs.drawString("Acte " + acte, 10, 10);
	        	grphcs.drawString("Scene " + scene, 710, 10);
	        	grphcs.drawString(str, 10, 560);
	        }
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
    
    public static Piece creerPiece(){
        System.out.println("Salut !");
         
        // Texte
        Texte txt = new Texte("Roméo, oh Roméo !");
        
        //Personnage
        Personnage perso1 = new Personnage("Roméo", "toto.png", "tata.png");
        Personnage perso2 = new Personnage("Juliette", "toto.png", "tata.png");
        
        // Réplique
        ArrayList<Contenu> contenus = new ArrayList<>();
        contenus.add(txt);
        Replique rep = new Replique(contenus);
        
        // Dialogue
        ArrayList<Replique> repliques = new ArrayList<>();
        repliques.add(rep);
        Dialogue dial = new Dialogue(repliques);
        
        // Scene
        ArrayList<Dialogue> dialogues = new ArrayList<>();
        dialogues.add(dial);
        Scene scene = new Scene(1, dialogues);
        
        // Acte
        ArrayList<Scene> scenes = new ArrayList<>();
        scenes.add(scene);
        Acte acte = new Acte(1, scenes);
        
        // Pièce
        ArrayList<Acte> actes = new ArrayList<>();
        ArrayList<Personnage> personnages = new ArrayList<>();
        actes.add(acte);
        personnages.add(perso1);
        personnages.add(perso2);
        Piece piece = new Piece("Romeo et Juliette", actes, personnages);
        Personnage sgana = new Personnage("sgana", "SganarelleFace"	, "SganarelleAventure");
        Personnage martine = new Personnage("martine","martineFace","martineAventure");
        Personnage mRobert = new Personnage("M.robert", "teteDeMRobert", "MRobertAventure");
        personnages.clear();
        personnages.add(sgana);
        personnages.add(martine);
        repliques = new ArrayList<>();
        txt = new Texte("Non, je te dis que je n’en veux rien faire, et que c’est à moi de parler et d’être le maître.");
        contenus = new ArrayList<>();
        contenus.add(txt);
        
        repliques.add(new Replique(contenus,sgana.getNom()));
        contenus = new ArrayList<>();
        contenus.add(new Texte("Et je te dis, moi, que je veux que tu vives à ma fantaisie, et ne je ne me suis point mariée avec toi pour souffrir tes fredaines. "));
        repliques.add(new Replique(contenus,martine.getNom()));
        
        dial = new Dialogue(repliques,personnages);
        scene.getDialogues().add(dial);
        scenes.add(scene);
        scene = new Scene(2, new ArrayList<Dialogue>());
        personnages = new ArrayList<Personnage>();
        personnages.add(mRobert);
        personnages.add(sgana);
        personnages.add(martine);
        repliques = new ArrayList<>();
        contenus = new ArrayList<>();
        contenus.add(new Texte("Holà, holà, holà ! Fi ! Qu’est-ce ci ? Quelle infamie ! Peste soit le coquin, de battre ainsi sa femme ! "));
        
        repliques.add(new Replique());
        dial= new Dialogue(repliques, personnages);
        System.out.println(piece);
        
        System.out.println(piece);
        return(piece);
    } 
    
    public static ImageStore getSpriteFromZip(Piece piece){
    	ImageStore is = new ImageStore();
    	//Serializer
        Serializer serial = new Serializer(piece);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			serial.toZip("test.zip","../sprites/Medecin malgre lui/Persos/");
			serial.fromZip("test.zip", is);
			is.printAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
        return is;
    }
    
}
