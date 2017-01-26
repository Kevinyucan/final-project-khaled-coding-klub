/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Khaled
 */
public class Student extends Unit {

    //instance variable for student's speed
    private int movement;
    //instance variable for student's health (grade) 
    private int health;
    //
    private SpriteBatch batch;
    //The character essentially holds x and y coordinates 
    private Vector2 velocity = new Vector2();
    //Indicates how many pixels per seconds we move by, (tolerance is a way to notify once we reach 3 pixels before a waypoint)

    //an array of set points  (holds x and y ) 
    public Student(int x, int y, int movement, int health, String textureName) {
        //takes the inheritance of super class, in this case it is x, y , texture name and movement speed
        super(x, y, textureName, movement);
        //batch initialized in order to be used later when drawing health
        batch = new SpriteBatch();

        //sets the health of student 
        this.health = health;
        //sets the movement speed (int)
        this.movement = movement;

    }

    /**
     * a getter that is used to get the speed of a student
     * @return the speed of the student in terms of integer
     */
    public int getSpeed() {
        return this.movement;
        
    }
     
    /**
     * a getter that is used to get the health of a student 
     * @return the health of the student in terms of integer 
     */
     public int getHealth(){
         return this.health;
         
     }
     
     


     /**
      * A render method used to pass in instance variables such health, etc for only students
      * @param batchs spritebatch variable which is used to draw 2D rectangles that reference a texture
      */
    public void studentRender(SpriteBatch batchs) {
        //Begins the batch in order to start drawing on the screen which will setup appropriate render states.
        batch.begin();

        //draws the health of each student on top of their heads
        font.draw(batchs, "" + health, this.getX() + this.getTextureWidth() / 2 - 5, this.getY() + this.getTextureHeight() + 20);

        //passes the remaining variables and inheritance to the upper unit class
        super.render(batchs);
        //ends the batch which tells the program to stop drawing 
        batch.end();
    }

    /**
     * a setter that sets the health of a student once they're hit with paper
     * @param hp the health points of a student (or you can say grade)
     */
    public void setHealth(int hp) {
        this.health = hp;
    }

}
