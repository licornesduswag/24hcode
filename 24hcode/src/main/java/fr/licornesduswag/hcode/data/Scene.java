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
 * Une scène de la pièce, contenant plusieurs dialogues
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class Scene implements Serializable{
    /**
	 * 
	 */
	String bg = "";
	String fg = "";
	private static final long serialVersionUID = 1L;

	public Scene() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int numero;
    
    private ArrayList<Dialogue> dialogues;
    
    // Constructeur

    public Scene(int numero, ArrayList<Dialogue> dialogues) {
        this.numero = numero;
        this.dialogues = dialogues;
    }

    public Scene(int numero) {
        this.numero = numero;
        this.dialogues = new ArrayList<>();
    }
    
    // Getters & setters

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(ArrayList<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }

    public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public String getFg() {
		return fg;
	}

	public void setFg(String fg) {
		this.fg = fg;
	}

	@Override
    public String toString() {
        return "Scene{" + "numero=" + numero + ", dialogues=" + dialogues + '}';
    }
    
}
