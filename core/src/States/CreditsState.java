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
public class CreditsState extends State {

    private Texture bg;
    private Texture back;
    
    public CreditsState(StateManager gsm){
        super(gsm);
        setCameraView(Game.WIDTH, Game.HEIGHT);
        setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        
        bg = new Texture("creditspage.jpg");
        back = new Texture("back.png");
    }
    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(getCombinedCamera());
        batch.begin();
        
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());
        batch.draw(back, 0, 0, 100, 100);
        
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
            
            if(touch.x > 0 && touch.x < 100 && touch.y > 0 && touch.y < 100){
                StateManager gsm = getStateManager();
                gsm.push(new MenuState(gsm)); 
            }
            
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
    
}
