package fr.licornesduswag.hcode.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;




/**
 * Classe serialisant nos objets java en XML
 * @author Kelian Duval (Spritix) 
 */

public class Serializer implements Serializable{

	// Constantes
	private static final int BUFFER_SIZE = 2048;
	private static final String ZIP_XML_FILE = "piece.xml";
	private String ZIP_IMAGE_DIR = "sprites/"; // Ne pas oublier le slash Ã  la fin du nom de dossier

	private Piece piece;

	public Serializer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Serializer(Piece piece) {
		super();
		this.piece = piece;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public void toZip(String nom, String spriteDir) throws IOException {
		try {
			// Ecriture du fichier XML en mÃ©moire
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			toXml(output);
			byte[] xmlBytes = output.toByteArray();

			// CrÃ©ation du flux de sortie
			BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream(nom));

			try (ZipOutputStream out = new ZipOutputStream(buff)) {

				// Configuration du fichier ZIP
				out.setMethod(ZipOutputStream.DEFLATED);
				out.setLevel(9);

				// Ecriture du fichier XML
				BufferedInputStream bi = new BufferedInputStream(new ByteArrayInputStream(xmlBytes), BUFFER_SIZE);
				ZipEntry xmlEntry = new ZipEntry("piece.xml");
				out.putNextEntry(xmlEntry);

				byte data[] = new byte[BUFFER_SIZE];
				int xmlCount;
				while ((xmlCount = bi.read(data, 0, BUFFER_SIZE)) != -1) {
					out.write(data, 0, xmlCount);
				}
				out.closeEntry();
				
				// Ecriture des images depuis un dossier
				if (spriteDir != null) {
					File directory = new File(spriteDir);
					File[] files = directory.listFiles();

					for (File file : files) {
						try (BufferedInputStream buffi = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE)) {

							// CrÃ©ation de l'entrÃ©e
							ZipEntry entry = new ZipEntry(ZIP_IMAGE_DIR + file.getName());
							out.putNextEntry(entry);

							// Ecriture du fichier
							int count;
							while ((count = buffi.read(data, 0, BUFFER_SIZE)) != -1) {
								out.write(data, 0, count);
							}

							// Fermeture de l'entrÃ©e
							out.closeEntry();
						}
					} // Fin de la boucle d'ecriture d'images
				}
			} // Fermeture du fichier zip

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Ecrit l'objet sous forme XML dans un flux
	 * @param stream Le flux dans lequel ecrire le document XML
	 */
	public void toXml(OutputStream stream) {
		try (XMLEncoder enc = new XMLEncoder(stream)) {
			enc.writeObject(this);
			enc.flush();
		}
	}

	/**
	 * Charge un jeu depuis un fichier XML
	 * @param stream Le flux Ã  partir duquel charger le fichier XML
	 * @return Le jeu chargÃ© (sans images)
	 */
	public static Serializer fromXml(InputStream stream) {
		Serializer serial;

		try (XMLDecoder dec = new XMLDecoder(stream)) {
			serial = (Serializer) dec.readObject();
		}

		return serial;
	}

	/**
	 * Charge un jeu depuis un fichier ZIP
	 * @param filepath Chemin vers le fichier zip
	 * @param imgStore Store d'images qui contenir les images chargÃ©es
	 * @return Le jeu chargÃ©
	 * @throws IOException 
	 */
	public static Serializer fromZip(String filepath) throws IOException {
		Serializer serial = null;

		// Ouverture du fichier zip
		try (ZipFile zf = new ZipFile(filepath)) {
			Enumeration<? extends ZipEntry> entries = zf.entries();

			// Pour chaque entrÃ©e dans le fichier zip
			while (entries.hasMoreElements()) {
				ZipEntry e = (ZipEntry) entries.nextElement();

				// Si c'est un le fichier XML on charge le jeu depuis le fichier
				if (e.getName().equals(ZIP_XML_FILE)) {
					serial = fromXml(zf.getInputStream(e));
				}
			}
		}

		return serial;
	}


	@Override
	public String toString() {
		return "Serializer [piece=" + piece + "]";
	}
}
