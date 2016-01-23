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

import fr.licornesduswag.hcode.data.Piece;

/**
 * Itérateur qui renvoie des objets
 * 
 * ATTENTION: ce code est truffé de pièges, ne pas modifier si compréhension
 * partielle.
 * 
 * @author Romain Porte (MicroJoe) microjoe at mailoo.org
 */
public class PieceIterator {
	private Piece piece;

	private boolean shownPiece;
	private boolean shownActe;
	private boolean shownScene;
	private boolean shownDialogue;
	private boolean shownReplique;
	private boolean shownContent;

	private int currentActe 	= 0;
	private int currentScene	= 0;
	private int currentDialogue = 0;
	private int currentReplique = 0;
	private int currentContent 	= 0;


	public PieceIterator(Piece piece) {
		this.piece = piece;
	}    

	
	
	public Object next() {

		// Avant tout, on affiche la piece si ce n'est pas encore fait
		if (!shownPiece) {
			shownPiece = true;
			return piece;
		}

		// Puis le premier acte
		if (!shownActe) {
			shownActe = true;
			return piece.getActes().get(0);
		}

		
		//première scene 
		if(!shownScene){
			shownScene = true;
			return piece.getActes().get(0).getScenes().get(0);
		}

		//premier dialogue
		if(!shownDialogue){
			shownDialogue = true;
			return piece.getActes().get(0).getScenes().get(0).getDialogues().get(0);
		}

		//premiere replique
		if(!shownReplique){
			shownReplique = true;
			return piece.getActes().get(0).getScenes().get(0).getDialogues().get(0).getRepliques().get(0);
		}

		//premier contenu (Texte ou action for now)
		if(!shownContent){
			shownContent = true;
			return piece.getActes().get(0).getScenes().get(0).getDialogues().get(0).getRepliques().get(0).getContenu().get(0);
		}

		/*if(currentActe < piece.getActes().size()){
			if(currentScene < piece.getActes().get(currentActe).getScenes().size()){
				if(currentDialogue < piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().size()){
					if(currentReplique < piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue).getRepliques().size()){
						if(currentContent < piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue).getRepliques().get(currentReplique).getContenu().size()){
							//on peut afficher le contenu suivant dans la réplique courrante
							return piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue).getRepliques().get(currentReplique).getContenu().get(currentContent++);
						}else{
							//plus de contenu dispo, on essaye d'afficher la réplique suivante
							if(currentReplique < piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue).getRepliques().size()){
								currentContent = 0;
								return piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue).getRepliques().get(currentReplique++);
							}else{

								//plus de réplique à afficher, on essaye d'afficher le dialogue suivant
								if(currentDialogue < piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().size()){
									//du coup on reboot la réplique courante
									currentReplique = 0;
									return piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue++);
								}else{

									//plus de dialogue dispo, on essaye d'afficher la scene suivante
									if(currentScene < piece.getActes().get(currentActe).getScenes().size() ){
										//du coup on reboot le currentDialogue
										currentDialogue = 0;
										return piece.getActes().get(currentActe).getScenes().get(currentScene++);
									}else{

										//plus de scene dispo, on essaye d'afficher l'acte suivant
										currentActe++;
										if (currentActe  < piece.getActes().size()) {
											//du coup on reboot la scène courante
											currentScene = 0;
											return piece.getActes().get(currentActe);
										}else {

											// Plus d'acte à afficher, c'est la fin
											System.out.println("il n'y a plus d'acte");
											return null;
										}
									}
								}
							}
						}
					}
				}
			}
		}*/



		if (currentActe < piece.getActes().size()) {
			if (currentScene < piece.getActes().get(currentActe).getScenes().size()) {
				if(currentDialogue < piece.getActes().get(currentActe).getScenes().get(currentDialogue).getDialogues().size()){
					return piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue++);
				}else{
					currentDialogue = 0;
					currentScene++;
					if(currentScene < piece.getActes().get(currentActe).getScenes().size()){
						// On peut afficher la scène suivante dans l'acte courant
						return piece.getActes().get(currentActe).getScenes().get(currentScene);
					}
				}
				
				
			}
			else {
				// Plus de scènes disponibles, on essaye d'afficher l'acte suivant
				currentScene = 0;
				currentActe++;
				if (currentActe  < piece.getActes().size()) {

					return piece.getActes().get(currentActe);
				} 
				else {
					// Plus d'acte à afficher, c'est la fin
					return null;
				}
			}
		}
		
		
		/*if (currentActe < piece.getActes().size()) {
			if (currentScene < piece.getActes().get(currentActe).getScenes().size()) {
				if(currentDialogue < piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().size()){
					// On peut afficher la scène suivante dans l'acte courant
					return piece.getActes().get(currentActe).getScenes().get(currentScene).getDialogues().get(currentDialogue++);
				}else{
					currentDialogue = 0;
					currentScene ++;
					if(currentScene <piece.getActes().get(currentActe).getScenes().size()){
						return piece.getActes().get(currentActe).getScenes().get(currentScene);
					}
				}
			}
			else {
				// Plus de scènes disponibles, on essaye d'afficher l'acte suivant
				currentScene = 0;
				currentActe++;
				if (currentActe  < piece.getActes().size()) {

					return piece.getActes().get(currentActe);
				} 
				else {
					// Plus d'acte à afficher, c'est la fin
					return null;
				}
			}
		}*/




		return null;


	}
}
