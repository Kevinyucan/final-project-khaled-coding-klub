/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pls.game.Game;

/**
 *
 * @author guanv6321
 */
public class GameOverState extends State {
    
    // instance variables
    private Texture bg;
    private Texture back;
    BitmapFont font = new BitmapFont();
    public int score;
    
    /**
     * Constructor to initialize the background, back button, and score
     * @param gsm the state of which is being passed in
     * @param score 
     */
    public GameOverState(StateManager gsm, int score){
        // since this is extended to state, all inheritance is passed here
        super(gsm);
        // sets camera view to fit the entire screen that way coordinates are aligned
        setCameraView(Game.WIDTH, Game.HEIGHT);
        setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        
        // assigns a Texture to each variable
        bg = new Texture("GameOverScreen.jpg");
        back = new Texture("back.png");
        
        this.score = score;
    }
    
    @Override
    public void render(SpriteBatch batch) {
        // Sets the projection matrix to be used by this Batch
        batch.setProjectionMatrix(getCombinedCamera());
        // Calls the batch to draw
        batch.begin();
        // Draws the background and back button, and sets its size according to screen
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        batch.draw(back, getViewWidth()-150, 100, 100, 100);
        
        // Draws the score
        font.setColor(Color.RED);
        font.draw(batch, "" + score, getViewWidth()/2 + 100, getViewHeight()/2);
        
        batch.end();
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
            float backX = getViewWidth()-150;
            float backY = 100;
            
            // if button is clicked
            if(touch.x > backX && touch.x < backX + 100 && touch.y > backY && touch.y < backY + 100){
                // change to main menu screen
                StateManager gsm = getStateManager();
                gsm.push(new MenuState(gsm)); 
            }
            
        }
    }

    @Override
    public void dispose() {
        // dispose background and back button
        bg.dispose();
        back.dispose();
    }
    
}
