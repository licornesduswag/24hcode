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

package fr.licornesduswag.hcode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import fr.licornesduswag.hcode.data.Acte;
import fr.licornesduswag.hcode.data.Contenu;
import fr.licornesduswag.hcode.data.Dialogue;
import fr.licornesduswag.hcode.data.Personnage;
import fr.licornesduswag.hcode.data.Piece;
import fr.licornesduswag.hcode.data.Replique;
import fr.licornesduswag.hcode.data.Scene;
import fr.licornesduswag.hcode.data.Serializer;
import fr.licornesduswag.hcode.data.Texte;

/**
 * Un main de test
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class Main {
    public static void main(String[] args) {
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
        
        
        
        
        
        
        //Serializer
        Serializer serial = new Serializer(piece);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			serial.toZip("test.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(piece);
    }
}
