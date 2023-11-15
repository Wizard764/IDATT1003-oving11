package Main.Menu;
/**
 * The Endpoint class contains children of Page that perform an action rather than just offer a navigational Page.
 * @author Jonas Hazeland Baugerud
 */
public class Endpoint extends Page
{
    /**
     * Contains a reference to a function listed in Main.Utility. This is the code that will run upon this endpoint being reached.
     */
    private final Function function;
    /**
     * Contains the index of the Page(stored in Main.pages) to redirect to after running Endpoint function.
     */
    private final int redirect;
    /**
     * Primary constructor for Page. In practice this will be used to create Pages which will be copied using the copy constructor into a Menu object.
     * @param function  Contains a reference to a function listed in Main.Utility. This is the code that will run upon this endpoint being reached.
     * @param redirect  Contains the index of the Page(stored in Main.pages) to redirect to after running Endpoint function.
     */
    public Endpoint(Function function, int redirect)
    {
        super("", new Option[]{});
        this.function = function;
        this.redirect = redirect;
    }
    /**
     * Copy constructor
     * @param endpoint  Endpoint object to copy from.
     */
    public Endpoint(Endpoint endpoint)
    {
        super("", new Option[]{});
        this.function = endpoint.function;
        this.redirect = endpoint.redirect;
    }
    /**
     * Get the index of the Page this Endpoint points to after its function is run.
     * @return  Index of the Page this Endpoint points to after its function is run.
     */
    @Override
    public int getRedirect() {return redirect;}
    /**
     * Run the Endpoint's function.
     */
    @Override
    public void Run() {function.apply();}
}