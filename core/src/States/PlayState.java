/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Teachers.Student;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author hadik9595
 */
public class PlayState extends State {
    
    private Student unit;
    private Texture bg;
    private Texture character;
    
    public PlayState(StateManager sm){
        super(sm);
        
        unit = new Student(0, 0,1, "teacher.jpg", 5);
        
        bg = new Texture("background.jpg");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        
        batch.draw(bg, 0, 0);
        
       
        
        unit.render(batch);
        
        batch.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {
       unit.update(deltaTime);
    }

    @Override
    public void dispose() {
        bg.dispose();
        unit.dispose();
    }
    
}
