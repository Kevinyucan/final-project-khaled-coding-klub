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

    //waypoint
    private int waypoint = 0;

    private int speed;

    //an array of set points  (holds x and y ) 
    private Array<Vector2> path;

    public AISprites(Sprite sprite, Array<Vector2> path, int speed) {
        
        super(sprite);
        this.path = path;

        this.speed = speed;
    }

    public void draw(SpriteBatch spriteBatch) {
        //updates the screen
        update();
        super.draw(spriteBatch);
    }

    public void update() {
       
        //the angle of the current point to next point which is used by arc tangent (inverse of tangent) hence (y,x)
        float angle = (float) Math.atan2(path.get(waypoint).y - getY(), path.get(waypoint).x - getX());

        //Sets the velocity ( Y , X ), the relation between must add up to 1, if Cos is 0.9, sin is 0.1 
        velocity.set((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);

        //update position (move to the waypoint)
        setPosition(getNextX(), getNextY());
        
        //set rotation of the character, converts radians to degrees
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
   

    public int getWaypoint() {
        return waypoint;
    }

    public float getXCord() {
        return velocity.x;
    }

    public float getYCord() {
        return velocity.y;
    }

    public float getNextX() {
        return getX() + velocity.x * Gdx.graphics.getDeltaTime();
    }

    public float getNextY() {
        return getY() + velocity.y * Gdx.graphics.getDeltaTime();
    }

}
