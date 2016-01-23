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
package fr.licornesduswag.hcode.data;

import java.util.ArrayList;

/**
 *
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class TestIterator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PieceIterator it = new PieceIterator(fakePiece());
        
        Object obj;
        
        while((obj = it.next()) != null) {
            System.out.println(obj.getClass().getName());
        }
    }
    
    
    public static Piece fakePiece() {
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
        
        return piece;
    }
    
}
