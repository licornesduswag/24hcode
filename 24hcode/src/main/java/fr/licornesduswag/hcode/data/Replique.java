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
 * Une réplique avec du contenu
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class Replique implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Replique() {
		super();
		// TODO Auto-generated constructor stub
	}

    public Replique(String parleur) {
        this.parleur = parleur;
        this.contenu = new ArrayList<>();
    }

	public Replique(ArrayList<Contenu> contenu, String parleur) {
		super();
		this.contenu = contenu;
		this.parleur = parleur;
	}

	private ArrayList<Contenu> contenu;
	private String parleur;
    
    // Constructeur

    public Replique(ArrayList<Contenu> contenu) {
        this.contenu = contenu;
    }
    
    // Getters & setters

    public ArrayList<Contenu> getContenu() {
        return contenu;
    }

    public void setContenu(ArrayList<Contenu> contenu) {
        this.contenu = contenu;
    }
    
    public String getParleur() {
		return parleur;
	}

	public void setParleur(String parleur) {
		this.parleur = parleur;
	}

    @Override
    public String toString() {
        return "Replique{" + "contenu=" + contenu + ", parleur=" + parleur + '}';
    }

    
    
    
}
