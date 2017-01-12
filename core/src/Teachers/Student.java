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
 * @author yuk4142, Khaled and Victor Guanine 
 */
public class Student extends Unit {
    
    private int movement;
    private int health;
    private SpriteBatch batch;
     //The character essentially holds x and y coordinates 
    private Vector2 velocity = new Vector2();
    //Indicates how many pixels per seconds we move by, (tolerance is a way to notify once we reach 3 pixels before a waypoint)


    //an array of set points  (holds x and y ) 
   
  
    public Student(int x, int y, int movement, int health, String textureName){
        super(x,y,textureName, movement);
        batch = new SpriteBatch();
        
        this.health = health;
        this.movement = movement;  
        

    }
    
     public int getSpeed(){
        return this.movement;
        
    }
     
     public int getHealth(){
         
         return this.health;
         
     }
     
     public void renderz(SpriteBatch batchs){
         batch.begin();
         
        font.draw(batch, "" + health, this.getX() + this.getTextureWidth() / 2 - 5, this.getY() + this.getTextureHeight() + 20);
       
         super.render(batch);
         batch.end();
     }
     
     public void setHealth(int hp){
         this.health = hp;
     }
     
     
  
     
     
     
     
}
