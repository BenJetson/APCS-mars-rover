 

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
        Rover r5 = new Rover("Motion");
        
        r5.rotateRight();
        for (int i = 0; i < 5; i++) {
            r5.move();
        }
        r5.rotateRight();
        r5.move();
        r5.move();
        System.out.println(r5);
        
        r4.kill(r5);
        r5.kill(r5);
        
        r4.rotate(6);
        
        r1.setName("Sojourner");
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
