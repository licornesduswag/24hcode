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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Une pièce, contenant des actes
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class Piece implements Serializable{
    public Piece() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String nom;
    
    private ArrayList<Personnage> personnages;
    private ArrayList<Acte> actes;
    
    // Constructeur

    public Piece(String nom, ArrayList<Acte> actes, ArrayList<Personnage> personnages) {
        this.nom = nom;
        this.actes = actes;
        this.personnages = personnages;
    }

    
    
    // Getters & setters

    public ArrayList<Personnage> getPersonnages() {
		return personnages;
	}

	public void setPersonnages(ArrayList<Personnage> personnages) {
		this.personnages = personnages;
	}

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Acte> getActes() {
        return actes;
    }

    public void setActes(ArrayList<Acte> actes) {
        this.actes = actes;
    }

    @Override
	public String toString() {
		return "Piece [nom=" + nom + ", personnages=" + personnages + ", actes=" + actes + "]";
	}
    
    
}
