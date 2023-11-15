package Main.Menu;
/**
 * The Option class represents options within a Page within a Menu.
 * @author Jonas Hazeland Baugerud
 */
public class Option
{
    /**
     * The text displayed when a Page containing this Option is showed.
     */
    private final String text;
    /**
     * Refers to the index of the Page (Stored in Menu.pages) to display if this Option is selected.
     * Warning: Indices of Page objects must be positive integers starting from 0. The Menu class does NOT allow flexible indexing of Page objects.
     */
    private final int redirect;
    /**
         * Primary constructor for Option. In practice this will be used to create Options which will be copied using the copy constructor into a Page object.
     * @param text      The text displayed when a Page containing this Option is showed.
     * @param redirect  Refers to the index of the Page (Stored in Menu.pages) to display if this Option is selected.
     */
    public Option(String text, int redirect)
    {
        assert (redirect > 0); // Redirect connections cannot be negative as they represent indices in the Menu object's Page[] array.
        this.text = text;
        this.redirect = redirect;
    }
    /**
     * Copy constructor
     * @param in        Option object to copy from.
     */
    public Option(Option in)
    {
        this.text = in.text;
        this.redirect = in.redirect;
    }
    /**
     * Getter for Page to redirect to upon this Option being selected.
     * @return      Index of Page object stored within array Main.pages.
     */
    int getRedirect() {return redirect;}
    /**
     * Displays the text of the Option.
     */
    void Display() {System.out.println(text);}
}