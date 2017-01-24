/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import com.pls.game.AISprites;
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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Array;
import com.pls.game.Game;


/**
 *
 * @author hadik9595
 */
public class PlayState extends State {

   
    private Texture bg;
    private Texture character;
    private Texture button;
    private Texture panel;
    private Texture start;
    
    private boolean deployed;
    public int money;
    private int passed;
//    private int teacherAmount = 1;
//    public int studentAmount = 1;
    public int wave;
    private int counter;
    
 
    
    private Stage stage;
   
    //Skin skin = Assets.manager.get(Assets.uiskin, Skin.class);
    
    BitmapFont font = new BitmapFont();
    
    private Array<AISprites> aiSprites;
    private Array<Student> students;
    private Array<Teacher> teachers;
   private Array<Image> buttonImages;
    
//    private Array<Bullet> teachers;
    
    
    //variable used to separate individual pieces within a picture
    private TextureRegion region[];
    
    private Texture balance;
   
    
   

    
    public PlayState(StateManager sm){
        super(sm);
    
        setCameraView(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
       setCameraPosition(getViewWidth()/2 , getViewHeight()/2 );
       
       
       
        bg = new Texture("Background.jpg");
        button = new Texture("button.jpg");
        panel = new Texture("units.jpg");
        balance = new Texture("money.jpg");
        start = new Texture("start.png");
        
        Texture bullet1 = new Texture("bullet.png");
       
        students = new Array<Student>();
        teachers = new Array<Teacher>();
        buttonImages = new Array<Image>();
        
        
        
        
        
        region = new TextureRegion[5];
        region[0] = new TextureRegion(panel,0,0,136,156);
        region[1] = new TextureRegion(panel,136,0,136,156);
        region[2] = new TextureRegion(panel,136*2,0,136,156);
        region[3] = new TextureRegion(panel,136*3,0,136,156);
        region[4] = new TextureRegion(panel,136*4,0,136,156);
//        bullet = new Bullet((float)250, (float)250, (float)250, (float)250, 100,bullet1);
        
       
        
 
//         student = new Student[studentAmount];
//         teacher = new Teacher[teacherAmount];
//        for (int i = 0; i < student.length; i++) {
//            student[i] = new Student(338, 0,100,200, "student.jpg");
//            teacher[i] = new Teacher(338, 300,0, "teacher.jpg", 100);
//          
//            
//            
//        }
        
         students.add(new Student(400, 0,100,100, "student.jpg"));
          students.add(new Student(400, 0,200,100, "student.jpg"));
          teachers.add(new Teacher(338, 300, "teacher.jpg",100));
        
        for (int i = 0; i < region.length; i++) {
           create(i*64); 
        }
           
        
    
        
       
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(getCombinedCamera());

        batch.begin();
        
//        batch.draw(bg, 0 , 0,getViewWidth(), getViewHeight());
       
        //batch.draw(button, 0, 0);
//        batch.draw(panel, 0 , 0 , getViewWidth()/4, getViewHeight());
//        batch.draw(region[0],panel.getWidth()- getViewWidth(), 0, getViewWidth()/8 , getViewHeight()/8);
//        batch.draw(region[1], 0, 0, getViewWidth()/8, getViewHeight()/8);
//        batch.draw(region[4], 0, 0, getViewWidth()/8, getViewHeight()/8);
         
          batch.draw(balance, getViewWidth() - balance.getWidth() + 109 , 0, getViewWidth()/3 , getViewHeight()/8); 
          batch.draw(start, getViewWidth() / 2, 0, 150, 50);
         for (int i = 0; i < region.length; i++) {
//           batch.draw(region[i],i*64,0, getViewWidth()/10 , getViewHeight()/8);
            
        }

         
          
           
           
        

      

//         batch.draw(balance, getViewWidth() - balance.getWidth() + 109 , 0, getViewWidth()/3 , getViewHeight()/8);  
         font.setColor(Color.BLACK);
         font.draw(batch, "" + money, getViewWidth()-90, 33 );
          
           //draws the amount of waves (Teacher's lives) 
           font.draw(batch, "" + wave, getViewWidth()-90 , getViewHeight()-100 );

           
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
               

            

            // Check if the button is pressed
              
              for (int i = 0; i < region.length; i++) {
              
            float buttonX = i*64;
            float buttonY = 0;
            
            if (touch.x > buttonX && touch.x < buttonX + region[i].getRegionWidth()/2
                    && touch.y > buttonY && touch.y < buttonY + region[i].getRegionHeight()/2) {
                 System.out.println(i);
                //unit.createCharacter(touch.x,touch.y);
                
           
               
//                System.out.println("Button " + i);
                createStudent(i);
                
               
               
               
            }

        }
              
               for (Teacher teacher : teachers) {
                  float buttonX = teacher.getX() ;
                  float buttonY = teacher.getY();
                 
                 
//                 
//                   System.out.println("Camera coords " + touch.x + " " + touch.y );
//                   System.out.println("x " + teacher.getX() + " y " + teacher.getY() + "   width: " + teacher.getTextureWidth() + "   height: " + teacher.getTextureHeight());
            
 
                  if (touch.x > buttonX && touch.x < buttonX + teacher.getTextureWidth() 
                          && touch.y > buttonY && touch.y < buttonY + teacher.getTextureHeight()) {
                      teacher.toggleRadius();
                     System.out.println(teacher.toggleRadius());

                     
                  }
                  
              }
//               teachers.add(new Teacher((int)touch.x - (int)teacher.getWidth()/4 ,(int)touch.y  - (int)teacher.getHeight()/4, "student.jpg",100));
             float startX = getViewWidth()/2;
             float startY = 0;
             
             // clicking start button
             if(touch.x > startX && touch.x < startX + 150 && touch.y > startY && touch.y < 50){
                 
             }
              }

    }
    
    
    public void createStudent(int i){
        if(i == 0){
             students.add(new Student(400, 0,100,100, "student.jpg"));
        }
        
    }

