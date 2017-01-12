/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author yuk4142p 
 */


public class Bullet {
    private boolean hit;
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int speed;
    private Texture picture;
    
    
    
    
    public Bullet(float startX, float startY, float endX, float endY, int speed, Texture picture){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.speed = speed;
        this.picture = picture;
        
        
        
        
        
    }
    //creating the paths for bullet
        public Array<Vector2> getRandomPath() {
            //create array of paths coordinates
        Array<Vector2> path = new Array<Vector2>();
        
            //adds random point, 
            //path.add(new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight())));
            path.add(new Vector2(startX, startY));
            path.add(new Vector2(endX, endY));
           
        return path;

    }
    public boolean hashit(){
        return hit;
    }
    
}
