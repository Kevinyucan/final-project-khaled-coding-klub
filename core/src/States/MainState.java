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
 * @author Kevin Yu
 */
public class MainState extends State {

    // instance variable for background
    private Texture bg;

    /**
     * Constructor to initialize variables 
     * @param gsm the state of which is being passed in
     */
    public MainState(StateManager gsm) {
        // since this is extended to state, all inheritance is passed here
        super(gsm);
        // sets camera view to fit the entire screen that way coordinates are aligned
        setCameraView(Game.WIDTH, Game.HEIGHT);
        setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        
        // assigns a Texture for background
        bg = new Texture("main.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        // Sets the projection matrix to be used by this Batch
        batch.setProjectionMatrix(getCombinedCamera());
        
        batch.begin();

        // draws the background and back button, and sets its size
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());

        batch.end();
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            // Get the mouse click/touch position
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            // Convert that point to "game coordinates"
            unproject(touch);
            // if screen is clicked
            if (touch.x > 0 && touch.x < getViewWidth() && touch.y > 0 && touch.y < getViewHeight()) {
                StateManager gsm = getStateManager();
                // changes to main menu
                gsm.push(new MenuState(gsm));
            }
        }
    }

    @Override
    public void dispose() {
        // dispose the background
        bg.dispose();
    }
}
