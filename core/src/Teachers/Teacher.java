package Teachers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;


/**
 *
 * @author yuk4142
 */
public class Teacher extends Unit {
    
    //create private variables
    //firing range of teacher
    private int damageRadius;
    //spritebatch used to draw
    private SpriteBatch batch;
    //boolean toggles radius visibility of teacher
    private boolean isRadiusRevealed = false;
    //storing time since last bullet was fired
    private float timeSinceLastShot;
    //how fast bullets shoot out
    private float firingSpeed;
    //array of bullets
    private Array<Bullet> bullets;
    //picture of bullet
    private Texture bullet1;
    //temporary variable storing a student
    private Student temp;

    /**
     * Teacher constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param textureName what picture is used to draw teacher
     * @param radius firing radius for bullets
     */
    public Teacher(int x, int y, String textureName, int radius) {
        //initialize variables
        //store x and y coordinates to super
        super(x, y, textureName, 0);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        this.damageRadius = radius;
        this.firingSpeed = 500;
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
        //going through list of bullets
        for (Bullet bullet : bullets) {
            for (int i = 0; i < bullets.size; i++) {
                //if bullet collides with student
                if (bullet.collides(a)) {
                    //remove bullet at specified index in array
                    bullets.removeIndex(i);
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
    

    
    

    public void update(float deltaTime) {
        //set timesincelastshot by adding on to deltatime
        timeSinceLastShot += deltaTime;
        
        //if the time since last shot is bigger than the firing speed, fire a bullet
        if (timeSinceLastShot > firingSpeed) {

            shoot();
        }
       
        for (Bullet bullet : bullets) {
            bullet.updateBullet(deltaTime,temp);
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
        bullets.add(new Bullet(bulletX, bulletY, (float) 300, (float) 30, bullet1));
        
        
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

    /**
     * Turns teacher radius on and off(visibility)
     * @return true if radius is revealed, else return false
     */
    public boolean toggleRadius() {

        if (isRadiusRevealed) {
            return isRadiusRevealed = false;
        } else {
            return isRadiusRevealed = true;
        }
    }
        
        /**
         * Return specified student
         * @param a specified student
         * @return specified student
         */
       public Student getStudent(Student a){
           temp = a;
            return a;
        } 

}