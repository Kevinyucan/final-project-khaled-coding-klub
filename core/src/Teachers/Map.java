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
import com.pls.game.Game;

/**
 *
 * @author Hadida & KEVINNNNN & Guanification of Guanity
 */
public class Map {
ShapeRenderer sr;
    public Map() {
        sr = new ShapeRenderer();

    }

    
    /**
     * The manually assigned path that students follow in the map
     * @return returns each piece of path (in this case each line segment)
     */
    public Array<Vector2> getAssignedPath() {
        
        //creates new array of paths that is considered vector2 (x,y)
        Array<Vector2> path = new Array<Vector2>();
    
           
            //adds paths accordingly
            path.add(new Vector2(338, 1));
            path.add(new Vector2(338, 148));
            path.add(new Vector2(542, 148));
            path.add(new Vector2(542, Gdx.graphics.getHeight() - 115));
            path.add(new Vector2(50, Gdx.graphics.getHeight() - 115));
            path.add(new Vector2(50, 270));
            path.add(new Vector2(257, 270));
            path.add(new Vector2(257, 0));

         

        //returns the path that is added back to the Unit Class 
        return path;

    }
    
    /**
     * Rectangles that are used to outline the path, THIS IS FOR TESTING PURPOSES (NOT DRAWN), in case you want to see it 
     */
    public void drawSquares(){
        
        //sets renderer to white
           sr.setColor(Color.WHITE);
           
          //just like batch.begin();, this initializes the shape renderer in order to draw shapes
            sr.begin(ShapeRenderer.ShapeType.Line);

            //draws rectangles accordingly by manually inserting 
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
