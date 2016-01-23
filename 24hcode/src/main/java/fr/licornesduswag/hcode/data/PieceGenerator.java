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

import fr.licornesduswag.hcode.SAX.PieceLoader;
import io.herrmann.generator.Generator;

/**
 *
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class PieceGenerator extends Generator<Object> {

    private Piece piece;

    public PieceGenerator(Piece piece) {
        this.piece = piece;
    }
    
    
    
    @Override
    protected void run() throws InterruptedException {
        yield(piece);
        
        for (Acte a : piece.getActes()) {
            yield(a);
            for(Scene s : a.getScenes()){
            	yield(s);
            	for(Dialogue d : s.getDialogues()){
            		yield(d);
            		for(Replique r : d.getRepliques()){
            			yield(r);
            			for(Contenu c : r.getContenu()){
            				yield(c);
            			}
            		}
            	}
            }
            
        }
    }

    public static void main(String[] args) {
        PieceGenerator pg = new PieceGenerator(PieceLoader.load("../pieces/html/medecinMalgresLui.xml"));
        
        for (Object o : pg) {
            System.out.println(o.getClass().getName());
        }
    }
}
