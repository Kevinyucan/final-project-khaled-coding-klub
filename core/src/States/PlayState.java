/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Teachers.Bullet;
import Teachers.Student;
import Teachers.Teacher;
import Teachers.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Array;
import com.pls.game.AISprites;
import com.pls.game.Game;


/**
 *
 * @author hadik9595
 */
public class PlayState extends State {
    
//    private Student[] student;
//    private Teacher[] teacher;
    private Unit unit;
    private Texture bg;
    private Texture character;
    private Texture button;
    private Texture panel;
    
    private boolean deployed;
    public int money;
    private int passed;
//    private int teacherAmount = 1;
//    public int studentAmount = 1;
    public int wave;
    
    private Stage stage;
   
    //Skin skin = Assets.manager.get(Assets.uiskin, Skin.class);
    
    BitmapFont font = new BitmapFont();
    
    private Array<AISprites> aiSprites;
    private Array<Student> students;
    private Array<Teacher> teachers;
    
    //test
    private Bullet bullet;
    //variable used to separate individual pieces within a picture
    private TextureRegion region[];
    private Texture balance;
    
   private Array<Bullet> bullets;
   

    
    public PlayState(StateManager sm){
        super(sm);
        
        setCameraView(Game.WIDTH, Game.HEIGHT);
       setCameraPosition(getViewWidth() / 2, getViewHeight() / 2);
        bg = new Texture("Background.jpg");
        button = new Texture("button.jpg");
        panel = new Texture("units.jpg");
        balance = new Texture("money.jpg");
        Texture bullet1 = new Texture("bullet.png");
        
        students = new Array<Student>();
        teachers = new Array<Teacher>();
        
        bullets = new Array<Bullet>();
        
        
        
        region = new TextureRegion[5];
        region[0] = new TextureRegion(panel,0,0,136,156);
        region[1] = new TextureRegion(panel,136,0,136,156);
        region[2] = new TextureRegion(panel,136*2,0,136,156);
        region[3] = new TextureRegion(panel,136*3,0,136,156);
        region[4] = new TextureRegion(panel,136*4,0,136,156);
        bullet = new Bullet((float)250, (float)250, (float)250, (float)250, 100,bullet1);
        
       
        
 
//         student = new Student[studentAmount];
//         teacher = new Teacher[teacherAmount];
//        for (int i = 0; i < student.length; i++) {
//            student[i] = new Student(338, 0,100,200, "student.jpg");
//            teacher[i] = new Teacher(338, 300,0, "teacher.jpg", 100);
//          
//            
//            
//        }
        
         students.add(new Student(400, 0,100,100, "teacher.jpg"));
          students.add(new Student(400, 0,200,100, "teacher.jpg"));
          teachers.add(new Teacher(338, 300, "student.jpg",100));
        
        
        
        
    
        
       
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(getCombinedCamera());

        batch.begin();
       
        batch.draw(bg, 0 , 0,getViewWidth(), getViewHeight());
       
        //batch.draw(button, 0, 0);
        //batch.draw(panel, 0 , 0 , getViewWidth()/4, getViewHeight());
//        batch.draw(region[0],panel.getWidth()- getViewWidth(), 0, getViewWidth()/8 , getViewHeight()/8);
//        batch.draw(region[1], 0, 0, getViewWidth()/8, getViewHeight()/8);
//        batch.draw(region[4], 0, 0, getViewWidth()/8, getViewHeight()/8);
         
        bullet.render(batch);
         for (int i = 0; i < region.length; i++) {
           batch.draw(region[i],i*64,0, getViewWidth()/8 , getViewHeight()/8);
           
        }
         
         batch.draw(balance, getViewWidth() - balance.getWidth() + 109 , 0, getViewWidth()/3 , getViewHeight()/8); 
           font.setColor(Color.BLACK);
           font.draw(batch, "" + money, getViewWidth(), 33 );
       
//        for ( int i = 0; i < student.length; i++) {
//            student[i].render(batch);
//            
//        }
//        //student.render(batch);
//        
//        for (int i = 0; i < teacherAmount; i++) {
//            teacher[i].renderz(batch);
//        }
           
         for (Student student : students) {
           student.renderz(batch);
           
            
            
             
         }
         
           for (Teacher teacher : teachers) {
            teacher.renderz(batch);
   
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
//            System.out.println("Mouse X: " + Gdx.input.getX() + " Y: " + Gdx.input.getY());
//              System.out.println("X " + touch.x + " Y " + touch.y);
               

            teachers.add(new Teacher((int)touch.x,(int)touch.y, "student.jpg",100));

            // Check if the button is pressed
              
              for (int i = 0; i < region.length; i++) {
              
            float buttonX = i*64;
            float buttonY = 0;
            
            if (touch.x > buttonX && touch.x < buttonX + region[i].getRegionWidth()/2
                    && touch.y > buttonY && touch.y < buttonY + region[i].getRegionHeight()/2) {
                
                //unit.createCharacter(touch.x,touch.y);
                
           
               
                System.out.println("Button " + i);
                create(i);
               
               
               
            }
            
            
  
            
            
         

        }
              
               for (Teacher teacher : teachers) {
                  float buttonX = teacher.getX() ;
                  float buttonY = teacher.getY();
                 
                 
                   System.out.println("Camera coords " + touch.x + " " + touch.y );
                   System.out.println("x " + teacher.getX() + " y " + teacher.getY() + "   width: " + teacher.getTextureWidth() + "   height: " + teacher.getTextureHeight());
            
 
                  if (touch.x > buttonX && touch.x < buttonX + teacher.getTextureWidth() 
                          && touch.y > buttonY && touch.y < buttonY + teacher.getTextureHeight()) {
                     System.out.println("hello");
                     
                  }
              }
             
              }

    }
    
    
    public void create(int i){
        if(i == 0){
             students.add(new Student(400, 0,100,100, "teacher.jpg"));
        }
    }

    @Override
    public void update(float deltaTime) {
        
//       for (int i = 0; i < student.length; i++){
//        student[i].update(deltaTime);
//       }
//       
//       for (int i = 0; i < teacherAmount; i++){
//        teacher[i].update(deltaTime);
//       }
       
           for (Teacher teacher : teachers) {
           teacher.update(deltaTime);
   
         }
       
       for (Student student : students) {
           student.update(deltaTime);
           for ( int i = 0; i < students.size; i++){
             
                 if(students.get(i).getHealth() <= 0){
               students.removeIndex(i);
               money = money + 100;
               
           }
           }
         }
       for (Teacher teacher : teachers){
        for (Student studentz : students){
        
       if(studentz.collides(teacher)){
           int temp = studentz.getHealth();
           
           temp--;
           studentz.setHealth(temp);
           
           
          
        
           
//           StateManager gsm = getStateManager();
//            gsm.pop();
           
       }
       
        
       
 
       
        
        
    }
        }
       
    }
    
    public int getMoney(){
        return money;
    }
  
    
    @Override
    public void dispose() {
        bg.dispose();
        button.dispose();
        font.dispose();
        panel.dispose();
        balance.dispose();
        stage.dispose();

//        for (int i = 0; i < student.length; i++){
//           student[i].dispose(); 
//        }
//        
//        for (int i = 0; i < teacherAmount; i++) {
//          teacher[i].dispose();  
//        }
        
           for (Student student : students) {
           student.dispose();
   
         }
           
               for (Teacher teacher : teachers) {
           teacher.dispose();
   
         }
           
       
    }
    

    
   
 
    
    
  
    
 
    
    

  

    
}
