/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.pls.game.AISprites;

/**
 *
 * @author yuk4142
 */
public class Teacher extends Unit {
    
     private Circle damageBounds;
     private int damageRadius;
     private SpriteBatch batch;
     private boolean isRadiusRevealed = false;
    
      public Teacher(int x, int y,  String textureName,int radius ){
        super(x,y,textureName,0);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        this.damageRadius = radius;
        
        
    }
      
      
      public int getRadius(){
          return damageRadius;
      }
      
      public void setRadius(int radius){
          
          damageRadius = radius;
      }
      
      public void fire(){
          
      }
      
        public void renderz(SpriteBatch batchs){
//         batch.begin();
          super.render(batchs);
         sr.setColor(Color.RED);
         sr.begin(ShapeRenderer.ShapeType.Line);
           
         if(isRadiusRevealed == true){
         sr.circle(this.getX() + this.getTextureWidth()/2 ,this.getY()+ this.getTextureHeight()/2, 100);
         }
//         batch.end();
         

     }
        
        
        public boolean toggleRadius(){
 
            if(isRadiusRevealed){
            return isRadiusRevealed = false;
            }else{
                return isRadiusRevealed = true;
            }
            
            
             
            
            
        }


}
