package fr.licornesduswag.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class Keyboard {
	Input input; 
	
	Keyboard(GameContainer container){
		input = container.getInput();
	}
	
        boolean keyDown(){
		if (input.isKeyDown(Input.KEY_SPACE))
		{
		    return true;
		}
		else
		{
                    return false;
		}
	}
	
	boolean keyUp(){
		if (!input.isKeyDown(Input.KEY_SPACE))
		{
		    return true;
		}
		else 
			return false;
	}
}

