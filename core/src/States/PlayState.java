/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Teachers.Student;
import Teachers.Teacher;
import Teachers.Unit;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.pls.game.AISprites;
import com.pls.game.Game;

/**
 *
 * @author hadik9595
 */
public class PlayState extends State {
    
    private Student[] student;
    private Teacher[] teacher;
    private Unit unit;
    private Texture bg;
    private Texture character;
    private Texture button;
    private Texture panel;
    
    private boolean deployed;
    private int money;
    private int teacherAmount = 1;
    
    public int wave;
    public int studentAmount = 1;
    
    private Array<AISprites> aiSprites;
    
   
    
    public PlayState(StateManager sm){
        super(sm);
        setCameraView(Game.WIDTH, Game.HEIGHT);
       setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        bg = new Texture("Background.jpg");
        button = new Texture("button.jpg");
        panel = new Texture("panel.jpg");
 
         student = new Student[studentAmount];
         teacher = new Teacher[teacherAmount];
        for (int i = 0; i < student.length; i++) {
            student[i] = new Student(338, 0,100,200, "student.jpg");
            teacher[i] = new Teacher(338, 0,100, "teacher.jpg", 70);
        }
        
        
        
        
        
        
    
        
       
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(getCombinedCamera());

        batch.begin();
        batch.draw(bg, 0 , 0,getViewWidth(), getViewHeight());
        
        batch.draw(button, 0, 0);
        batch.draw(panel, 0 , 0 , getViewWidth()/4, getViewHeight());
        
        
        for ( int i = 0; i < student.length; i++) {
            student[i].render(batch);
        }
        //student.render(batch);
        
        for (int i = 0; i < teacherAmount; i++) {
            teacher[i].render(batch);
        }
        
        

        batch.end();
        
      
    }

    @Override
    public void handleInput() {
          if (Gdx.input.justTouched()) {
              
       // Get the mouse click/touch position
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            
            // Convert that point to "game coordinates"
            unproject(touch);
            System.out.println("Mouse X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());
            System.out.println("X: " + touch.x + " Y: " + touch.y );
            
              System.out.println("W "  + Gdx.graphics.getWidth());
              System.out.println("H " + Gdx.graphics.getHeight());
            // Check if the button is pressed
            float buttonX = 0;
            float buttonY =0;
            if (touch.x > buttonX && touch.x < buttonX + button.getWidth()
                    && touch.y > buttonY && touch.y < buttonY + button.getHeight()) {
                //unit.createCharacter(touch.x,touch.y);
                teacherAmount++;
                teacher[teacherAmount] = new Teacher((int)touch.x,(int)touch.y ,50, "student.jpg", 5);
               
            }

        }

    }

    @Override
    public void update(float deltaTime) {
       for (int i = 0; i < student.length; i++){
        student[i].update(deltaTime);
       }
       
       for (int i = 0; i < teacherAmount; i++){
        teacher[i].update(deltaTime);
       }
       
       
       
        
        for ( int i = 0; i < student.length; i++){
       if(student[i].collides(teacher[i])){
           System.out.println("Ham");
           StateManager gsm = getStateManager();
            gsm.pop();
       }
    }
    }
  
    
    @Override
    public void dispose() {
        bg.dispose();
        button.dispose();

        for (int i = 0; i < student.length; i++){
           student[i].dispose(); 
        }
        
        for (int i = 0; i < teacherAmount; i++) {
          teacher[i].dispose();  
        }
       
    }
    
   
    
}
