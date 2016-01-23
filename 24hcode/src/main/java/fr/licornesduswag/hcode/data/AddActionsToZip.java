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

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class AddActionsToZip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Serializer s = new Serializer().fromZip("Ressources/piece.zip", new FakeImageStore());
            Piece p = s.getPiece();
            
            System.out.println(p);
            
            // Ajout d'un déplacement
            ArrayList<Contenu> c = p.getActes().get(0).getScenes().get(0).getDialogues().get(0).getRepliques().get(0).getContenu();
            System.out.println(c);
            
            c.add(new ActionDeplacement(200, 100, "SGANARELLE", "Sganarelle se déplace..."));
            
            c = p.getActes().get(0).getScenes().get(0).getDialogues().get(0).getRepliques().get(1).getContenu();
            c.add(new ActionDeplacement(250, 100, "MARTINE", "Martine se déplace à son tour..."));
            
            System.out.println(p);
            
            new Serializer(p).toZip("Ressources/piece_actions.zip", "../sprites/Medecin malgre lui/Persos");
            
        } catch (IOException ex) {
            Logger.getLogger(AddActionsToZip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
