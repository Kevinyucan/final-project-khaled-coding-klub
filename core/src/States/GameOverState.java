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
    
    private Texture bg;
    private Texture back;
    BitmapFont font = new BitmapFont();
    public int score;
    
    public GameOverState(StateManager gsm, int score){
        super(gsm);
        setCameraView(Game.WIDTH, Game.HEIGHT);
        setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        bg = new Texture("GameOverScreen.jpg");
        back = new Texture("back.png");
        
        this.score = score;
    }
    
    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        batch.draw(back, getViewWidth()-150, 100, 100, 100);
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
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            unproject(touch);
            // check if button is pressed
            float backX = getViewWidth()-150;
            float backY = 100;
            
            // if clicked in any of these areas
            if(touch.x > backX && touch.x < backX + 100 && touch.y > backY && touch.y < backY + 100){
                // create a new state over this state 
                StateManager gsm = getStateManager();
                gsm.push(new MenuState(gsm)); 
            }
            
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        back.dispose();
    }
    
}
