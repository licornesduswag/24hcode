package fr.licornesduswag.hcode.data;

import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageStore implements ImageStoreInterface {
	private HashMap<String, Image> images = new HashMap<String, Image>();
	
	public void addImage(String nom, InputStream is){
		try {
			images.put(nom, new Image(is, nom, false));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage(String nom){
		return(images.get(nom));
	}
	
	public void printAll(){
		for(String key : images.keySet()){
			System.out.println(key);
		}
	}
}
