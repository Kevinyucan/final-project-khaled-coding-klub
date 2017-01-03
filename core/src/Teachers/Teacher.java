/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.math.Circle;

/**
 *
 * @author yuk4142
 */
public class Teacher extends Unit {
    
     private Circle damageBounds;
     private int damageRadius;
    
      public Teacher(int x, int y,int movement,  String textureName,int radius ){
        super(x,y,textureName);
        
        this.damageRadius = radius;
        
        
    }
      
      
      public int getRadius(){
          return damageRadius;
      }


}
