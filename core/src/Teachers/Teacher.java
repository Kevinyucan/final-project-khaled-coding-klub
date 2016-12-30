/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Teachers;

/**
 *
 * @author yuk4142
 */
public class Teacher extends Unit {
    
      public Teacher(int x, int y, int movement, String textureName, int radius){
        super(x,y,textureName,radius);
        
        
    }

    public boolean collides(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
