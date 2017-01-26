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
 * PlayState method, where most components in the game occur!
 *
 * @author hadik9595
 */
public class PlayState extends State {

    // instance variables, each containing a texture
    //background texture
    private Texture bg;
    //button texture
    private Texture button;
    //panel texture 
    private Texture panel;
    //texture for start button
    private Texture start;

    //money variable used to store money 
    public int money = 200;
    //an int variable that stores integer when students pass, subtract by 1
    public int studentsPassing = 100;
    //boolean variable that switches when mouse is clicked
    private boolean toggle;
    //an int variable used to keep score when students fail
    private int score;
    //stage variable used to unify actors and put them in action
    private Stage stage;
    //font instance, used for drawing score and etc
    BitmapFont font = new BitmapFont();
    //Array of sprites for student's movement 
    private Array<AISprites> aiSprites;
    //Array for students, which contains all the vital info such as texture 
    private Array<Student> students;
    //Array for teachers, which contains all the vital info such as texture 
    private Array<Teacher> teachers;
    //Array for button images 
    private Array<Image> buttonImages;
    //variable used to separate individual pieces within a picture
    private TextureRegion region[];
    //texture for balance 
    private Texture balance;
    //wave which corresponds to the the amount of students spawning each time
    private int wave = 10;

    /**
     * Constructor of PlayState where all variables such as textures are
     * initialized here
     *
     * @param sm the state of which is being passed in
     */
    public PlayState(StateManager sm) {
        //since this is extended to state, all inheritance is passed to playState
        super(sm);

        //sets camera view to fit the entire screen that way coordinates are aligned 
        setCameraView(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //declaring bunch of textures below
        //assigning background with a texture 
        bg = new Texture("Background.jpg");
        //assigning button with a texture
        button = new Texture("button.jpg");
        //assigning the button panel with a texture
        panel = new Texture("units.jpg");
        //assigning the balance where money is drawn with a texture
        balance = new Texture("money.jpg");
        //assigning the start button with a texture
        start = new Texture("start.jpg");

        //the array used to add students to the screen
        students = new Array<Student>();
        //the array used to add teachers to the screen
        teachers = new Array<Teacher>();
        //array used to add bullets (paper) to the screen when called
        buttonImages = new Array<Image>();

        //region is essentially texture region where one picture can be used as many models
        region = new TextureRegion[5];
        //assigning each square with a texture 
        region[0] = new TextureRegion(panel, 0, 0, 136, 156);
        region[1] = new TextureRegion(panel, 136, 0, 136, 156);
        region[2] = new TextureRegion(panel, 136 * 2, 0, 136, 156);
        region[3] = new TextureRegion(panel, 136 * 3, 0, 136, 156);
        region[4] = new TextureRegion(panel, 136 * 4, 0, 136, 156);

        //Initializes the buttons that way it connects to the playstate and therefore connects with rest
        createButtons();

    }

    /**
     * Renders models, draws background and is responsible for all elements that
     * occur in screen
     *
     * @param batch that is passed by from the game class, and is the necessary
     * key to drawing
     */
    @Override
    public void render(SpriteBatch batch) {
        // Sets the projection matrix to be used by this Batch
        batch.setProjectionMatrix(getCombinedCamera());

        // Calls the batch in order to draw which will setup appropriate render states
        batch.begin();

        // Draws the background on to the screen, and sets its size according to screen
        batch.draw(bg, 0, 0, getViewWidth(), getViewHeight());

        //Draws the balance score on the bottom right corner 
        batch.draw(balance, getViewWidth() - balance.getWidth() + 109, 0, getViewWidth() / 3, getViewHeight() / 8);
        //Draws the start button beside the balance score
        batch.draw(start, getViewWidth() / 2, 0, 150, 50);
        //Draws each button spaced out accordingly
        for (int i = 0; i < region.length; i++) {
            batch.draw(region[i], i * getViewWidth() / 11, 0, getViewWidth() / 10, getViewHeight() / 8);
        }
        font.setColor(Color.BLACK);
        font.draw(batch, "" + money, getViewWidth() - 90, 33);

        //draws the amount of waves (Teacher's lives) 
        font.setColor(Color.RED);
        font.draw(batch, "Passing: " + studentsPassing, getViewWidth() - 90, getViewHeight() - 10);

        // draws the score
        font.draw(batch, "Score: " + score, getViewWidth() - 90, getViewHeight() - 30);

        //goes through each student in the students loop, where they are rendered each
        for (Student student : students) {
            student.studentRender(batch);
        }

        //goes through each teacher in the teachers loop, where they are rendered each
        for (Teacher teacher : teachers) {
            teacher.teacherRender(batch);

        }

//ends the batch which tells the program to stop drawing 
        batch.end();

    }

    /**
     * A toggle setter that flips teacher's toggle from false to true, vice
     * versa
     *
     * @return the boolean toggle
     */
    public boolean toggling() {

        //if toggle is true, then set it to false, vice versa
        if (toggle) {
            return toggle = false;
        } else {
            return toggle = true;
        }

    }

    /**
     * Handles any input done by the user, specifically any mouse clicks
     */
    @Override
    public void handleInput() {

        //an if condition that is critically important, it checks whether the mouse is clicked or not 
        if (Gdx.input.justTouched()) {

            // Get the mouse click/touch position
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

            // Convert that point to "game coordinates"
            unproject(touch);

            // Check if any of the buttons found in panel to be pressed 
            for (int i = 0; i < region.length; i++) {
                //takes buttons X and Y in the panel
                float buttonX = i * 64;
                float buttonY = 0;

                //checks if the buttons in the pannel is pressed, (this is very useful because we can create different type of teachers using numbering system)
                if (touch.x > buttonX && touch.x < buttonX + region[i].getRegionWidth() / 2
                        && touch.y > buttonY && touch.y < buttonY + region[i].getRegionHeight() / 2) {

                    //toggles a boolean which indicates that dragging is happening (this is to reduce memory consumption when dragging and dropping isnt happening)
                    toggling();
                    //checks if toggle is true
                    if (toggle) {

                        //initiate drag and drop! 
                        createButtons();
                    }

                }

            }

            // goes through each teacher that exists in the game
            for (Teacher teacher : teachers) {
                //sets their x and y in float variables 
                float buttonX = teacher.getX();
                float buttonY = teacher.getY();

                //if the mouse coordinates are in between the assigned coordinates for each teacher 
                if (touch.x > buttonX && touch.x < buttonX + teacher.getTextureWidth()
                        && touch.y > buttonY && touch.y < buttonY + teacher.getTextureHeight()) {
                    //toggles radius which displays the damaging radius (this is good if there are more types of teachers)
                    teacher.toggleRadius();

                }

            }
            //stores the button coordinates in float variables 
            float startX = getViewWidth() / 2;
            float startY = 0;

            // clicking start button
            if (touch.x > startX && touch.x < startX + 150 && touch.y > startY && touch.y < 50) {
                //add 5 to each time the button is pressed which increases oount of students
                wave = wave + 5;
                //passes in the wave number to creating student method which progressively increases count of students being spawned 
                createStudent(wave);

            }

        }

    }

    /**
     * Creates students when the button is pressed
     *
     * @param i the parameter of which when pressed the amount of students
     * increase
     */
    public void createStudent(int i) {
        //a loop created so that it spawns in students accordingly as waves increase
        for (int j = 0; j < i; j++) {
            //just to space out students 
            j = j * 15;
            //creates the students by assigning x , y coordinate and speed as well as texture name
            students.add(new Student(400 + j, 1, 100, 100, "student.jpg"));
        }

    }

    /**
     * a getter method used to get score every time a student fails
     *
     * @return the score as an int variable
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Constantly updates the models,stage and any actions that occur in the
     * game
     *
     * @param deltaTime the time that it takes to perform a function
     */
    @Override
    public void update(float deltaTime) {
        //triggers toggle to true in order to function the buttons (this is to reduce memory consumption)
        if (toggle) {
            //passes in the delta time, which constantly updates the stage per second
            stage.act(deltaTime);
            //draws the stage 
            stage.draw();
            //Sets the InputProcessor that will receive all touch and key input events.
            Gdx.input.setInputProcessor(stage);
        }

        //goes through every teacher in a for loop
        for (Teacher teacher : teachers) {
            //updates every teacher per second
            teacher.update(deltaTime);

        }

        //goes through every students in a for loop
        for (Student student : students) {
            //updates every student per second
            student.update(deltaTime);

            //gets the numerical value of each student in the game 
            for (int i = 0; i < students.size; i++) {

                //if student's y reaches 0, the student has passed 
                if (students.get(i).getY() <= 0) {
                    //removes the student from the screen
                    students.removeIndex(i);
                    //decreases the students passing value by 1 
                    studentsPassing = studentsPassing - 1;

                }

            }
        }

        //goes through each student in the entire game 
        for (Student student : students) {
            //gets the numerical value of each student (numbers the students 
            for (int i = 0; i < students.size; i++) {
                //once the student's health reaches 0 or below (since the shooting rate is too fast) 
                if (students.get(i).getHealth() <= 0) {
                    //removes the student with a health under or equal to 0
                    students.removeIndex(i);
                    //add $100 to current balance 
                    money = money + 100;
                    //increase score by 1 
                    score++;

                }

            }

        }

        //a for loop capable of going through each teacher that is in the game 
        for (Teacher teacher : teachers) {
            //a for loop capable of going through each student that is in the game 
            for (Student currentStudent : students) {
                //a temporary integer used to store the student's health
                int temp = currentStudent.getHealth();
                //if a student (rectangle) collides with a teacher's radius (circle)
                if (currentStudent.collides(teacher)) {
                    //the teacher shoots assignments at students in attempt to fail the student
                    teacher.shoot();
                    //gets the position of the current student that way they can communicate
                    teacher.getStudent(currentStudent);
                    //If the student collides with a paper that a teacher has thrown 
                    if (teacher.bulletCollide(currentStudent)) {

                        // the temporary value is decreased (which is the health) 
                        temp--;
                        // the student's health is set to the temporary value 
                        currentStudent.setHealth(temp);
                        //remove the bullet according to the student hit
                        teacher.removeBullet(currentStudent);

                    }
                }

            }
        }
        // if students passing reaches 0    
        if (studentsPassing <= 0) {
            StateManager gsm = getStateManager();
            // changes to the game over menu
            gsm.push(new GameOverState(gsm, this.getScore()));
        }

    }

    /**
     * a getter for the amount of money the player currently has
     *
     * @return the amount of money the player has
     */
    public int getMoney() {
        return money;
    }

    /**
     * disposes of all materials used so that it reduces memory consumption when
     * it comes to playing on mobiles
     */
    @Override
    public void dispose() {
        //disposes the background
        bg.dispose();
        //disposes the button
        button.dispose();
        //disposes the fonts
        font.dispose();
        //disposes the panel
        panel.dispose();
        //disposes the balance texture
        balance.dispose();
        //disposes the stage necessary for drag and drop
        stage.dispose();

        //disposes all students spawned
        for (Student student : students) {
            student.dispose();

        }

        //disposes all teachers spawned
        for (Teacher teacher : teachers) {
            teacher.dispose();

        }

    }

    /**
     * method that is responsible for buttons as well as drag and dropping
     *
     * @param i the button number of which different teachers can be dragged and
     * dropped
     */
    public void createButtons() {
        //toggles the button to off that way the buttons wouldn't consume lots of memory
        toggling();
        //creates a new stage that is used to draw actors on
        stage = new Stage();

        //Skins behave as textures, they're created to label different parts of the map with vaid and invalid areas
        final Skin validTexture = new Skin();
        final Skin invalidTexture = new Skin();
        //used for drawing model that follows the mouse crusor 
        final Skin skin = new Skin();

        //By adding the skins, it defines what texture to use 
        skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
        //set texture for teacher when dragged from button
        skin.add("badlogic", new Texture("teacher.jpg"));
        //sets texture for valid areas 
        validTexture.add("badlogic", new Texture("valid.jpg"));
        //sets texture for invalid areas
        invalidTexture.add("badlogic", new Texture("invalid.jpg"));

        //creates images for valid targets, in this case everywhere is valid except for designated spots (path)
        Image validTargetImage = new Image(validTexture, "badlogic");
        validTargetImage.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //adds the actor to the stage in order to communicate with the different actors with stage
        stage.addActor(validTargetImage);

        //creates new array of buttons 
        Array<Image> buttons = new Array();
        //creates new array for invalid areas (the path)
        Array<Image> invalid = new Array();

        //invalid targets that are images created, which each correspond to their position (ex, 455,66)
        Image invalidTargetImage = new Image(invalidTexture, "badlogic");
        invalidTargetImage.setBounds(50, 365, 492, 50);

        Image invalidTargetImage2 = new Image(invalidTexture, "badlogic");
        invalidTargetImage2.setBounds(542, 148, 55, 267);

        Image invalidTargetImage3 = new Image(invalidTexture, "badlogic");
        invalidTargetImage3.setBounds(50, 270, 55, 95);

        Image invalidTargetImage4 = new Image(invalidTexture, "badlogic");
        invalidTargetImage4.setBounds(50, 270, 207, 30);

        Image invalidTargetImage5 = new Image(invalidTexture, "badlogic");
        invalidTargetImage5.setBounds(257, getViewHeight() / 8, 50, 300);

        Image invalidTargetImage6 = new Image(invalidTexture, "badlogic");
        invalidTargetImage6.setBounds(307, 0, 30, 132);

        Image invalidTargetImage7 = new Image(invalidTexture, "badlogic");
        invalidTargetImage7.setBounds(338, 0, 50, 190);

        Image invalidTargetImage8 = new Image(invalidTexture, "badlogic");
        invalidTargetImage8.setBounds(338, 148, 205, 42);

        Image invalidTargetImage9 = new Image(invalidTexture, "badlogic");
        invalidTargetImage9.setBounds(256 + getViewWidth() / 10, 0, 400, getViewHeight() / 8);

        //Valid targets that are images created, which each correspond to their position on the screen by setting bounds
        Image sourceImage = new Image(skin, "badlogic");
        sourceImage.setBounds(0, 0, getViewWidth() / 10, getViewHeight() / 8);

        Image sourceImage2 = new Image(skin, "badlogic");
        sourceImage2.setBounds(64, 0, getViewWidth() / 10, getViewHeight() / 8);

        Image sourceImage3 = new Image(skin, "badlogic");
        sourceImage3.setBounds(128, 0, getViewWidth() / 10, getViewHeight() / 8);

        Image sourceImage4 = new Image(skin, "badlogic");
        sourceImage4.setBounds(192, 0, getViewWidth() / 10, getViewHeight() / 8);

        Image sourceImage5 = new Image(skin, "badlogic");
        sourceImage5.setBounds(256, 0, getViewWidth() / 10, getViewHeight() / 8);

        //once images are initialized and created, all images are added onto their arrays that were made from above
        invalid.add(invalidTargetImage);
        invalid.add(invalidTargetImage2);
        invalid.add(invalidTargetImage3);
        invalid.add(invalidTargetImage4);
        invalid.add(invalidTargetImage5);
        invalid.add(invalidTargetImage6);
        invalid.add(invalidTargetImage7);
        invalid.add(invalidTargetImage8);
        invalid.add(invalidTargetImage9);

        //The images that are intiailized above are added onto buttons array 
        buttons.add(sourceImage);
        buttons.add(sourceImage2);
        buttons.add(sourceImage3);
        buttons.add(sourceImage4);
        buttons.add(sourceImage5);

        //initializes the function drag and drop so that its implementations are accessable 
        DragAndDrop dragAndDrop = new DragAndDrop();
        //Adding a new source of where the source of where teachers can be deployed
        for (int j = 0; j < buttons.size; j++) {
            //going through the loop, each button is added as an actor 
            stage.addActor(buttons.get(j));

            //adds a new source which means it adds an object that is is able to be dragged 
            dragAndDrop.addSource(new Source(buttons.get(j)) {
                //a method that is for once the mouse begins to drag 
                public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    //initializes a payload which is the object that would be dragged 
                    Payload payload = new Payload();

                    // creates an item that is an image, displays the character when dragged but since bg is above it, which prevents it from being displayed
                    Image item = new Image(skin, "badlogic");

                    //sets boundaries of the image (which means size)
                    item.setBounds(128, 0, getViewWidth() / 10, getViewHeight() / 8);
                    //the object is then set to the payload
                    payload.setObject(item);

                    //once dragging, "Moving" would be displayed which indicates movement of mouse
                    payload.setDragActor(new Label("Moving", skin));

                    //once it lands on a valid area, displays "valid"
                    Label validLabel = new Label("Valid", skin);
                    //the colour is set to green
                    validLabel.setColor(0, 1, 0, 1);
                    //sets the valid drag actor into payload with the green colour and the string "valid"
                    payload.setValidDragActor(validLabel);

                    //same as above, once the mouse lands on an invalid area it would display invalid 
                    Label invalidLabel = new Label("Invalid", skin);
                    //sets the colour to red 
                    invalidLabel.setColor(1, 0, 0, 1);
                    //sets the invalid drag actor into payload with the red colour and the string "invalid"
                    payload.setInvalidDragActor(invalidLabel);

                    //returns the payload once the action is performed 
                    return payload;
                }
            });
        }

        //adds another source known as valid target which is for anywhere but paths 
        dragAndDrop.addTarget(new Target(validTargetImage) {
            //a boolean that returns true if the mouse is in the area set as valid
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                //sets the image that is being dragged to green
                getActor().setColor(Color.GREEN);

                // returns true if mouse is on the valid area
                return true;
            }

            //Once dragging stops, the colour is set back to white 
            public void reset(Source source, Payload payload) {
                getActor().setColor(Color.WHITE);
            }

            //Once the mouse is released the image is dropped and deployed onto the valid area, parameters are x and y, of mouse
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                //if the player has enough money to hire a teacher
                if (money >= 100) {
                    //hires a teacher at the location where the mouse is pointed at 
                    teachers.add(new Teacher((int) x - 25, (int) y - 25, "teacher.jpg", 100));
                    //the money decreases by 100
                    money = money - 100;
                }

            }
        });

        //a loop that goes through each invalid images (or areas)
        for (int j = 0; j < invalid.size; j++) {
            //adds all the invalid places into the stage so that it can be accessed
            stage.addActor(invalid.get(j));

            //add the invalid targets like adding valid targets
            dragAndDrop.addTarget(new Target(invalid.get(j)) {
                //boolean that checks if the mouse is being dragged into an invalid area
                public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                    //sets the image that is being dragged to red 
                    getActor().setColor(Color.RED);

                    //does not perform anything therefore return false
                    return false;
                }

                //reset the colour of the image to white once the mouse lets go at an invalid area, nothing happens
                public void reset(Source source, Payload payload) {
                    getActor().setColor(Color.WHITE);
                }

                //nothing happens, the action is negated since the area that the mouse is pointing at is invalid
                public void drop(Source source, Payload payload, float x, float y, int pointer) {
                }
            });
        }

    }
}
