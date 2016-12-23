/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author hadik9595
 */
public abstract class State {
    
    private StateManager stateManager;
    
    public abstract void render(SpriteBatch batch);
    public abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void dispose();
    
    public State(StateManager sm){
        stateManager = sm;
    }
    
    
    public StateManager getStateManager(){
        return stateManager;
    }
    
    
    
}