    @Override
    public void update(float deltaTime) {

        stage.act(deltaTime);
       stage.draw();
       Gdx.input.setInputProcessor(stage);
    
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

           for (Student student : students) {
           student.dispose();
   
         }
           
               for (Teacher teacher : teachers) {
           teacher.dispose(); 
           
   
         }
           
       
    }
    
    
	public void create (int i) {
                
		stage = new Stage();

              
		final Skin skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("badlogic", new Texture("teacher.jpg"));
       
               
            
                    //valid target
		Image validTargetImage = new Image(skin, "badlogic");
		validTargetImage.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(validTargetImage); 
                
		
               
                Array<Image> inventory = new Array();
                
                
               
                
             
               
            

                //invalid target
		Image invalidTargetImage = new Image(skin, "badlogic");
//		invalidTargetImage.setBounds(542, 148, 55, 267);
                invalidTargetImage.setBounds(50, 365, 492, 50);
                
		stage.addActor(invalidTargetImage);
                
                
                Image sourceImage = new Image(skin, "badlogic");
		sourceImage.setBounds(0,0, getViewWidth()/10 , getViewHeight()/8);
                
                Image sourceImage2 = new Image(skin, "badlogic");
		sourceImage2.setBounds(64,0, getViewWidth()/10 , getViewHeight()/8);
                
                 Image sourceImage3 = new Image(skin, "badlogic");
		sourceImage3.setBounds(128,0, getViewWidth()/10 , getViewHeight()/8);
                
                  Image sourceImage4 = new Image(skin, "badlogic");
		sourceImage4.setBounds(192,0, getViewWidth()/10 , getViewHeight()/8);
                
		stage.addActor(sourceImage);
                inventory.add(sourceImage);
                inventory.add(sourceImage2);
                inventory.add(sourceImage3);
                inventory.add(sourceImage4);
                
                
                //Adding a new source of where the source of where teachers can be deployed
                for (int j = 0; j < inventory.size; j++) {
                stage.addActor(inventory.get(j));
                    System.out.println("invetory " + inventory.size);
          
		DragAndDrop dragAndDrop = new DragAndDrop();
		dragAndDrop.addSource(new Source(inventory.get(j)) {
			public Payload dragStart (InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
                                
                                Teacher item = new Teacher(0, 300, "student.jpg",100);
				payload.setObject(item);

				payload.setDragActor(new Label("Moving", skin));

				Label validLabel = new Label("Valid", skin);
				validLabel.setColor(0, 1, 0, 1);
				payload.setValidDragActor(validLabel);

				Label invalidLabel = new Label("Invalid", skin);
				invalidLabel.setColor(1, 0, 0, 1);
				payload.setInvalidDragActor(invalidLabel);

				return payload;
			}
		});
                
                	dragAndDrop.addTarget(new Target(validTargetImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.GREEN);
                               
				return true;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
                            System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
                           
                           teachers.add(new Teacher((int)x - (int)teachers.get(0).getTextureWidth()/4-10,(int)y  - (int)teachers.get(0).getTextureHeight()/4-10 , "teacher.jpg",100));

                           //teacher[0].getWidth()/4 teacher[0].getHeight()/4
                               
			}
		});
		dragAndDrop.addTarget(new Target(invalidTargetImage) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.RED);
                             
                               
				return false;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
                           
                            
			}
		});
                counter++;
                  }
	}

}

