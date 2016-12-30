package com.pls.game;

import States.MenuState;
import States.State;

import States.StateManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class Game extends ApplicationAdapter {


    
    private SpriteBatch batch;
  
 
    private StateManager stateManager;

    //resolution of the screen
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    //The price to hire teachers
    public static final int SCIENCE = 150;
    public static final int MATH = 200;
    public static final int GYM = 75;
    public static final int ART = 100;

    @Override
    public void create() {
        batch = new SpriteBatch();

        Gdx.gl.glClearColor(0, 0, 0, 0);

        stateManager = new StateManager();

        State firstScreen = new MenuState(stateManager);
        //set the screen (load first screen)
        stateManager.push(firstScreen);

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        
        // handle input
        stateManager.handleInput();
        // update the game states
        stateManager.update(Gdx.graphics.getDeltaTime());
        // draw the screen
        stateManager.render(batch);

    }

    @Override
    public void dispose() {
        batch.dispose();

    }
    
    



 
}
