/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pls.game.Game;

/**
 *
 * @author yuk4142
 */
public abstract class Unit {
    
    private int damage;
    //An instance used for holding position (x,y,z)
    private Vector3 position;
    //Instance for textures of unit
    private Texture unitModel;
    //instance for boundaries (TEMPORARY SINCE OUR IDEA IS NOT FULLY DEVELOPED) 
    private Circle damageBounds;
    
    private int damageRadius;
    
    private int health;
    
    private TextureRegion textures;
    
    
    
    public Unit(int x, int y, String textureName, int radius){
        //initializes the unit's coords
        position = new Vector3(x,y,0);
        //intializes the unit's texture
        unitModel = new Texture(textureName);
        
        //the bounds where the teachers can attack once 
         //damageBounds = new Circle(position.x, position.y, unitModel.getWidth() + radius , unitModel.getHeight() + radius);
        damageBounds = new Circle(position.x, position.y, radius);
        
    
    }
    
    public void fire(){
        
    }
    
    
     public void update(float deltaTime){
         
         position.x += 1;
         
         position.y = this.function((int) position.x);
         
        
        // set the new bounds
        damageBounds.setPosition(position.x, position.y);
    }
    
      public void render(SpriteBatch batch){
        batch.draw(unitModel, position.x, position.y);
    }
      
       public Circle getBounds(){
        return damageBounds;
    }
       
       public int getHealth(){
           return this.health; 
       }
       
       public int getDamage(){
           return this.damage;
       }
       
       public int function(int x){
            x = x*x;
           int changeY = 2*(x + 300) + 30;
           
           if(changeY < Game.HEIGHT){
               
           return  changeY;
           }else{
               return 0;
           }
       }
       
       
        public void dispose(){
        unitModel.dispose();
    }
    
    
    
}
