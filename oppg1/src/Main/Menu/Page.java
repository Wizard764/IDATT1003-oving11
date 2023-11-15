package Main.Menu;
/**
 * The Page class represents a Page within a Menu.
 * @author Jonas Hazeland Baugerud
 */
public class Page
{
    /**
     * Title/prompt of the page. This will be printed to console upon Page.Display being called.
     */
    private final String title;
    /**
     * List of options the user may choose from. These are displayed together with the title to form a Page in a Menu.
     */
    private final Option[] options;
    /**
     * Primary constructor for Page. In practice this will be used to create Pages which will be copied using the copy constructor into a Menu object.
     * @param title     Title/prompt of the page. This will be printed to console upon Page.Display being called.
     * @param options   List of options the user may choose from. These are displayed together with the title to form a Page in a Menu.
     */
    public Page(String title, Option[] options)
    {
        this.title = title;
        this.options = options;
    }
    /**
     * Copy constructor
     * @param in        Page object to copy from.
     */
    public Page(Page in)
    {
        this.title = in.title;
        this.options = in.options;
    }
    /**
     * Getter for Page certain Option within this Page redirects to upon being selected.
     * @param option    Index of selected Option. Warning: Must be a positive integer. Option indexing is non-flexible.
     * @return          Index of Page object stored within Menu.pages.
     */
    public int getOptionRedirect(int option) {return options[option].getRedirect();}
    public int getNoOptions(){return options.length;};
    /**
     * Display the Page with title and Options.
     */
    public void Display()
    {
        System.out.println(title);
        for(Option option : options) {option.Display();}
    }
    /**
     * Needed to avoid error caused by compiler not finding Run in Page due to the way it's called on Endpoints in the Menu class. Should never run.
     * @return      Error avoidance
     */
    public int getRedirect(){return 0;};
    /**
     * Needed to avoid error caused by compiler not finding Run in Page due to the way it's called on Endpoints in the Menu class. Should never run.
     */
    public void Run(){};
}