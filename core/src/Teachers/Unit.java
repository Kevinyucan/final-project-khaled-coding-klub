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
    private Rectangle studentBounds;

    private int damageRadius;

    private Map map;
    private int health;
    private int amount;
    
 private Array<Student> students;
 

    private TextureRegion textures;
    BitmapFont font = new BitmapFont(); 
    //new changes
    private Array<AISprites> aiSprites;
    private Sprite sprite;
    //instance variable designated for shaping a path
    ShapeRenderer sr;
    private SpriteBatch batch;
    
    
    private Teacher[] teacher;
    
    private AISprites spritee;

    public Unit(int x, int y, String textureName, int movement) {
        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        game = new Game();
        students = new Array<Student>();
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
        sprite.setOrigin(0,0);
        
        //sets the coordinates of each character
        sprite.setPosition(x, y);
        
        

        aiSprites = new Array<AISprites>();
        
        aiSprites.add(new AISprites(sprite, map.getRandomPath(), movement ));
        

        //the bounds where the teachers can attack once 
        //damageBounds = new Circle(position.x, position.y, unitModel.getWidth() + radius , unitModel.getHeight() + radius);
       
        
        for (AISprites aiSprite : aiSprites) {
          
            damageBounds = new Circle(aiSprite.getX() + aiSprite.getWidth()/2, aiSprite.getY() + aiSprite.getHeight()/2, 100 );
            studentBounds = new Rectangle(aiSprite.getX(), aiSprite.getY(), aiSprite.getWidth(), aiSprite.getHeight());
            
        }
        
        

    }
    
   
    
    
    
    

    public void update(float deltaTime) {

        // set the new bounds
        for (AISprites aiSprite : aiSprites) {
            
            sprite.setX(aiSprite.getX());
            sprite.setY(aiSprite.getY());
            
        damageBounds.setPosition(sprite.getX(), sprite.getY());
        studentBounds.setPosition(sprite.getX(), sprite.getY());
            
        }
        
        
        
        
    }



    public void render(SpriteBatch batchs) {
        //draws the background since it's static and we don't change it in middle of the game
        batchs.draw(unitModel, 0, 0);

        

        
        
        
        //starts drawing (way to update screen)
        batch.begin();
        
        for (AISprites aiSprite : aiSprites) {
            aiSprite.draw(batch);
            
        }
        
//        font.draw(batch, "" + health, sprite.getX() + sprite.getWidth() / 2 - 5, sprite.getY() + sprite.getHeight() + 20);
       

     
      
      
        batch.end();

        sr.end();
        
        map.drawSquares();
        
        //responsible for drawing the lines from point to point
//        sr.setColor(Color.WHITE);
//        sr.begin(ShapeType.Line);
//        
//        sr.rect(542, 148, 55, 267);
//        sr.rect(50, 365,492 , 50);
//        sr.rect(50, 270, 55, 95);
//        sr.rect(50, 270, 207, 30);
//        sr.rect(257, 0, 50, 300);
//        sr.rect(307, 0, 30 , 132);
//        sr.rect(338, 0, 50, 190);
//        sr.rect(338, 148, 205, 42);
        
        //loop for drawing each line
        for (AISprites aiSprite : aiSprites) {
            Vector2 previous = aiSprite.getPath().first();
            for (Vector2 waypoint : aiSprite.getPath()) {
                //sr.line(previous, waypoint);
//                sr.ellipse(0, 0, 300, 400);
                
                
                previous = waypoint;
            }
        }
//        sr.end();

        sr.setColor(Color.RED);
        sr.begin(ShapeType.Line);
       
        for (AISprites aiSprite : aiSprites) {
            for (Vector2 waypoint : aiSprite.getPath()) {
                sr.circle(waypoint.x, waypoint.y, 5);
//                sr.circle(sprite.getX() + sprite.getWidth()/2 ,sprite.getY() + aiSprite.getHeight()/2, 100);
                
              
                
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
    
    public Rectangle getBoundaries(){
        return studentBounds;
    }

   

    public int getDamage() {
        return this.damage;
    }
    
 
    
    public float getY(){
        return sprite.getY();
    }
    
    public float getX(){
        return sprite.getX();
    }
    
    public float getTextureWidth(){
        
        return sprite.getWidth();
    }
  
    public float getTextureHeight(){
        
        return sprite.getHeight();
    }
    
    
   
public void death(){
   System.out.println(students.size);
     for ( int i = 0; i < 2; i++){
          
    for (Student studentz : students){
        System.out.println("woah");
        students.removeIndex(1);
        
    } 
}
}
  

   
    
    
    public boolean collides(Teacher a){
        
        if(Intersector.overlaps(a.getBounds(), getBoundaries())){
           
           health--;
         
            
          return true;
            
           
            
            
        }
       return false;
    }
    
     public void dispose() {
        unitModel.dispose();
        sprite.getTexture().dispose();
        sr.dispose();
        font.dispose();
        
        
        
    }

  
}
