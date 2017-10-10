
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
    private int energyLevel;
    private boolean isAlive;
    private final int[] homeLocation = new int[2];

    public static final int MAX_PICS = 5;
    public static final int MAX_ENERGY = 200;
    public static final int ENERGY_MOVEMENT = 5;
    public static final int ENERGY_PHOTO = 2;
    public static final int ENERGY_TRANSMIT = 10;
    public static final int ENERGY_TELEPORT = 100;
    public static final int ENERGY_KILL = 80;

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
        this.energyLevel = MAX_ENERGY;
        this.homeLocation[0] = x;
        this.homeLocation[1] = y;
    }

    // set/get

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public int getEnergyLevel() {
        return energyLevel;
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

    // death
    private boolean die() {
        if (isAlive) {
            isAlive = false;
            return true;
        }
        return false;
    }

    public boolean kill(Rover other) {
        if (lessEnergyCheck("kill other rovers", ENERGY_KILL)) {
            boolean success = other.die();
            if (success) {
                System.out.printf("%s killed %s.%n", this.name, other.name);
            } else {
                System.err.printf("ERROR: %s can't kill %s because %s was already dead!%n",
                    this.name, other.name, other.name);
            }
            return success;
        }
        return false;
    }

    // motion
    public void move(int numSpaces)
    {
        if (lessEnergyCheck(String.format("move %d space(s)", numSpaces), ENERGY_MOVEMENT, numSpaces)) {
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
            energyLevel -= numSpaces * ENERGY_MOVEMENT;
            System.out.printf("%s moved %d spaces in direction %f.%n",
                name, numSpaces, dir);
        }
    }

    public void move() {
        move(1);
    }

    public void teleport(double x, double y) {
        if (lessEnergyCheck("teleport", ENERGY_TELEPORT)) {
            this.x = x;
            this.y = y;
            System.out.printf("%s teleported to (%f, %f).%n", name, this.x, this.y);
        }
    }

    public void goHome() {
        System.out.printf("%s is attempting to teleport to its home location...%n", name);
        teleport(homeLocation[0], homeLocation[1]);
    }

    public void rotateLeft() 
    {
        if (lessEnergyCheck("Rotate", ENERGY_MOVEMENT)) {
            dir -= 0.5;

            if (dir < 0)
            {
                dir = 3.5;
            }
            
            System.out.println(name + " turned to the left");
        }
    }

    public void rotateRight() {
        if (lessEnergyCheck("rotate right", ENERGY_MOVEMENT)) {
            dir += 0.5;
    
            if (dir == 4)
            {
                dir = 0;
            }
    
            System.out.println(name + " turned to the right");        
        }
    }

    public void rotate(double numRotations) {
        boolean isValid = (numRotations != 0 && numRotations % 0.5 == 0);
        if (isValid && lessEnergyCheck("rotate", ENERGY_MOVEMENT, (int)(numRotations * 2))) {
            dir += numRotations;
            if (dir >= 4) {
                dir = dir % 4;
            } else if (dir < 0) {
                dir = (Math.abs(dir) - 4) % 4;
            }
            System.out.printf("%s rotated %f times, resulting in direction %f.%n",
                name, numRotations, dir);
        } else if (!isValid) {
            System.out.printf("ERROR: %s can't rotate -- %f is an invalid rotation factor.%n", name, numRotations);
        }
            
    }

    // extra functionality
    public boolean charge(int amount) {
        if (energyLevel + amount <= MAX_ENERGY) {
            energyLevel += amount;
            return true;
        }
        System.err.printf("ERROR: Cannot charge rover %s by amount %d. Max is %d.%n",
            name, amount, MAX_ENERGY);
        return false;
    }

    public void takePic() {
        boolean hasMemory = (numPics < MAX_PICS);
        if (lessEnergyCheck("take a picture", ENERGY_PHOTO) && hasMemory) {
            System.out.printf("%s took a picture at [%f, %f] facing %s.%n", 
                name, x, y, this.getDirectionName());
            this.numPics++;
        } else if (isAlive && !hasMemory) {
            System.err.printf("ERROR: Memory on %s is full. Transmit photos to take more.%n",
                name);
        }
    }

    public void transmitPics() {
        if (lessEnergyCheck("transmit photos", ENERGY_TRANSMIT, numPics)) {
            System.out.printf("%s transmitted %d photos to earth and erased local copies.%n",
                            name, numPics);
            numPics = 0;
        }
    }

    public boolean hasEnergy(int amount) {
        return (energyLevel - amount > 0);
    }

    public boolean hasEnergy(int amount, int multiplier) {
        return hasEnergy(amount * multiplier);
    }
    
    private boolean lessEnergy(int amount, int multiplier) {
        if (hasEnergy(amount, multiplier)) {
            energyLevel -= (amount * multiplier);
            return true;
        }
        throw new IllegalArgumentException("Error: insufficient energy. Use lessEnergyCheck() or hasEnergy() to check before using lessEnergy().");
    }
    
    private boolean lessEnergy(int amount) {
        return lessEnergy(amount, 1);
    }
    
    private boolean lessEnergyCheck(String actionName, int energyRequired, int energyMultiplier) {
        if (!isAlive) {
            System.err.printf("ERROR: %s can't do action '%s' -- it's dead!%n", name, actionName);
            return false;
        } else if (!hasEnergy(energyRequired, energyMultiplier)) {
            System.err.printf("ERROR: %s posesses insufficient energy to perform action '%s' -- recharge first!%n",
                name, actionName);
            return false;
        }
        lessEnergy(energyRequired, energyMultiplier);
        return true;
    }
    
    private boolean lessEnergyCheck(String actionName, int energyRequired) {
        return lessEnergyCheck(actionName, energyRequired, 1);
    }

    public String toString() {
        return String.format("Rover[name=%s, x=%f, y=%f, dir=%f, %n" +
            "      numPics=%d, isAlive=%b, homeLocation = [%d, %d]%n" +
            "      energyLevel=%d]%n",
            name, x, y, dir, numPics, isAlive,
            homeLocation[0], homeLocation[1], energyLevel);
    }
}
