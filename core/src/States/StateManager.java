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
    
    private Stack<State> states; 
    
     public StateManager(){
       //we are ready to use it, initializes it
       states = new Stack<State>();
   }
   
   
   public void push(State s){
       states.push(s);
   }
   
   public void pop(){
       //pop means it gets rid of current screen and goes back to previous one
       State s = states.pop();
       s.dispose();
       
   }
   
   public void set(State s){
       pop();
       push(s);
   }
   
   public void update(float deltaTime){
       states.peek().update(deltaTime);
       
   }
   
   public void render(SpriteBatch batch){
       states.peek().render(batch);
   }

    public void handleInput() {
        //updates any changes in terms of altering coordinates 
        states.peek().handleInput();
    }
}
    

