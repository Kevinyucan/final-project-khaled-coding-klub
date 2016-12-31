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
    
    public Student(int x, int y, int movement, String textureName, int radius){
        super(x,y,movement,textureName,radius);
        
        
        
        
    }
    
     public int getSpeed(){
        return this.movement;
    }
}
