
/**
 * Write a description of class RoverRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoverRunner
{
    public static void main(String[] arrrggggggs)
    {
        Rover r1 = new Rover("Curiosity");
        Rover r2 = new Rover("Spirit");
        Rover r3 = new Rover("Adventure");
        Rover r4 = new Rover("Danger");
        
        System.out.println(r1);
        
        r1.move();
        System.out.println(r1);

        r1.rotateRight();
        System.out.println(r1);
        
        r2.rotateLeft();
        System.out.println(r2);
        
        r2.move();
        System.out.println(r2);
        
        r1.move();
        System.out.println(r1);

        System.out.println(r3);

        r3.move();
        r3.rotateLeft();
        r3.move();
        r3.move();
        r3.rotateLeft();
        r3.rotateRight();

        System.out.println(r3);


        System.out.println(r4);

        r4.rotateRight();
        r4.move();
        r4.move();
        r4.rotateRight();
        r4.move();

        System.out.println(r4);
    }
}
