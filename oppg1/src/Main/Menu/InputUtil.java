package Main.Menu;
import java.util.Scanner;
/**
 * The InputUtil class contains static utility for taking Integer input and exception handling.
 * Used for Menu navigation.
 * @author Jonas Hazeland Baugerud
 */
public final class InputUtil
{
    /**
     * Throw exception is class is instantiated.
     */
    private InputUtil() {throw new RuntimeException("Static class may not be instantiated.");}
    /**
     * Returns input from the user in the form of an integer.
     * @param prompt    Prompt to display to the user.
     * @param error     Error to display to the user upon input being the srong format.
     * @param min       Minimum acceptable value.
     * @param max       Maximum acceptable value.
     * @param sc        Scanner for input purposes.
     * @return          Returns an integer min <= return value <= max.
     */
    public static int InputInt(String prompt, String error, int min, int max, Scanner sc)
    {
        while(true)
        {
            try
            {
                System.out.print(prompt);
                int temp = sc.nextInt();
                if(temp >= min && temp <= max) {return temp;}
                else {System.out.println(error);}
            }
            catch (Exception e)
            {
                System.out.println(error);
                sc.next();
            }
        }
    }
}