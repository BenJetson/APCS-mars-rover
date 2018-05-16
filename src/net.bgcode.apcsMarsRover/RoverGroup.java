/**
 * Write a description of class RoverRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.List;

public class RoverGroup {

    List<Rover> rovers;
    
    public RoverGroup() {
        this.rovers = new ArrayList<Rover>();
    }
    
    public void add(Rover rover) {
        rovers.add(rover);
    }
    
    public Rover find(String query) {
        
        for (Rover r : rovers) {
            String name = r.getName();
            
            if (name.equals(query)) {
                return r;
            }
        }
        
        return null;
    }
}