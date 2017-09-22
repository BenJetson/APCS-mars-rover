
/**
 * Write a description of class Rover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rover
{
    // fields
    String name;
    int x;
    int y;
    int dir; // 0=North, 1=East, 2=South, 3=West
    
    
    // constructor(s)
    public Rover(String name)
    {
        this.name = name;
        this.x = 0;
        this.y = 0;
        this.dir = 0;
    }
    
    
    // methods - stuff the Rover can do
    public void move()
    {
        if (dir == 0)
        {
            y += 1;
        }
        else if (dir == 1)
        {
            x += 1;
        }
        else if (dir == 2)
        {
            y -= 1;
        }
        else 
        {
            x -= 1;
        }
        
        System.out.println(name + " moved in direction " + dir);
    }
    
    public void rotateLeft() 
    {
        dir -= 1;
        
        if (dir < 0)
        {
            dir = 3;
        }
        
        System.out.println(name + " turned to the left");        
    }
    
    public void rotateRight()
    {
        dir += 1;
        
        if (dir == 4)
        {
            dir = 0;
        }
        
        System.out.println(name + " turned to the right");        
    }

    public String toString() 
    {
        return "Rover[name=" + name + ", x=" + x + ", y=" + y + ", dir=" + dir + "]";
    }
}
