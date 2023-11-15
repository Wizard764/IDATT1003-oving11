package Main.Menu;
import java.util.Scanner;
/**
 * The Menu class represents a text-based navigational menu.
 * @author Jonas Hazeland Baugerud
 */
public class Menu
{
    /**
     * List of pages within the menu, one page shown at a time, including Endpoints.
     */
    private final Page[] pages;
    /**
     * //Tracks the current Page to display or Endpoint to run.
     */
    private int currentPage;
    /**
     * Primary constructor for Menu.
     * @param pages Array of Pages contained within this menu. Also contains Endpoints.
     * Warning: Page indexing is non-flexible and is a always a positive integer starting on 0.
     */
    public Menu(Page[] pages)
    {
        this.pages = pages;
        currentPage = 0; //Always display page 0 first.
    }
    /**
     * Main function. Displays the current page of the Menu and takes user input to navigate to other Page or Endpoint.
     * @param sc    Scanner for input purposes.
     */
    public void Run(Scanner sc)
    {
        if(pages[currentPage] instanceof Endpoint) // If chosen Page is an Endpoint.
        {
            pages[currentPage].Run();
            currentPage = pages[currentPage].getRedirect();
        }
        else // Normal purely navigational Pages.
        {
            pages[currentPage].Display();
            currentPage = pages[currentPage].getOptionRedirect(InputUtil.InputInt("Choose an option: ", "You must choose a valid option", 1, pages[currentPage].getNoOptions(), sc) - 1); // Take input from the user for which page to navigate to.
            sc.nextLine();
        }
    }
}