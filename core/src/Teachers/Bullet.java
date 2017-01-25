/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author yuk4142????
 */
public class Bullet {


    private float currentX;
    private float currentY;


    private float speed;
    private Rectangle bulletBoundary;
    private Texture picture;
    private float direction;
    public boolean shot;

    /**
     * Bullet constructor
     * @param currentX the x coordinate of bullet
     * @param currentY the y coordindate of bullet
     * @param speed the speed the bullet is moving
     * @param damage damage bullet does
     * @param picture Texture
     */
    public Bullet(float currentX, float currentY, float speed, float damage, Texture picture) {
        //initialize variables
        this.currentX = currentX;
        this.currentY = currentY;
        this.speed = speed;
        this.picture = picture;
        //create boundary for the bullet
        bulletBoundary = new Rectangle(currentX, currentY, 25, 40);



    }
//    //creating the paths for bullet
//        public Array<Vector2> getRandomPath() {
//            //create array of paths coordinates
//        Array<Vector2> path = new Array<Vector2>();
//        
//            
//            
//           
//        return path;
//
//    }

    /**
     * Returns X coordinate of bullet
     * @return x coordinate of bullet
     */
    public float getX() {
        return currentX;
    }

    /**
     * Returns y coordinate of bullet
     * @return y coordinate of bullet
     */
    public float getY() {
        return currentY;
    }
    
    public boolean getShot(){
        return shot;
    }

    
    public void render(SpriteBatch batch) {
        batch.begin();
        //draw bullet
        if(!shot){
        batch.draw(picture, currentX, currentY, 25, 25);
        }
        batch.end();

        // 2 d straight projectile java
        //http://gamedev.stackexchange.com/questions/84017/how-to-move-an-object-uniformly-from-one-point-to-another-at-a-fixed-angle
//             this.time = this.time + deltaTime;
//
//    // Vertical : Speed * Sine * Angle
//    double vy = (this.speed * Math.sin(this.angle)) + this.ax*this.time ;
//    // Horizontal : Speed * Cosine * Angle
//    double vx = (this.speed * Math.cos(this.angle)) + this.ay*this.time;
//
//    this.x = this.x + vx*this.time;
//    this.y = this.y + vy*this.time + this.ay*(this.time*this.time); 
//
//    vx += this.ax * this.time;
//    vy += this.ay * this.time;
    }

    public void update(float deltaTime) {

     
        currentX += deltaTime * speed;
      
        currentY = (currentY + (speed * MathUtils.sin(direction)));
        bulletBoundary.setPosition(currentX, currentY);
//        System.out.println("Bullet X: " + currentX + " Bullet Y: " + currentY);
    }

    /**
     * Boolean collides method
     * @param a Student colliding with
     * @return true if bullet has collided with student
     */
    public boolean collides(Student a) {
        if (bulletBoundary.overlaps((a.getBoundaries()))) {
            return true;
        }
        return false;
    }
}
