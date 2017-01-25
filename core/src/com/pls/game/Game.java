package com.pls.game;

import States.MainState;
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
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    //The price to hire teachers
    public static final int SCIENCE = 850;
    public static final int MATH = 400;
    public static final int PRINTER = 550;
    public static final int ENGLISH = 2000;
    
    public int studentAmount = 3;
    

    @Override
    public void create() {
        
        batch = new SpriteBatch();

        Gdx.gl.glClearColor(0, 0, 0, 0);

        stateManager = new StateManager();

        State firstScreen = new MainState(stateManager);
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
    
      
    public int getStudentAmount(){
        return studentAmount;
    }
    
    @Override
    public void resize(int width, int height){
       // stateManager.resize(width, height);
    }
    



 
}
