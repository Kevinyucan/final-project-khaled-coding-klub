/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author yuk4142, Khaled and Victor Guanine 
 */
public class Student extends Unit {
    
    private int movement;
    private int health;
    private SpriteBatch batch;
    
  
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
        font.draw(batchs, "" + health, this.getX() + this.getTexture().getWidth() / 2 - 5, this.getY() + this.getTexture().getHeight() + 20);
         super.render(batchs);
         batch.end();
     }
     
     public void setHealth(int hp){
         this.health = hp;
     }
     
     
    
     
     
     
     
}
