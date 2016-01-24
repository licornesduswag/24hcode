package fr.licornesduswag.ui;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AffichageTexte {
	private static int line;
	private static boolean defil;
	
	public static void affiche(String[] lines, Graphics g){
		g.setColor(Color.white);
		g.fillRoundRect(5, 485, 790, 105, 5);
		g.setColor(Color.black);
		if (lines.length > 3){
			defil = true;
		}
		if(defil){
			if(lines.length  > line){
				g.drawString(lines[line], 10, 455);
			}		
			if(lines.length > line +1){
				g.drawString(lines[line+1], 10, 455 + 24);
			}
			if(lines.length > line +2){
				g.drawString(lines[line+2], 10, 455 + 48);
			}
			if(line == lines.length - 2){
				defil = false;
			}
		}
		else {
			if(lines.length > 0){
				g.drawString(lines[0], 10, 455);
			}		
			if(lines.length > 1){
				g.drawString(lines[1], 10, 455 + 24);
			}
			if(lines.length > 2){
				g.drawString(lines[2], 10, 455 + 48);
			}
		}
	}
	
	
	public static void nextLine(){
		line++;
	}
	
	public static void reset(){
		line = 0;
	}
	
	public static boolean isDefil(){
		return defil;
	}
}
