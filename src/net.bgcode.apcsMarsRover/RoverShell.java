import java.util.Scanner;


/**
 * Write a description of class RoverShell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RoverShell
{   
    
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_MOVE = "move";
    public static final String COMMAND_KILL = "kill";
    public static final String COMMAND_ROTATE = "rotate";
    public static final String COMMAND_TAKEPHOTO = "take photo";
    public static final String COMMAND_TELEPORT = "teleport";
    public static final String COMMAND_GOHOME = "go home";
    public static final String COMMAND_CHARGE = "charge";
    public static final String COMMAND_TRANSMIT = "transmit";
    public static final String COMMAND_STATUS = "status";

    
    public static void main(String[] args) {
        boolean isRunning = true;
        SimpleScanner input = new SimpleScanner();
        
        Rover r1 = new Rover("Sojourner");
        Rover r2 = new Rover("Curiosity");
        Rover r3 = new Rover("Spirit");
        
        RoverGroup group = new RoverGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);
        
        while (isRunning) {
            System.out.println("Enter the name of the rover you wish to use >>>");
            String roverName = input.readString();
            Rover selectedRover = null;
            if (!roverName.equals(COMMAND_EXIT)) {
                selectedRover = group.find(roverName);
            } else {
                isRunning = false;
            }
            
            if (selectedRover instanceof Rover) {
                
                System.out.printf("Enter action for rover '%s' to perform >>>", roverName);
                String action = input.readString();
                
                if (action.equals(COMMAND_ROTATE)) {
                    System.out.println("Turn 'left' or 'right' ? >>>");
                    String direction = input.readString();
                    
                    if (direction.equals("right")) {
                        selectedRover.rotateRight();
                    } else if (direction.equals("left")) {
                        selectedRover.rotateLeft();
                    } else {
                        System.err.println("ERROR: Invalid direction!");
                    }
                } else if (action.equals(COMMAND_MOVE)) {
                    System.out.println("How many spaces to move? >>>");
                    int spaces = input.readInt();
                    selectedRover.move(spaces);
                } else if (action.equals(COMMAND_TAKEPHOTO)) {
                    selectedRover.takePic();
                } else if (action.equals(COMMAND_TRANSMIT)) {
                    selectedRover.transmitPics();
                } else if (action.equals(COMMAND_CHARGE)) {
                    System.out.printf("Rover '%s' currently posesses '%d' energy.%nHow much to charge?%n", roverName, selectedRover.getEnergyLevel());
                    int amount = input.readInt();
                    selectedRover.charge(amount);
                } else if (action.equals(COMMAND_KILL)) {
                    System.out.println("Enter name of rover to kill >>>");
                    String endangeredRoverName = input.readString();
                    Rover endangeredRover = group.find(endangeredRoverName);
                    if (endangeredRover instanceof Rover) {
                        selectedRover.kill(endangeredRover);
                    } else {
                        System.err.printf("ERROR: Rover '%s' does not exist!%n", endangeredRoverName);
                    }
                } else if (action.equals(COMMAND_TELEPORT)) {
                    System.out.println("Enter x coordinate >>>");
                    double tpx = input.readDouble();
                    System.out.println("Enter y coordinate >>>");
                    double tpy = input.readDouble();
                    selectedRover.teleport(tpx, tpy);
                } else if (action.equals(COMMAND_GOHOME)) {
                    selectedRover.goHome();
                } else if (action.equals(COMMAND_STATUS)) {
                    System.out.println();
                } else {
                    System.err.printf("ERROR: '%s' is not a recognized command.%n", action);
                }
                System.out.println(selectedRover);
            } else if (isRunning) {
                System.err.printf("ERROR: Rover '%s' not found.%n", roverName);
            }
        }
        System.out.println("Goodbye!");
    }

}
