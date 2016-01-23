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

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

import fr.licornesduswag.hcode.data.Acte;
import fr.licornesduswag.hcode.data.Contenu;
import fr.licornesduswag.hcode.data.Replique;
import fr.licornesduswag.hcode.data.Texte;
/**
 * @author Alban
 *
 */
public class SAXContentHandler implements ContentHandler{

	private Locator locator;
	private ArrayList<Acte> listeActes = new ArrayList<>();
	


	/**
	 * Constructeur par défaut
	 */
	public SAXContentHandler() {
		// TODO Auto-generated constructor stub
		super();
		locator = new LocatorImpl();
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
		// TODO Auto-generated method stub
		String contenuBalise = new String(ch, start, length);
		System.out.println("#PCDATA : " + contenuBalise);
		ArrayList<Contenu> liste = new ArrayList<Contenu>();
		liste.add(new Texte(contenuBalise));
		Replique r = new Replique(liste);

	}

	/**
	 * Fin de lanalyse du document
	 * @throws SAXException
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Fin de l'analse du document.");

	}

	/* (non-Javadoc)
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		System.out.print("Fermeture de la balise : " + localName);
		
		// name space non null
		if ( ! "".equals(uri)) { 
			System.out.print("appartenant a l'espace de nommage : " + localName);
		}

		System.out.println();

	}

	/**
	 * Fin de traitement de l'espace de nommage.
	 * @param prefix
	 * @throws SAXException
	 */
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Fin de traitement de l'espace de nommage : " + prefix);

	}

	/**
	 * Recu chaque fois que des caracteres d'espacement peuvent etre ignores au sens de
	 * XML. C’est-a-dire que cet evenement est envoye pour plusieurs espaces se succedant,
	 * les tabulations, et les retours chariot se succedant ainsi que toute combinaison de ces
	 * trois types d'occurrence.
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 */
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("espaces inutiles rencontres : ..." + new String(ch, start, length) +  "...");

	}

	/**
	 * Rencontre une instruction de fonctionnement.
	 * @param target
	 * @param data
	 * @throws SAXException
	 */
	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Instruction de fonctionnement : " + target);
		System.out.println("  dont les arguments sont : " + data);

	}
	/**
	 * Definition du locator qui permet a tout moment pendant l'analyse, de localiser
	 * le traitement dans le flux. Le locator par defaut indique, par exemple, le numero
	 * de ligne et le numero de caractere sur la ligne.
	 * @param locator
	 */
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		this.locator = locator;

	}

	/**
	 * je sais pas ce que c'est
	 * @param name
	 * @throws SAXException
	 */
	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		System.out.println("Ouverture de la balise : " + localName);

		if ( ! "".equals(uri)) { // espace de nommage particulier
			System.out.println("  appartenant a l'espace de nom : "  + uri);
		}

		System.out.println("  Attributs de la balise : ");

		// on parcourt la liste des attributs
		for (int index = 0; index < atts.getLength(); index++) { 

			System.out.println("     - " +  atts.getLocalName(index) + " = " + atts.getValue(index));
		}

	}

	/**
	 * Debut de traitement dans un espace de nommage.
	 * @param prefix
	 * @param uri
	 * @throws SAXException
	 */
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Traitement de l'espace de nommage : " + uri + ", prefixe choisi : " + prefix);

	}



	/**
	 * @return the locator
	 */
	public Locator getLocator() {
		return locator;
	}



	/**
	 * @param locator the locator to set
	 */
	public void setLocator(Locator locator) {
		this.locator = locator;
	}

}
