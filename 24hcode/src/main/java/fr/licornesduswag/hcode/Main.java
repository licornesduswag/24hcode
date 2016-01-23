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

import fr.licornesduswag.hcode.data.Acte;
import fr.licornesduswag.hcode.data.Contenu;
import fr.licornesduswag.hcode.data.Dialogue;
import fr.licornesduswag.hcode.data.Piece;
import fr.licornesduswag.hcode.data.Replique;
import fr.licornesduswag.hcode.data.Scene;
import fr.licornesduswag.hcode.data.Texte;
import java.util.ArrayList;

/**
 * Un main de test
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Salut !");
        
        // Texte
        Texte txt = new Texte("Roméo, oh Roméo !");
        
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
        actes.add(acte);
        Piece piece = new Piece("Romeo et Juliette", actes);
        
        System.out.println(piece);
    }
}
