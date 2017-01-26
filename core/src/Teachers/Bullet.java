/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author yuk4142
 */
public class Bullet {

    //create private variables
    //the x and y coordinates
    private float currentX;
    private float currentY;
    //speed of the bullet
    private float speed;
    //bullet boundary
    private Rectangle bulletBoundary;
    //picture for bullet
    private Texture picture;
    //storing if bullet has been shot
    public boolean shot;

    /**
     * Bullet constructor
     * @param currentX the x coordinate of bullet
     * @param currentY the y coordinate of bullet
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
    
    /**
     * 
     * @return if bullet has been shot/rendered
     */
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

    }

    public void updateBullet(float deltaTime, Student a) {
      
        //fire bullet according to where the bullet is to the student
             if(currentX < a.getX()){
                  currentX += deltaTime * speed;
             }
             if(currentX > a.getX()){
                  currentX -= deltaTime * speed;
             }
             if(currentY < a.getY()){
                 currentY += deltaTime * speed;
             }
             if(currentY > a.getY()){
                  currentY -= deltaTime * speed;
             }
        bulletBoundary.setPosition(currentX, currentY);

    }

    /**
     * Boolean collides method
     * @param a Student colliding with
     * @return true if bullet has collided with student
     */
    public boolean collides(Student a) {
        //if bullet overlaps student
        if (bulletBoundary.overlaps((a.getBoundaries()))) {
            //return true
            return true;
        }
        //else return false
        return false;
    }
}
