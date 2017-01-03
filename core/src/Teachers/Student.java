/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

/**
 *
 * @author yuk4142, Khaled and Victor Guanine 
 */
public class Student extends Unit {
    
    private int movement;
    private int health;
  
    public Student(int x, int y, int movement, int health, String textureName){
        super(x,y,textureName);
        
        this.health = health;
        this.movement = movement;
        
        
        
        
        
    }
    
     public int getSpeed(){
        return this.movement;
    }
     
     public int getHealth(){
         return this.health;
     }
     
     
}
