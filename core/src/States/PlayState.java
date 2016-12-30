/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Teachers.Student;
import Teachers.Teacher;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pls.game.AISprites;

/**
 *
 * @author hadik9595
 */
public class PlayState extends State {
    
    private Student student;
    private Teacher teacher;
    private Texture bg;
    private Texture character;
    
    private Array<AISprites> aiSprites;
    
   
    
    public PlayState(StateManager sm){
        super(sm);
        bg = new Texture("background.jpg");
        student = new Student(0, 0,1, "teacher.jpg", 5);
        
        
        
    
        
       
    }

    @Override
    public void render(SpriteBatch batch) {
        
      
        
        batch.begin();
        batch.draw(bg, 0, 0);
        
        student.render(batch);

        batch.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {
       student.update(deltaTime);
       
       
       
       if(student.collides(teacher)){
           StateManager gsm = getStateManager();
            gsm.pop();
       }
    }
 
    
    @Override
    public void dispose() {
        bg.dispose();
        student.dispose();
       
    }
    
   
    
}
