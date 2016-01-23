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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class TestGame extends BasicGame {

    public TestGame() {
        super("test");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new TestGame());
            app.setDisplayMode(600, 400, false);
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(TestGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
