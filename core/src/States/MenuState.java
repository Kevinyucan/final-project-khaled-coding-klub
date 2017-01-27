/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.pls.game.Game;


/**
 *
 * @author hadik9595, guanv6321
 */
public class MenuState extends State {
    
    // instance variables
    private Texture bg;
    private Texture button;
    private Texture button2;
    private Texture button3;
      
    private int highScore;
    
    
    /**
     * Constructor to initialize variables
     * @param gsm the state of which is being passed in
     */
    public MenuState(StateManager gsm){
       // since this is extended to state, all inheritance is passed here 
       super(gsm);
       // sets camera view to fit the entire screen that way coordinates are aligned
       setCameraView(Game.WIDTH, Game.HEIGHT);
       setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
     
        // assigns a Texture for each variable 
        bg = new Texture("main.png");
        button = new Texture("button.jpg");
        button2 = new Texture("instructions.png");
        button3 = new Texture("credits.jpg");
        
                                                        
    }

    @Override
    public void render(SpriteBatch batch) {
        
        // Sets the projection matrix to be used by this Batch
         batch.setProjectionMatrix(getCombinedCamera());
        //start drawing stuff 
        batch.begin();
        //draws the score
        
        // the getviewwidth and height commands are used to stretch the screen according to your screen (phone or pc)
        batch.draw(bg, 0, 0,getViewWidth(), getViewHeight());
        batch.draw(button, getViewWidth() / 2 - button.getWidth() / 2, getViewHeight() / 2);
        batch.draw(button2, getViewWidth()/2 - 100, getViewHeight()/2 - 130, 200, 100);
        batch.draw(button3, getViewWidth() / 2 - 100, 100, 200, 100);
        
        
        // to match the button and centre it!
        //batch.draw(button,0,0 ,   46, 66);
        //font.draw(batch, "" + highScore, getViewWidth()/2, getViewHeight()-100);
        batch.end();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
       // Get the mouse click/touch position
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Convert that point to "game coordinates"
            unproject(touch);
            // Check if the button is pressed
            float buttonX = getViewWidth() / 2 - button.getWidth() / 2;
            float buttonY = getViewHeight() / 2;
            float buttonX2 = getViewWidth()/2 -100;
            float buttonY2 = getViewHeight()/2 - 130;
            float buttonX3 = getViewWidth()/2 - 100 ;
            float buttonY3 = 100;
            
            // if play button is clicked
            if (touch.x > buttonX && touch.x < buttonX + button.getWidth()
                    && touch.y > buttonY && touch.y < buttonY + button.getHeight()) {
                StateManager gsm = getStateManager();
                // change to playing screen
                gsm.push(new PlayState(gsm));
                
            }
            
            // if instructions button is pressed
            if(touch.x > buttonX2 && touch.x < buttonX2 + 200 
                    && touch.y > buttonY2 && touch.y < buttonY2 + 100){
                StateManager gsm = getStateManager();
                // change to instructions screen
                gsm.push(new HowToState(gsm));                          
            }

            // if credits button is pressed
            if(touch.x > buttonX3 && touch.x < buttonX3 + 200
                    && touch.y > buttonY3 && touch.y < buttonY3 + 100 ){
                StateManager gsm = getStateManager();
                // change to credits screen
                gsm.push(new CreditsState(gsm));
            }
                        
        }
        
    }
        
    
    public void updateScore(){
          Preferences pref = Gdx.app.getPreferences("highscore");
       highScore = pref.getInteger("highscore", 0);
    }

    @Override
    public void update(float deltaTime) {
      
    }

    @Override
    public void dispose() {
        // dispose the background and all buttons
        bg.dispose();
        button.dispose();
        button2.dispose();
        button3.dispose();
    }
}
