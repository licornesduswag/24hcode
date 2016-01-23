package fr.licornesduswag.ui;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MainPersonnage extends BasicGame {
	GameContainer container;
	private float x = 300, y = 300;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	
	
    public MainPersonnage() {
        super("Test deplacement bonhomme");
    }
    
    public static void main(String[] args)
    {
    	AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new MainPersonnage());
			appgc.setDisplayMode(800, 600, false);
	    	appgc.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	this.container = container;
    	container.setShowFPS(false);
    	SpriteSheet spriteSheet = new SpriteSheet("../sprites/Medecin malgre lui/Persos/1.png", 32, 32);
    	for(int i=0; i<8; i++)
    	{
    		animations[i] = new Animation();
    	}
    	animations[4].addFrame(spriteSheet.getSprite(0, 3), 500);
		animations[4].addFrame(spriteSheet.getSprite(2, 3), 500);
    	animations[5].addFrame(spriteSheet.getSprite(0, 1), 500);
    	animations[5].addFrame(spriteSheet.getSprite(2, 1), 500);
    	animations[6].addFrame(spriteSheet.getSprite(0, 0), 500);
    	animations[6].addFrame(spriteSheet.getSprite(2, 0), 500);
    	animations[7].addFrame(spriteSheet.getSprite(0, 2), 500);
    	animations[7].addFrame(spriteSheet.getSprite(2, 2), 500);
    	animations[0].addFrame(spriteSheet.getSprite(1, 3), 500);
    	animations[1].addFrame(spriteSheet.getSprite(1, 1), 500);
    	animations[2].addFrame(spriteSheet.getSprite(1, 0), 500);
    	animations[3].addFrame(spriteSheet.getSprite(1, 2), 500);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	g.setBackground(Color.cyan);
    	g.setColor(new Color(0, 0, 0, .5f));
        g.fillOval(x - 8, y - 4, 16, 8);
    	g.drawAnimation(animations[direction + (moving ? 4 : 0)], x-16, y-30);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	if (this.moving) {
            switch (this.direction) {
                case 0: this.y -= .1f * delta; break;
                case 1: this.x -= .1f * delta; break;
                case 2: this.y += .1f * delta; break;
                case 3: this.x += .1f * delta; break;
            }
        }
    }
    
    @Override
    public void keyReleased(int key, char c) {
    	this.moving = false;
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
    
    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_UP:    this.direction = 0; this.moving = true; break;
            case Input.KEY_LEFT:  this.direction = 1; this.moving = true; break;
            case Input.KEY_DOWN:  this.direction = 2; this.moving = true; break;
            case Input.KEY_RIGHT: this.direction = 3; this.moving = true; break;
        }
    }
}
