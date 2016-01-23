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
import fr.licornesduswag.hcode.utils.StringCleaner;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author Alban
 *
 */
public class SAXContentHandler extends DefaultHandler {

	private String pClass = "";
 
    private Piece piece = new Piece("truc");
    
    private int numActe = 1;
    private Acte acte = null;
    
    private int numScene = 1;
    private Scene scene = null;

    public Piece getPiece() {
        return piece;
    }

	/**
	 * Evenement recu a chaque fois que l'analyseur rencontre des caracteres (entre
	 * deux balises).
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub.
        
        if (pClass.equals("Chapitre")) {
		
            String contenuBalise  = new String(ch, start, length);
            String filtered = StringCleaner.clean(contenuBalise);

            if (!filtered.equals("")) {
                if (filtered.startsWith("Acte")) {
                    // On clos l'ancien acte et on en crée un nouveau
                    if (acte != null) {
                        piece.getActes().add(acte);
                    }
                    acte = new Acte(numActe);
                    numActe++;
                    numScene = 1;
                    
                } else if (filtered.startsWith("Scène")) {
                    // On clos l'ancienne scène et on en crée une nouvelle
                    if (scene != null) {
                        acte.getScenes().add(scene);
                    }
                    scene = new Scene(numScene);
                    numScene++;
                }
            }
        }
	}

	/**
	 * Fin de lanalyse du document
	 * @throws SAXException
	 */
	@Override
	public void endDocument() throws SAXException {
		// Ajout dernière scène à l'acte
        
        if (scene != null) {
            acte.getScenes().add(scene);
        }
        
        if (acte != null) {
            piece.getActes().add(acte);
        }

	}

	/**
	 * Evenement envoyé à la fermeture d'une balise
	 * @param uri
	 * @param localName
	 * @param qName
	 * @throws SAXException
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
        pClass = "";
	}

	/**
	 * Evenement envoye au demarrage du parse du flux xml.
	 * @throws SAXException
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Début de l'annalyse du document .xml");

	}
	/**
	 * Evenement recu a chaque fois que l'analyseur rencontre une balise XML ouvrante.
	 * @param uri
	 * @param localName
	 * @param qName
	 * @param atts
	 * @throws SAXException
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if ("p".equals(localName)) {
            pClass = atts.getValue("class");
        }
	}
}
