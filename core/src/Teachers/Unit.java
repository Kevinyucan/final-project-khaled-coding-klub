/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.pls.game.AISprites;
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

    //new changes
    private Array<AISprites> aiSprites;
    private Sprite sprite;
    //instance variable designated for shaping a path
    private ShapeRenderer sr;
    private SpriteBatch batch;

    public Unit(int x, int y, String textureName, int radius) {
        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        //initializes the unit's coords
        position = new Vector3(x, y, 0);
        //intializes the unit's texture
        unitModel = new Texture(textureName);
        //unitModel = new Texture(textureName);

        //new changes 
        sprite = new Sprite(new Texture(textureName));
        //set size of sprite
        sprite.setSize(50, 50);
        //set starting point
        sprite.setOrigin(0, 0);

        aiSprites = new Array<AISprites>();
        aiSprites.add(new AISprites(sprite, getRandomPath()));

        //the bounds where the teachers can attack once 
        //damageBounds = new Circle(position.x, position.y, unitModel.getWidth() + radius , unitModel.getHeight() + radius);
        damageBounds = new Circle(sprite.getX(), sprite.getY(), radius);

    }

    public void fire() {

    }

    public void update(float deltaTime) {

        position.x += 1;

        position.y = this.function((int) position.x);

        // set the new bounds
        damageBounds.setPosition(position.x, position.y);
    }

    //new changes
    private Array<Vector2> getRandomPath() {
        Array<Vector2> path = new Array<Vector2>();
        for (int i = 0; i < MathUtils.random(5, 10); i++) {
            //adds random point, 
            path.add(new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight())));
        }
        return path;

    }

    public void render(SpriteBatch batchs) {
        //draws the background since it's static and we don't change it in middle of the game
        batchs.draw(unitModel, damage, damage);

        //starts drawing (way to update screen)
        batch.begin();
        for (AISprites aiSprite : aiSprites) {
            aiSprite.draw(batch);
        }
        batch.end();

        //responsible for drawing the lines from point to point
        sr.setColor(Color.WHITE);
        sr.begin(ShapeType.Line);
        //loop for drawing each line
        for (AISprites aiSprite : aiSprites) {
            Vector2 previous = aiSprite.getPath().first();
            for (Vector2 waypoint : aiSprite.getPath()) {
                sr.line(previous, waypoint);
                previous = waypoint;
            }
        }
        sr.end();

        sr.begin(ShapeType.Filled);
        for (AISprites aiSprite : aiSprites) {
            for (Vector2 waypoint : aiSprite.getPath()) {
                sr.circle(waypoint.x, waypoint.y, 5);
            }
        }
        sr.end();

        sr.setColor(Color.CYAN);
        sr.begin(ShapeType.Line);
        for (AISprites aiSprite : aiSprites) {
            sr.line(new Vector2(aiSprite.getX(), aiSprite.getY()), aiSprite.getPath().get(aiSprite.getWaypoint()));
        }
        sr.end();

//        //new changes
//        for (AISprites aiSprite : aiSprites) {
//            Vector2 previous = aiSprite.getPath().first();
//            
//            
//            
//
//            for (Vector2 waypoint : aiSprite.getPath()) {
//                //draws first line
//                sr.begin(ShapeType.Line);
//                sr.line(previous, waypoint);
//
//                sr.end();
//
//                sr.begin(ShapeType.Filled);
//
//                sr.circle(waypoint.x, waypoint.y, 5);
//
//                previous = waypoint;
//                
//
//                sr.end();
//            }
//
//        }
//      
    }

    public Circle getBounds() {
        return damageBounds;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public int function(int x) {

        x = x * x;
        int changeY = 2 * (x + 300) + 30;

        if (changeY < Game.HEIGHT) {

            return changeY;
        } else {
            return 0;
        }
    }

    public void dispose() {
        unitModel.dispose();
        sprite.getTexture().dispose();
        sr.dispose();
    }

}
