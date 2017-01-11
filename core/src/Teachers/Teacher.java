/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

/**
 *
 * @author yuk4142
 */
public class Teacher extends Unit {
    
     private Circle damageBounds;
     private int damageRadius;
     private SpriteBatch batch;
    
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
      
        public void renderz(SpriteBatch batchs){
         batch.begin();
          
         sr.setColor(Color.RED);
         sr.begin(ShapeRenderer.ShapeType.Line);
         sr.circle(this.getX() + this.getTextureWidth()/2 ,this.getY()+ this.getTextureHeight()/2, 100);
         super.render(batchs);
         batch.end();
         
     }


}
