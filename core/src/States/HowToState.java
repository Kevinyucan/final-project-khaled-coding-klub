/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pls.game.Game;

/**
 *
 * @author guanv6321
 */
public class HowToState extends State {
    
    // instance variables for background and back button
    private Texture bg;
    private Texture back;
    
    /**
     * Constructor to initialize variables
     * @param gsm the state of which is being passed in
     */
    public HowToState(StateManager gsm){
        // since this is extended to state, all inheritance is passed here
        super(gsm);
        // sets camera view to fit the entire screen that way coordinates are aligned
        setCameraView(Game.WIDTH, Game.HEIGHT);
        setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        
        // sets image for the background and button
        bg = new Texture("howto.jpg");
        back = new Texture("back.png");
        
    }
    
    @Override
    public void render(SpriteBatch batch){
        // Sets the projection matrix to be used by this Batch
        batch.setProjectionMatrix(getCombinedCamera());
        // Calls the batch to draw
        batch.begin();
        
        // draws the background and back button, and sets its size
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        batch.draw(back, getViewWidth()-120, 50, 100, 100);
        
        batch.end();
    }
    
    @Override
    public void dispose(){
        // dispose background and back button
        bg.dispose();
        back.dispose();
    }

    @Override
    public void update(float deltaTime) {
        
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            // Get the mouse click/touch position
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Convert that point to "game coordinates"
            unproject(touch);
            
            // sets starting coordinates of button
            float backX = getViewWidth()-120;
            float backY = 50;
            
            // if button is clicked
            if(touch.x > backX && touch.x < backX + 100 && touch.y > backY && touch.y < backY + 100){
                // change to main menu screen  
                StateManager gsm = getStateManager();
                gsm.push(new MenuState(gsm)); 
                // if clicked in any other area
            }else{
                // change to playing screen
                StateManager gsm = getStateManager();
                gsm.push(new PlayState(gsm)); 
            }
            
        }
    }
}
