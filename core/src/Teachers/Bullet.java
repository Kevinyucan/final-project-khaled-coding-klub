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
 * @author yuk4142p 
 */


public class Bullet {
    private boolean hit;
    private float startX;
    private float startY;
    private float currentX;
    private float currentY;
    private float endX;
    private float endY;
    private float speed;
    private Rectangle bulletBoundary;
    private Texture picture;
    private Vector2 velocity = new Vector2();
    private Array<Vector2> path;
    
    private float time = 0;
    private float direction;
    
     private ShapeRenderer sr;
    
    public Bullet(float startX, float startY,  float speed,float damage, Texture picture){
        
        sr = new ShapeRenderer();
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.speed = speed;
        this.picture = picture;
        
       
        
        bulletBoundary = new Rectangle(startX, startY,25,40);
        
//                Line line = new Line();
        
        
        
        
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
        
        public void render(SpriteBatch batch){
            batch.begin();
            batch.draw(picture, startX, startY,25,25);
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
        public void update(float deltaTime){
            
            
            currentX = deltaTime*speed;
            currentY = (currentY + (speed * MathUtils.sin(direction)));
            bulletBoundary.setPosition(currentX, currentY);
            
    }

    public boolean collides(Student a) {
        if(bulletBoundary.overlaps((a.getBoundaries()))){
        return true ;
        }
        return false;
    }
    
}
