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
package fr.licornesduswag.RTFReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;

/**
 * @author Alban
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			File fichierSortie = rtfToHTML(new FileReader(new File("C:\\Users\\Alban\\git\\24hcode\\pieces\\MOLIERE-Le_medecin_malgre_lui-[Atramenta.net].rtf")), "fichier", "html" );
			
			System.out.println();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 
	 * @param rtf Le fichier en .rtf (fileReader(File))
	 * @param outFileName le nom du fichier de sortie (sans extension)
	 * @param typeSortie (html, txt for now)
	 * @return file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static File rtfToHTML(Reader rtf, String outFileName, String typeSortie) throws IOException, FileNotFoundException{
		JEditorPane p = new JEditorPane();
	    p.setContentType("text/rtf");
	    EditorKit kitRtf = p.getEditorKitForContentType("text/rtf");
	    try {
	        kitRtf.read(rtf, p.getDocument(), 0);
	        kitRtf = null;
	        EditorKit kitHtml = p.getEditorKitForContentType("text/"+typeSortie);
	        Writer writer = new StringWriter();
	        kitHtml.write(writer, p.getDocument(), 0, p.getDocument().getLength());
	        String contenuFichier = writer.toString();
	        File fichierSortie = new File(outFileName+"."+typeSortie);
			FileWriter fw = new FileWriter(fichierSortie);
			System.out.println("conversion en cours");
			fw.write(contenuFichier);
			System.out.println("conversion terminée");
			fw.close();
	        return fichierSortie;
	    } catch (BadLocationException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
