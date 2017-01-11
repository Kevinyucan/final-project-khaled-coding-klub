/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Hadida & KEVINNNNN & Guanification of Guanity
 */
public class Map {
ShapeRenderer sr;
    public Map() {
        sr = new ShapeRenderer();

    }

    //new changes
    public Array<Vector2> getRandomPath() {
        Array<Vector2> path = new Array<Vector2>();
        for (int i = 0; i < MathUtils.random(5, 10); i++) {
            //adds random point, 
            //path.add(new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight())));
            path.add(new Vector2(338, 0));
            path.add(new Vector2(338, 148));
            path.add(new Vector2(542, 148));
            path.add(new Vector2(542, Gdx.graphics.getHeight() - 115));

            path.add(new Vector2(50, Gdx.graphics.getHeight() - 115));
            path.add(new Vector2(50, 270));
            path.add(new Vector2(257, 270));
            path.add(new Vector2(257, 0));

         

        }
        return path;

    }
    
    public void drawSquares(){
           sr.setColor(Color.WHITE);
            sr.begin(ShapeRenderer.ShapeType.Line);

            sr.rect(542, 148, 55, 267);
            sr.rect(50, 365, 492, 50);
            sr.rect(50, 270, 55, 95);
            sr.rect(50, 270, 207, 30);
            sr.rect(257, 0, 50, 300);
            sr.rect(307, 0, 30, 132);
            sr.rect(338, 0, 50, 190);
            sr.rect(338, 148, 205, 42);
            
            

            sr.end();
   
    }
   
    
}
