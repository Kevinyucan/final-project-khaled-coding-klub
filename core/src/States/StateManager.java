/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;


/**
 *
 * @author hadik9595
 */
public class StateManager {
    
    // instance variable
    private Stack<State> states;
    
    /**
     * constructor for the state manager
     */
    public StateManager(){
        states = new Stack<State>();
    }
    
    /**
     * method for switching states
     * @param s 
     */
    public void push(State s){
        states.push(s);
    }
    
    /**
     * method for switching states
     */
    public void pop(){
        State s = states.pop();
        s.dispose();
    }
    
    /**
     * method for setting states
     * @param s 
     */
    public void set(State s){
        pop();
        push(s);
    }
    
    /**
     * method for updating states
     * @param deltaTime 
     */
    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }
    
    /**
     * method for rendering states
     * @param batch 
     */
    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }

    /**
     * method for handling input of states
     */
    public void handleInput() {
        states.peek().handleInput();
    }

    /**
     * method for resizing the screen
     * @param width of the screen
     * @param height of the screen
     */
    public void resize(int width, int height) {
        states.peek().resize(width, height);
    }
}
    

