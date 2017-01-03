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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private Game game;
    private int damage;
    //An instance used for holding position (x,y,z)
    private Vector3 position;
    //Instance for textures of unit
    private Texture unitModel;
    //instance for boundaries (TEMPORARY SINCE OUR IDEA IS NOT FULLY DEVELOPED) 
    private Circle damageBounds;

    private int damageRadius;

    private int health;
    private int amount;
    
    

    private TextureRegion textures;
    BitmapFont font = new BitmapFont(); 
    //new changes
    private Array<AISprites> aiSprites;
    private Sprite sprite;
    //instance variable designated for shaping a path
    private ShapeRenderer sr;
    private SpriteBatch batch;
    
    private Student student;
    private Teacher[] teacher;
    
    private AISprites spritee;

    public Unit(int x, int y, String textureName) {
        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        game = new Game();
        
       
        
        
        
        
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
        sprite.setOrigin(0,0);
        
        //sets the coordinates of each character
        sprite.setPosition(x, y);
        
        

        aiSprites = new Array<AISprites>();
        
        aiSprites.add(new AISprites(sprite, getRandomPath(), 100));

        //the bounds where the teachers can attack once 
        //damageBounds = new Circle(position.x, position.y, unitModel.getWidth() + radius , unitModel.getHeight() + radius);
       
        
        for (AISprites aiSprite : aiSprites) {
            Student c = (Student)student;
            damageBounds = new Circle(aiSprite.getNextX() + aiSprite.getWidth()/2, aiSprite.getNextY() + aiSprite.getHeight()/2, 100);
            amount++;
        }
        
        

    }

    public void fire() {

    }
    
    
    
    

    public void update(float deltaTime) {

        

        // set the new bounds
        for (AISprites aiSprite : aiSprites) {
            
            sprite.setX(aiSprite.getY());
            sprite.setY(aiSprite.getX());
            
        damageBounds.setPosition(sprite.getX(), sprite.getY());
        }
    }

    //new changes
    private Array<Vector2> getRandomPath() {
        Array<Vector2> path = new Array<Vector2>();
        for (int i = 0; i < MathUtils.random(5, 10); i++) {
            //adds random point, 
            //path.add(new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight())));
           path.add(new Vector2(338, 0)); 
           path.add(new Vector2(338, 99));
           path.add(new Vector2(542, 99));
            path.add(new Vector2(542, Gdx.graphics.getHeight() - 120));
   
           path.add(new Vector2(50, Gdx.graphics.getHeight() - 120));
           path.add(new Vector2(50, 254));
           path.add(new Vector2(257, 254));
           path.add(new Vector2(257, 0));
           
           
           
          
           
           
           
           
           
            
        }
        return path;

    }

    public void render(SpriteBatch batchs) {
        //draws the background since it's static and we don't change it in middle of the game
        batchs.draw(unitModel, 0, 0);

        
          

        
        
        
        //starts drawing (way to update screen)
        batch.begin();
        
        for (AISprites aiSprite : aiSprites) {
            aiSprite.draw(batch);
            
            
            font.draw(batch, ""+ 100 , aiSprite.getX() + sprite.getWidth()/2 - 10, aiSprite.getY() + sprite.getHeight() + 20 );
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
//                sr.ellipse(0, 0, 300, 400);
                
                
                previous = waypoint;
            }
        }
        sr.end();

        sr.setColor(Color.RED);
        sr.begin(ShapeType.Line);
       
        for (AISprites aiSprite : aiSprites) {
            for (Vector2 waypoint : aiSprite.getPath()) {
                sr.circle(waypoint.x, waypoint.y, 5);
                sr.circle(aiSprite.getNextX() + aiSprite.getWidth()/2,aiSprite.getNextY() + aiSprite.getHeight()/2, damageRadius);
                
              
                
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
    
 
    
    public float getY(){
        return position.y;
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

   
    
    
    public boolean collides(Teacher a){
       
        if(damageBounds.overlaps(a.getBounds())){
            
//            return true;
            
           
            
            
        }
       return false;
    }
    
     public void dispose() {
        unitModel.dispose();
        sprite.getTexture().dispose();
        sr.dispose();
        font.dispose();
        
        
        
    }

    public void createCharacter(float x, float y) {
        
    }

}
