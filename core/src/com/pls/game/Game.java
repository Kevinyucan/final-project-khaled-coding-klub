package com.pls.game;

import States.MenuState;
import States.State;

import States.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
//to draw stuff	
    private SpriteBatch batch;
	Texture img;
        
        private StateManager stateManager;
        
        //resolution of the screen
        public static final int WIDTH = 1000;
        public static final int HEIGHT = 800;
	
        
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("background.jpg");
                Gdx.gl.glClearColor(1,0 , 0, 1);
        
        
                stateManager = new StateManager();
                
                
                State firstScreen = new MenuState(stateManager);
                //set the screen (load first screen)
                stateManager.push(firstScreen);
                
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
