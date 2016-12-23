/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author hadik9595
 */
public class MenuState extends State {
        private Texture bg;
    private Texture button;
    
    
    private int highScore;
    //for high score
    private BitmapFont font;
    
    //passes in the gamestate manager 
    
    public MenuState(StateManager gsm){
       super(gsm);
       
     
       
       
       //stores the highscore... "highscore" is the name of file being stored
       Preferences pref = Gdx.app.getPreferences("highscore");
       highScore = pref.getInteger("highscore", 0);
       
       highScore = 0;
       //creates default 15pt Arial font
       font = new BitmapFont();
                                                        
    }

    @Override
    public void render(SpriteBatch batch) {
        
        //start drawing stuff 
        batch.begin();
        //draws the score
        
        // the getviewwidth and height commands are used to stretch the screen according to your screen (phone or pc)
        //batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        // to match the button and centre it!
        //batch.draw(button, getViewWidth()/2 - button.getWidth(), getViewHeight()/2 - button.getHeight());
        //font.draw(batch, "" + highScore, getViewWidth()/2, getViewHeight()-100);
        batch.end();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
          
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
        bg.dispose();
        button.dispose();
    }
}
