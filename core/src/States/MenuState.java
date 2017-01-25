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
 * @author hadik9595
 */
public class MenuState extends State {
    private Texture bg;
    private Texture button;
    private Texture button2;
    private Texture button3;
    
    public boolean keyPressed;

    
    private int highScore;
    
    
    //passes in the gamestate manager 
    
    public MenuState(StateManager gsm){
       super(gsm);
       setCameraView(Game.WIDTH, Game.HEIGHT);
       setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
     
       
        bg = new Texture("main.png");
        button = new Texture("button.jpg");
        button2 = new Texture("instructions.png");
        button3 = new Texture("credits.jpg");
        
                                                        
    }

    @Override
    public void render(SpriteBatch batch) {
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
            
            if (touch.x > buttonX && touch.x < buttonX + button.getWidth()
                    && touch.y > buttonY && touch.y < buttonY + button.getHeight()) {
                StateManager gsm = getStateManager();

                // Created a new game state on top of the game state
                gsm.push(new PlayState(gsm));
                
            }
            
            //button #2 
            if(touch.x > buttonX2 && touch.x < buttonX2 + 200 
                    && touch.y > buttonY2 && touch.y < buttonY2 + 100){
                StateManager gsm = getStateManager();
                
                gsm.push(new HowToState(gsm));                          
            }

            
            if(touch.x > buttonX3 && touch.x < buttonX3 + 200
                    && touch.y > buttonY3 && touch.y < buttonY3 + 100 ){
                StateManager gsm = getStateManager();
                
                gsm.push(new CreditsState(gsm));
            }
                        
        }
        
    }
    
    
        
    
    
//     @Override
//    public boolean keyDown(int keycode) {
//        if (keycode == Input.Keys.XXX) {
//            keyPressed = true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean keyUp(int keycode) {
//        if (keycode == Input.Keys.) {
//            keyPressed = false;
//        }
//
//        return false;
//    }
    
    
    
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
        button3.dispose();
    }
}
