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

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class Main extends BasicGame {
    TrueTypeFont font;
    Keyboard k = null;
    boolean test;
    boolean piece;
    
    public Main() {
        super("24hcode");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        gc.setShowFPS(false);
        k = new Keyboard(gc);
	// load font from a .ttf file
	try {
		InputStream inputStream	= ResourceLoader.getResourceAsStream("Ressources/Fonts/paper.ttf");
 
		Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		awtFont = awtFont.deriveFont(32f); // set font size
		font = new TrueTypeFont(awtFont, true);
 
	} catch (Exception e) {
		e.printStackTrace();
	}	
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if(k.keyDown() && !test && !piece){
           piece = true;
        }
        test = !k.keyUp();
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        if(!piece){
            grphcs.setBackground(Color.darkGray);
            grphcs.setColor(Color.white);
            grphcs.setFont(font);
            grphcs.drawString("24h du code", 10, 10);
            grphcs.drawString("Espace pour voir la pièce", 10, 550);
        }
        else{
            grphcs.setBackground(Color.cyan);
            grphcs.setColor(Color.darkGray);
            grphcs.setFont(font);
            grphcs.drawString("ceci est une pièce", 10, 10);
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
