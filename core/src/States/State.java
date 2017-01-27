/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author hadik9595
 */
public abstract class State {
    
    // instance variables for the camera and state manager
    private OrthographicCamera cam;
    private StateManager stateManager;
    
    // create all of the abstract methods for state
    public abstract void render(SpriteBatch batch);
    public abstract void update(float deltaTime);
    public abstract void handleInput();
    public abstract void dispose();
    
    /**
     * Constructor for the state class
     * @param sm 
     */
    public State(StateManager sm){
        // sets variable fot state manager and create the camera
        stateManager = sm;
        cam = new OrthographicCamera();
        
    }
    
    /**
     * gets the state manager
     * @return the state manager
     */
    public StateManager getStateManager(){
        return stateManager;
    }
    
    /**
     * set the camera's view
     * @param width of the camera
     * @param height of the camera
     */
    public void setCameraView(float width, float height) {
        cam.setToOrtho(false, width, height);
        cam.update();
    }
    
    /**
     * set the camera's current position
     * @param x value of camera
     * @param y value of camera
     */
    public void setCameraPosition(float x, float y) {
        cam.position.x = x;
        cam.position.y = y;
        cam.update();
    }
    
    /**
     * get combined camera
     * @return combined camera
     */
    public Matrix4 getCombinedCamera() {
        return cam.combined;
    }
    
    /**
     * move x position of camera
     * @param x position of camera
     */
    public void moveCameraX(float x) {
        cam.position.x = x;
        cam.update();
    }
    
    /**
     * get x position of camera
     * @return x position of camera
     */
    public float getCameraX() {
        return cam.position.x;
    }

    /**
     * get y position of camera
     * @return y position of camera
     */
    public float getCameraY() {
        return cam.position.y;
    }

    /**
     * get the camera's width
     * @return the camera's width
     */
    public float getViewWidth() {     
        return cam.viewportWidth;
       
    }

    /**
     * get the camera's height
     * @return the camera's height
     */
    public float getViewHeight() {
        return cam.viewportHeight;
    }

    /**
     * unproject touch coordinates
     * @param touch coordinates
     */
    public void unproject(Vector3 touch) {
        cam.unproject(touch);
        
    }
    
    /**
     * resize the screen
     * @param width of the screen
     * @param height of the screen
     */
    public void resize(int width, int height){
        cam.setToOrtho(false, width, height);
        cam.update();
    }
    
    
    
}
