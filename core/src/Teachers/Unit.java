/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import States.PlayState;
import States.State;
import States.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.pls.game.AISprites;
import com.pls.game.Game;

/**
 *
 * @author yuk4142 && Khaled
 */
public abstract class Unit {

    //instance variable for teacher damage 
    private int damage;
    //An instance used for holding position (x,y,z)
    private Vector3 position;
    //Instance for textures of unit
    private Texture unitModel;
    //instance for teacher boundaries (circle)
    private Circle damageBounds;
    //instance for student's boundaries (rectangle)
    private Rectangle studentBounds;

    //the damage radius for teacher in terms of integer 
    private int damageRadius;

    //private map variable so that it can be used to draw paths for students to follow
    private Map map;
    //variable health for students 
    private int health;

    //orthographic camera for the entire game (that way 0,0 is at bottom corner rather than opposite)
    private OrthographicCamera cam;

    //an array of students 
    private Array<Student> students;

    //texture region for the buttons 
    private TextureRegion textures;
    //font for drawing scores etc
    BitmapFont font = new BitmapFont();
    //an array of students (for moving, boundaries and such) and teachers (for boundaries )
    private Array<AISprites> aiSprites;
    //sprite for teacher and student
    private Sprite sprite;
    //instance variable designated for shaping a path
    ShapeRenderer sr;
    //spritebatch for drawing 
    private SpriteBatch batch;

    //array for teachers 
    private Teacher[] teacher;

    /**
     * The unit constructor where both teachers and student are passed in to
     * initialize
     *
     * @param x the x coordinate of teacher/student
     * @param y the y coordinate of teacher/student
     * @param textureName the texture name of student/ teacher
     * @param movement the movement in terms of integer (teachers are stationary
     * therefore 0)
     */
    public Unit(int x, int y, String textureName, int movement) {
        //intializes shape renderer so that it can be used later on
        sr = new ShapeRenderer();
        //initializes batch so that it can be accessed (this is strictly for movement)
        batch = new SpriteBatch();

        //initializes students array so that it can be accessed later on
        students = new Array<Student>();
        //initializes the map so that it can be drawn later on
        map = new Map();

        //initializes the unit's coords
        position = new Vector3(x, y, 0);
        //intializes the unit's texture
        unitModel = new Texture(textureName);
        //unitModel = new Texture(textureName);

        //new changes 
        sprite = new Sprite(unitModel);
        //set size of sprite
        sprite.setSize(50, 50);
        //set starting point
        sprite.setOrigin(0, 0);

        //sets the coordinates of each character
        sprite.setPosition(x, y);

        //creates new aiSprites which is students, that is for movement along the path
        aiSprites = new Array<AISprites>();
        //adds the students as sprites into the method of where they can now move 
        aiSprites.add(new AISprites(sprite, map.getAssignedPath(), movement));

        //goes through each student and teacher in the game 
        for (AISprites aiSprite : aiSprites) {
            //sets the boundaries of the teacher's damaging radius
            damageBounds = new Circle(aiSprite.getX() + aiSprite.getWidth() / 2, aiSprite.getY() + aiSprite.getHeight() / 2, 100);
            //sets the boundaries of the students as rectangles
            studentBounds = new Rectangle(aiSprite.getX(), aiSprite.getY(), aiSprite.getWidth(), aiSprite.getHeight());

        }

    }

    /**
     * update method used to constantly update any changes happening to units
     *
     * @param deltaTime the amount of time between events
     */
    public void update(float deltaTime) {

        // set the new boundaries of each student and teacher
        for (AISprites aiSprite : aiSprites) {
            //sets the student's X and Y coordinates 
            sprite.setX(aiSprite.getX());
            sprite.setY(aiSprite.getY());

            //updates the damage bounds of both student (rectangle) and teacher ( circle radius)
            damageBounds.setPosition(sprite.getX(), sprite.getY());
            studentBounds.setPosition(sprite.getX(), sprite.getY());

        }

    }

    /**
     * Renders models and is responsible for all elements that occur in screen
     *
     * @param batch that is passed by from the PlayState class, and is the
     * necessary key to drawing units
     */
    public void render(SpriteBatch batchs) {

        // Calls the batch in order to draw which will setup appropriate render states
        batch.begin();

        //goes through each student in the game that is capable of moving along path
        for (AISprites aiSprite : aiSprites) {
            //draws all the students as it constantly updates their coordinates
            aiSprite.draw(batch);

        }
        //ends the batch which tells the program to stop drawing 
        batch.end();

        //loop for drawing each line segment for the students to follow
        for (AISprites aiSprite : aiSprites) {
            //saves the previous line of the path into a vector2 variable 
            Vector2 previous = aiSprite.getPath().first();
            //using a for loop, to go through each waypoint
            for (Vector2 waypoint : aiSprite.getPath()) {

                //sets previous way point to the current one in order to create a loop all around the paths
                previous = waypoint;
            }
        }

        //ends the shape renderer which indicates that the program is done drawing
        sr.end();

    }

    /**
     * a getter for only teacher's damage radius that behaves as boundaries
     *
     * @return the teacher's damage bounds (circle)
     */
    public Circle getBounds() {

        return damageBounds;
    }

    /**
     * a getter for only student's rectangle boundaries
     *
     * @return the boundaries of each student
     */
    public Rectangle getBoundaries() {
        return studentBounds;
    }

    /**
     * a float getter used to get the current unit's Y coordinate
     *
     * @return the current unit's Y coordinate on screen
     */
    public float getY() {
        return sprite.getY();
    }

    /**
     * a float getter used to get the current unit's X coordinate
     *
     * @return the current unit's X coordinate on screen
     */
    public float getX() {
        return sprite.getX();
    }

    /**
     * A getter used to get the texture's width of the current unit
     *
     * @return the current unit's width of texture
     */
    public float getTextureWidth() {

        return sprite.getWidth();
    }

    /**
     * A getter used to get the texture's height of the current unit
     *
     * @return the current unit's height of texture
     */
    public float getTextureHeight() {

        return sprite.getHeight();
    }

    /**
     * a booolean method that checks if a teacher's radius collides with
     * student's rectangle bounds
     *
     * @param a the properties of a teacher are passed in as a parameter
     * @return true if collision is detected, otherwise return false
     */
    public boolean collides(Teacher a) {
        //checks if teacher's radius collides (intersects) with student's rectangle
        if (Intersector.overlaps(a.getBounds(), getBoundaries())) {

            //Once collision occurs, return true
            return true;

        } else {
            //otherwise return false
            return false;
        }

    }

    /**
     * disposes of all materials used so that it reduces memory consumption when
     * it comes to playing on mobiles
     */
    public void dispose() {
        //disposes of unitModel texture
        unitModel.dispose();
        //disposes of sprite texture
        sprite.getTexture().dispose();
        //disposes of shape renderer 
        sr.dispose();
        //disposes of font 
        font.dispose();

    }

}
