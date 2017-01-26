/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pls.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pls.game.Game;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Hadida
 */
public class AISprites extends Sprite {

    //The character essentially holds x and y coordinates 
    private Vector2 velocity = new Vector2();
    //Indicates how many pixels per seconds we move by, (tolerance is a way to notify once we reach 3 pixels before a waypoint)

    //the amount of waypoints 
    private int waypoint = 0;

    //speed of which student is travelling at
    private int speed;

    //an array of set points  (holds x and y ) 
    private Array<Vector2> path;

    /**
     * Constructor method for the moving students known as the aiSprites
     *
     * @param sprite the student which is passed in as sprite with vital
     * information (X,Y)
     * @param path the path which the students follow
     * @param speed the speed of which student travels at
     */
    public AISprites(Sprite sprite, Array<Vector2> path, int speed) {

        //takes inheritance of the upper class which is the Sprite in this case 
        super(sprite);
        //initializes the path 
        this.path = path;

        //initializes the speed of which student is travelling at 
        this.speed = speed;
    }

    /**
     * The drawing method that continuously draws the students moving
     *
     * @param spriteBatch the student's batch that is being passed in from Unit
     */
    public void draw(SpriteBatch spriteBatch) {
        //updates the screen with the changes applied
        update();
        //uses the upper class to draw the student 
        super.draw(spriteBatch);
    }

    /**
     * Constantly updates the students in terms of movement
     */
    public void update() {

        //the angle of the current point to next point which is used by arc tangent (inverse of tangent) hence (y,x)
        float angle = (float) Math.atan2(path.get(waypoint).y - getY(), path.get(waypoint).x - getX());

        //Sets the velocity ( Y , X ), the relation between must add up to 1, if Cos is 0.9, sin is 0.1 
        velocity.set((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);

        //update position (move to the waypoint)
        setPosition(getNextX(), getNextY());

        //set rotation of the character, converts radians to degrees, You can mess around with it 
//        setRotation(angle * MathUtils.radiansToDegrees);
        // checks if we have reached a point by calling method out
        if (isWayPointReached()) {
            //Once the unit reached current point, update position
            setPosition(path.get(waypoint).x, path.get(waypoint).y);
            //if we reached a point, restart amount
            if (waypoint + 1 >= path.size) {

                waypoint = 0;
                //otherwise add on till the path is complete
            } else {
                waypoint++;
            }
        }

    }

    /**
     * boolean that returns true or false if a unit has reached a waypoint
     *
     * @return true if the unit has reached a point on the path
     */
    public boolean isWayPointReached() {
        //by doing speed time which determines at which pixels should the unit be considered at a waypoint. 
        return Math.abs(path.get(waypoint).x - getX()) <= speed * Gdx.graphics.getDeltaTime() && Math.abs(path.get(waypoint).y - getY()) <= speed * Gdx.graphics.getDeltaTime();
    }

    /**
     * getter method that returns the path the unit is on
     *
     * @return the path that the unit is on
     */
    public Array<Vector2> getPath() {
        return path;
    }

    /**
     * a getter that is used to access the number of waypoint
     *
     * @return the number of waypoints
     */
    public int getWaypoint() {
        return waypoint;
    }

    /**
     * a getter that is used to get the next X coordinate of the student which
     * is caclulated
     *
     * @return the next x coordinate of the student that is moving
     */
    public float getNextX() {
        //takes the current X coordinate of student then multiples it by velocity and time 
        return getX() + velocity.x * Gdx.graphics.getDeltaTime();
    }

    /**
     * a getter that is used to get the next Y coordinate of the student which
     * is caclulated
     *
     * @return the next Y coordinate of the student that is moving
     */
    public float getNextY() {
        //takes the current Y coordinate of student then multiples it by velocity and time 
        return getY() + velocity.y * Gdx.graphics.getDeltaTime();
    }

}
