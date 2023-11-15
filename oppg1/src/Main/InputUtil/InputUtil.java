package Main.InputUtil;
import java.util.Scanner;
/**
 * The `InputUtil` class provides utility methods for taking user input with error handling.
 */
public final class InputUtil {
    /**
     * Private constructor to ensure that this class is not instantiated.
     * @throws RuntimeException Always throws a `RuntimeException` with the message "Static class may not be instantiated."
     */
    private InputUtil()
    {
        throw new RuntimeException("Static class may not be instantiated.");
    }
    /**
     * Prompts the user to input a float within a specified range and handles errors.
     *
     * @param prompt The message displayed to the user as a prompt.
     * @param error The error message displayed when the input is not valid.
     * @param min The minimum value allowed for the input.
     * @param max The maximum value allowed for the input.
     * @param sc The `Scanner` object used for input.
     * @return The valid float input within the specified range.
     */
    public static float InputFloat(String prompt, String error, float min, float max, Scanner sc)
    {
        while (true)
        {
            try
            {
                System.out.print(prompt);
                float temp = sc.nextFloat();
                if (temp >= min && temp <= max)
                {
                    return temp;
                }
                else
                {
                    System.out.println(error);
                }
            } catch (Exception e)
            {
                System.out.println(error);
                sc.next();
            }
        }
    }
    /**
     * Prompts the user to input an integer within a specified range and handles errors.
     *
     * @param prompt The message displayed to the user as a prompt.
     * @param error The error message displayed when the input is not valid.
     * @param min The minimum value allowed for the input.
     * @param max The maximum value allowed for the input.
     * @param sc The `Scanner` object used for input.
     * @return The valid integer input within the specified range.
     */
    public static int InputInt(String prompt, String error, int min, int max, Scanner sc)
    {
        while (true)
        {
            try
            {
                System.out.print(prompt);
                int temp = sc.nextInt();
                if (temp >= min && temp <= max)
                {
                    return temp;
                }
                else
                {
                    System.out.println(error);
                }
            } catch (Exception e)
            {
                System.out.println(error);
                sc.next();
            }
        }
    }
    /**
     * Prompts the user to input a short within a specified range and handles errors.
     *
     * @param prompt The message displayed to the user as a prompt.
     * @param error The error message displayed when the input is not valid.
     * @param min The minimum value allowed for the input.
     * @param max The maximum value allowed for the input.
     * @param sc The `Scanner` object used for input.
     * @return The valid short input within the specified range.
     */
    public static short InputShort(String prompt, String error, short min, short max, Scanner sc)
    {
        while (true)
        {
            try
            {
                System.out.print(prompt);
                short temp = sc.nextShort();
                if (temp >= min && temp <= max)
                {
                    return temp;
                }
                else
                {
                    System.out.println(error);
                }
            } catch (Exception e)
            {
                System.out.println(error);
                sc.next();
            }
        }
    }
}