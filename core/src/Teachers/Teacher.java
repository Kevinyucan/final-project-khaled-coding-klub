package Teachers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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
    private float timeSinceLastShot;
    private float firingSpeed;
    private Array<Bullet> bullets;
    private Texture bullet1;

    public Teacher(int x, int y, String textureName, int radius) {
        //initialize variables
        super(x, y, textureName, 0);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        this.damageRadius = radius;
        this.firingSpeed = 10;
        this.timeSinceLastShot = 0;
        this.bullets = new Array<Bullet>();
        bullet1 = new Texture("bullet.png");
    }

    /**
     * Returns firing radius of teacher
     *
     * @return firing radius of teacher
     */
    public int getRadius() {
        return damageRadius;
    }

    /**
     * Removes bullet that is hitting student
     *
     * @param a student the bullet is hitting
     */
    public void removeBullet(Student a) {
        for (Bullet bullet : bullets) {
            for (int i = 0; i < bullets.size; i++) {

                if (bullet.collides(a)) {
                    bullets.removeIndex(i);
                    System.out.println("heron never dies");


                }


            }

        }

    }
/**
 * Check if bullet collides with student
 * @param a student bullet is colliding with
 * @return true if bullet has collided
 */
    public boolean bulletCollide(Student a) {
        for (Bullet bullet : bullets) {
            if (bullet.collides(a)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * If bullet is out of bounds, remove it
     */
    public void bulletOutOfBounds(){
        for (Bullet bullet : bullets) {
            for (int i = 0; i < bullets.size; i++) {
            if (bullet.getX()<0||bullet.getX()>400||bullet.getY()<0||bullet.getY()>800){
                bullets.removeIndex(i);
               
            }
            }
        }
    }
    
    

    public void update(float deltaTime) {
        //set timesincelastshot by adding on to deltatime
        timeSinceLastShot += deltaTime;
        
        //if the time since last shot is bigger than the firing speed, fire a bullet
        if (timeSinceLastShot > firingSpeed) {

            shoot();
        }
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }
    }

    /**
     *Fire a bullet
     */
    public void shoot() {
        //set time last shot = 0
        timeSinceLastShot = 0;
        
        float bulletX = super.getX(); 
                 float bulletY = super.getY();
                 //add new bullet
        bullets.add(new Bullet(bulletX, bulletY, (float) 100, (float) 30, bullet1));
        
        
    }

    /**
     * Sets range of teacher
     * @param radius the radius for the range of teacher
     */
    public void setRadius(int radius) {

        damageRadius = radius;
    }

 

    public void renderz(SpriteBatch batchs) {
//         batch.begin();
        super.render(batchs);
        sr.setColor(Color.RED);
        sr.begin(ShapeRenderer.ShapeType.Line);
        //render bullets in array
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
        //draw radius circle for range
        if (isRadiusRevealed == true) {
            sr.circle(this.getX() + this.getTextureWidth() / 2, this.getY() + this.getTextureHeight() / 2, 100);
        }
//         batch.end();


    }

    public boolean toggleRadius() {

        if (isRadiusRevealed) {
            return isRadiusRevealed = false;
        } else {
            return isRadiusRevealed = true;
        }





    }
}