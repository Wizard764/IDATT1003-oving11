package Main;
/**
 * A class for representing a property.
 */
public class Property
{
    private final ID id;
    private final String name;
    private final float area;
    private final String owner;
    /**
     * Constructor for a Property.
     *
     * @param id                 The ID of the property.
     * @param name               The name of the property. Can be empty.
     * @param area               The area of the property in m^2.
     * @param owner              The owner of the property.
     * @throws IllegalArgumentException if the municipality does not exist.
     */
    public Property(ID id, String name, float area, String owner) throws IllegalArgumentException
    {
        this.id = id; //Throws IllegalArgumentException
        this.name = name;
        this.area = area;
        this.owner = owner;
    }
    /**
     * Constructor for a Property without name. Calls primary constructor.
     *
     * @param id                 The ID of the property.
     * @param area               The area of the property in m^2.
     * @param owner              The owner of the property.
     * @throws IllegalArgumentException if the municipality does not exist.
     */
    public Property(ID id, float area, String owner) throws IllegalArgumentException
    {
        this(id, "", area, owner); //Throws IllegalArgumentException
    }
    /**
     * Returns the unique ID of the property defined by the municipality-, lot- and section-numbers.
     * @return Property-ID. Format:AAAA-BB/CC.
     */
    public ID getID()
    {
        return id;
    }
    /**
     * Retrieves the name of the property.
     *
     * @return The name of the property. Returns an empty string if the name is not set.
     */
    public String getName()
    {
        return name;
    }
    /**
     * Retrieves the area of the property.
     *
     * @return The area of the property in square meters.
     */
    public float getArea()
    {
        return area;
    }
    /**
     * Retrieves the owner's name of the property.
     *
     * @return The name of the owner of the property.
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Constructs a string describing the Property. If a property has no name this is described.
     * @return String containing information about the property.
     */
    @Override
    public String toString()
    {
        String out = "ID: " + getID().toString() + "\tOwner: " + owner + "\tArea: " + area + "\tName: ";
        if(name.isEmpty())
        {
            return out + "no name";
        }
        else
        {
            return out + name;
        }
    }
}