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
    
    private Texture bg;
    
    public HowToState(StateManager gsm){
        super(gsm);
        setCameraView(Game.WIDTH, Game.HEIGHT);
        setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        
        // sets image for the background
        bg = new Texture("howto.jpg");
        
    }
    
    @Override
    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        
        // draws the background
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        
        batch.end();
    }
    
    @Override
    public void dispose(){
        bg.dispose();
    }

    @Override
    public void update(float deltaTime) {
        
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            unproject(touch);
            if(touch.x > 0 && touch.x < getViewWidth() && touch.y > 0 && touch.y < getViewHeight()){
                StateManager gsm = getStateManager();
                gsm.push(new PlayState(gsm)); 
            }
            
        }
    }
}
