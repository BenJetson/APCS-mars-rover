
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
    private int[] homeLocation = new int[2];
    
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
        this.homeLocation[0] = x;
        this.homeLocation[1] = y;
    }
    
    // set/get
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDirectionName() {
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
    
    public void move(int numSpaces)
    {
        if (isAlive) {
            if (dir == 0)
            {
                y += numSpaces;
            }
            else if (dir == 0.5) {
                x += Math.cos(Math.PI / 4) * numSpaces;
                y += Math.sin(Math.PI / 4) * numSpaces;
            }
            else if (dir == 1)
            {
                x += numSpaces;
            }
            else if (dir == 1.5) {
                x = Math.cos(Math.PI / 4) * numSpaces;
                y -= Math.sin(Math.PI / 4) * numSpaces;
            }
            else if (dir == 2)
            {
                y -= numSpaces;
            }
            else if (dir == 2.5) {
                x -= Math.cos(Math.PI / 4) * numSpaces;
                y -= Math.sin(Math.PI / 4) * numSpaces;
            }
            else if (dir == 3) {
                x -= numSpaces;
            }
            else if (dir == 3.5) {
                x -= Math.cos(Math.PI / 4) * numSpaces;
                y += Math.sin(Math.PI / 4) * numSpaces;
            }
            
            System.out.printf("%s moved %d spaces in direction %f.%n", name, numSpaces, dir);
        } else {
            System.err.printf("ERROR: %s can't move -- it's dead!%n", this.name);
        }
    }
    
    public void move() {
        move(1);
    }
    
    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.printf("%n teleported to (%d, %d).%n", this.x, this.y);
    }
    
    public void goHome() {
        teleport(homeLocation[0], homeLocation[1]);
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
    
    public void rotateRight() {
        dir += 0.5;
        
        if (dir == 4)
        {
            dir = 0;
        }
        
        System.out.println(name + " turned to the right");        
    }
    
    public void rotate(double numRotations) {
        if (numRotations != 0 && numRotations % 0.5 == 0) {
            dir += numRotations;
            if (dir >= 4) {
                dir = dir % 4;
            } else if (dir < 0) {
                dir = (Math.abs(dir) - 4) % 4;
            }
            System.out.printf("%s rotated %f times, resulting in direction %f.%n",
                                name, numRotations, dir);
        } else {
            System.err.printf("ERROR: %s cannot rotate %f turns. Must not equal zero and "
                               + "be factor of 1/2.%n", name, numRotations);
        }
    }
    
    public void takePic() {
        System.out.printf("%s took a picture at [%d, %d] facing %s.%n", 
                          name, x, y, this.getDirectionName());
        this.numPics++;
    }

    public String toString() 
    {
        return String.format("Rover[name=%s, x=%f, y=%f, dir=%f, %n" +
                             "      numPics=%d, isAlive=%b, homeLocation = [%d, %d]]%n",
                               name, x, y, dir, numPics, isAlive,
                               homeLocation[0], homeLocation[1]);
    }
}
