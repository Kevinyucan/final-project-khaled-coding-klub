/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

import States.StateManager;

/**
 *
 * @author yuk4142
 */
public class Math extends Unit{
    
    private int damage;
    private int movement;
    private int health;
    
    
    public Math(int x, int y,int health, String textureName, int movement){
        super(x,y,textureName,movement);
        
        this.movement = movement;
        this.damage = damage;
        this.health = health; 
 
    }
    
    
}
