
/**
 * Write a description of class Rover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rover
{
    // fields
    private String name;
    private double x;
    private double y;
    private double dir; // 0=North, 1=East, 2=South, 3=West
    private int numPics;
    private boolean isAlive;
    
    // constructor(s)
    
    public Rover() {
        this(null, 0, 0, 0);
    }
    
    public Rover(String name)
    {
        this(name, 0, 0, 0);
    }
    
    public Rover(String name, int x, int y, int dir) {
        this.name = name;
        this.x = x;
        this.y = y; 
        this.dir = dir;
        this.isAlive = true;
    }
    
    // set/get
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDirection() {
        if (dir == 0) {
            return "North";
        } else if (dir ==0.5) {
            return "Northeast";
        } else if (dir == 1) {
            return "East";
        } else if (dir == 1.5) {
            return "Southeast";
        } else if (dir == 2) {
            return "South";
        } else if (dir == 2.5) {
            return "Southwest";
        } else if (dir == 3) {
            return "West";
        } else if (dir == 3.5) {
            return "Northwest";
        }
        return "Unknown";
    }
    
    // methods - stuff the Rover can do
    public boolean die() {
        if (isAlive) {
            isAlive = false;
            return true;
        }
        return false;
    }
    
    public boolean kill(Rover other) {
        if (this.isAlive) {
            boolean success = other.die();
            if (success) {
                System.out.printf("%s killed %s.%n", this.name, other.name);
            } else {
                System.err.printf("ERROR: %s can't kill %s because %s was already dead!%n",
                                   this.name, other.name, other.name);
            }
            return success;
        }
        System.err.printf("ERROR: %s can't kill other rovers -- it's dead!%n", this.name);
        return false;
    }
    
    public void move()
    {
        if (dir == 0)
        {
            y++;
        }
        else if (dir == 0.5) {
            x += Math.cos(Math.PI / 4);
            y += Math.sin(Math.PI / 4);
        }
        else if (dir == 1)
        {
            x++;
        }
        else if (dir == 1.5) {
            x = Math.cos(Math.PI / 4);
            y -= Math.sin(Math.PI / 4);
        }
        else if (dir == 2)
        {
            y--;
        }
        else if (dir == 2.5) {
            x -= Math.cos(Math.PI / 4);
            y -= Math.sin(Math.PI / 4);
        }
        else if (dir == 3) {
            x--;
        }
        else if (dir == 3.5) {
            x -= Math.cos(Math.PI / 4);
            y += Math.sin(Math.PI / 4);
        }
        
        System.out.println(name + " moved in direction " + dir);
    }
    
    public void rotateLeft() 
    {
        dir -= 0.5;
        
        if (dir < 0)
        {
            dir = 3.5;
        }
        
        System.out.println(name + " turned to the left");        
    }
    
    public void rotateRight()
    {
        dir += 0.5;
        
        if (dir == 4)
        {
            dir = 0;
        }
        
        System.out.println(name + " turned to the right");        
    }
    
    public void takePic() {
        System.out.printf("%n took a picture at [%d, %d] facing %s.", 
                          name, x, y, this.getDirection());
        this.numPics++;
    }

    public String toString() 
    {
        return "Rover[name=" + name + ", x=" + x + ", y=" + y + ", dir=" + dir + "]";
    }
}
