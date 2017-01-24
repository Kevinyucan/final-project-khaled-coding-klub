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
        super(x, y, textureName, 0);
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        this.damageRadius = radius;
        this.firingSpeed = 30;
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


                }


            }

        }

    }

    public boolean bulletCollide(Student a) {
        for (Bullet bullet : bullets) {
            if (bullet.collides(a)) {
                return true;
            }
        }
        return false;
    }

    public void update(float deltaTime) {
        timeSinceLastShot += Gdx.graphics.getDeltaTime();
        if (timeSinceLastShot > firingSpeed) {

            shoot();
        }
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }
    }

    public void shoot() {
        timeSinceLastShot = 0;
        float bulletX = super.getX(); 
                 float bulletY = super.getY();
        bullets.add(new Bullet(bulletX +  30, bulletY + 30, (float) 30, (float) 30, bullet1));
        
        System.out.println("X:" + bulletX + " Y:" + bulletY);
    }

    public void setRadius(int radius) {

        damageRadius = radius;
    }

    public void fire() {
    }

    public void renderz(SpriteBatch batchs) {
//         batch.begin();
        super.render(batchs);
        sr.setColor(Color.RED);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
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