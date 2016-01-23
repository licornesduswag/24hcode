/**
 * The MIT License
 *
 * Copyright 2016 Rousseau Alban (Nabnab9) alban.rousseau9 at gmail.com.
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
package fr.licornesduswag.hcode.SAX;

import fr.licornesduswag.hcode.data.Acte;
import fr.licornesduswag.hcode.data.Piece;
import fr.licornesduswag.hcode.data.Scene;


/**
 * @author Alban
 *
 */
public class MainSAX {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Piece p = PieceLoader.load("../pieces/html/medecinMalgresLui.xml");
        
        for (Acte a : p.getActes()) {
            System.out.println("acte "+a.getNumero());
            for (Scene s : a.getScenes()) {
                System.out.println("scene " + s.getNumero());
            }
        }
        //Serializer s = new Serializer(p);
    }

}
