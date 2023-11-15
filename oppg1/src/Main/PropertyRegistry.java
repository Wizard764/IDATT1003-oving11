package Main;
import Main.InputUtil.InputUtil;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class represents a Property Registry that manages a list of properties and provides various operations for property management.
 */
public class PropertyRegistry
{
    private ArrayList<Property> properties;
    private ArrayList<ID> IDs; //Used for efficiency. Faster to compare string array rather than retrieving data from Property objects.
    /**
     * Constructs an empty PropertyRegistry.
     */
    public PropertyRegistry()
    {
        properties = new ArrayList<>();
        IDs = new ArrayList<>();
    }
    /**
     * Adds a new property to the registry with a specified ID, name, area, and owner.
     *
     * @param id    The ID of the property.
     * @param name  The name of the property.
     * @param area  The area of the property in square meters.
     * @param owner The owner's name of the property.
     * @throws IllegalArgumentException If the property with the same ID already exists.
     */
    public void AddProperty(ID id, String name, float area, String owner) throws IllegalArgumentException
    {
        if(PropertyExists(id))
        {
            throw new IllegalArgumentException("Property already exists.");
        }
        properties.add(new Property(id, name, area, owner));
        IDs.add(id);
    }
    /**
     * Adds a new property to the registry with a specified ID, area, and owner. The name of the property is set to an empty string.
     *
     * @param id    The ID of the property.
     * @param area  The area of the property in square meters.
     * @param owner The owner's name of the property.
     * @throws IllegalArgumentException If the property with the same ID already exists.
     */
    public void AddProperty(ID id, float area, String owner) throws IllegalArgumentException
    {
        AddProperty(id, "", area, owner); //Throws IllegalArgumentException
    }
    /**
     * Checks if a property with the given ID exists in the registry.
     *
     * @param id_in The ID to check for existence in the registry.
     * @return true if the property exists, false otherwise.
     */
    public boolean PropertyExists(ID id_in)
    {
        boolean tried = false;
        boolean retried = false;
        while(!retried)
        {
            for (ID id : IDs)
            {
                if (id.equals(id_in))
                {
                    return true;
                }
            }
            if(tried)
            {
                retried = true;
            }
            else
            {
                UpdateIDs();
                tried = true;
            }
        }
        return false;
    }
    /**
     * Retrieves a property by its ID.
     *
     * @param id The ID of the property to retrieve.
     * @return The Property object with the specified ID.
     * @throws IllegalArgumentException If no property with the given ID exists.
     */
    public Property GetPropertyByID(ID id) throws IllegalArgumentException
    {
        if(PropertyExists(id))
        {
            int i;
            for(i = 0; i < properties.size(); i++)
            {
                if(IDs.get(i).equals(id))
                {
                    break;
                }
            }
            return properties.get(i);
        }
        throw new IllegalArgumentException("Property doesn't exist.");
    }
    /**
     * Removes a property from the registry by its ID.
     *
     * @param id The ID of the property to be removed.
     * @return A confirmation string indicating successful removal.
     * @throws IllegalArgumentException If the property with the given ID does not exist.
     * @throws Error If an attempt is made to remove a nonexistent property.
     */
    public String RemovePropertyByID(ID id) throws IllegalArgumentException, Error
    {
        Property selected = GetPropertyByID(id);
        for(int i = 0; i < properties.size(); i++)
        {
            if(id.equals(IDs.get(i)))
            {
                String confirmation = "Successfully removed property : " + properties.get(i).toString();
                IDs.remove(i);
                properties.remove(i);
                return confirmation;
            }
        }
        throw new Error("Attempt was made to remove nonexistent property");
    }
    /**
     * Removes one or multiple properties from the registry, based on user selection.
     *
     * @param properties_in An array of properties to potentially remove.
     * @param sc The Scanner object used for reading user input.
     * @return A confirmation string indicating the properties that were removed.
     */
    private String RemoveProperties(Property[] properties_in, Scanner sc) //Should in theory never throw exception because private method may only be called from within class using correctly formatted data.
    {
        if(properties_in.length == 1) //If only one match, just remove it
        {
            return RemovePropertyByID(properties_in[0].getID());
        }
        String prompt = "Multiple matching properties found:";
        int i = 1;
        for(Property property : properties_in) //Construct string of matching properties
        {
            prompt += "\n" + Integer.toString(i) + ". " + property.toString();
            i++;
        }
        prompt += "\n" + Integer.toString(i) + " All of the above";
        //Let user choose which property to remove
        int choice = InputUtil.InputInt(prompt,
                "Enter a number between" + Integer.toString(1) + " and " + Integer.toString(properties_in.length + 1),
                1, properties_in.length + 1, sc);
        if(choice == properties_in.length + 1) //Remove all matching properties
        {
            String confirmation = "";
            for(int j = 0; j < properties_in.length; j++)
            {
                confirmation += RemovePropertyByID(properties_in[j].getID());
                if(j != properties_in.length - 1)
                {
                    confirmation += "\n";
                }
            }
            return confirmation;
        }
        return RemovePropertyByID(properties_in[choice - 1].getID()); //Remove chosen property
    }
    /**
     * Retrieves an array of properties owned by a specific owner.
     *
     * @param owner_in The name of the owner.
     * @return An array of properties owned by the specified owner.
     * @throws IllegalArgumentException If the owner name is blank or no properties are registered to the owner.
     */
    public Property[] GetPropertiesByOwner(String owner_in) throws IllegalArgumentException
    {
        if(owner_in.isEmpty())
        {
            throw new IllegalArgumentException("Owner cannot be blank.");
        }
        ArrayList<Property> temp = new ArrayList<>();
        for(Property property : properties)
        {
            if(property.getOwner().equals(owner_in))
            {
                temp.add(property);
            }
        }
        if(temp.isEmpty())
        {
            throw new IllegalArgumentException("No properties registered to owner");
        }
        return temp.toArray(new Property[0]);
    }
    /**
     * Removes properties from the registry based on the owner's name.
     *
     * @param owner_in The name of the owner.
     * @param sc The Scanner object used for reading user input.
     * @return A string indicating the outcome of the removal process.
     * @throws IllegalArgumentException If the owner name is blank or invalid.
     */
    public String RemovePropertyByOwner(String owner_in, Scanner sc) throws IllegalArgumentException
    {
        return RemoveProperties(GetPropertiesByOwner(owner_in), sc); //throws IllegalArgumentException
    }
    /**
     * Retrieves an array of properties with a specified name.
     *
     * @param name_in The name to filter properties by.
     * @return An array of properties matching the specified name.
     * @throws IllegalArgumentException If the name is empty or no properties with the specified name exist.
     */
    public Property[] GetPropertiesByName(String name_in) throws IllegalArgumentException
    {
        if(name_in.isEmpty())
        {
            throw new IllegalArgumentException("Cannot filter by nameless properties.");
        }
        ArrayList<Property> temp = new ArrayList<>();
        for(Property property : properties)
        {
            if(property.getName().equals(name_in))
            {
                temp.add(property);
            }
        }
        if(temp.isEmpty())
        {
            throw new IllegalArgumentException("No properties with specified name");
        }
        return temp.toArray(new Property[0]);
    }
    /**
     * Removes properties from the registry based on their name.
     *
     * @param name_in The name of the properties to remove.
     * @param sc The Scanner object used for reading user input.
     * @return A string indicating the outcome of the removal process.
     * @throws IllegalArgumentException If the name is empty or invalid.
     */
    public String RemovePropertyByName(String name_in, Scanner sc) throws IllegalArgumentException
    {
        Property[] matchingProperties;
        matchingProperties = GetPropertiesByName(name_in); //throws IllegalArgumentException
        return RemoveProperties(matchingProperties, sc);
    }
    /**
     * Updates the list of IDs based on the current properties in the registry.
     */
    private void UpdateIDs()
    {
        IDs.clear();
        for(Property property : properties)
        {
            IDs.add(property.getID());
        }
    }
    /**
     * Retrieves the number of properties registered in the PropertyRegistry.
     *
     * @return The number of properties registered.
     */
    public int getNoPropertiesRegistered()
    {
        return properties.size();
    }
    /**
     * Calculates the average area of all registered properties.
     *
     * @return The average area of the properties.
     * @throws ArithmeticException If there are no properties to calculate the area of.
     */
    public double GetAverageArea() throws ArithmeticException
    {
        if(properties.size() == 0)
        {
            throw new ArithmeticException("There are no properties to calculate area of.");
        }
        double totArea = 0.0f;
        for(Property property : properties)
        {
            totArea += property.getArea();
        }
        return totArea / (double) properties.size();
    }
    /**
     * Retrieves an array of properties with a specified lot number.
     *
     * @param lotNumber The lot number to filter properties by.
     * @return An array of properties matching the specified lot number.
     */
    public Property[] GetPropertiesByLotNumber(int lotNumber)
    {
        ArrayList<Property> temp = new ArrayList<>();
        for(int i = 0; i < properties.size(); i++)
        {
            if(IDs.get(i).lotNumber == lotNumber)
            {
                temp.add(properties.get(i));
            }
        }
        return temp.toArray(new Property[0]);
    }
    /**
     * Generates a string representation of the PropertyRegistry containing a list of all properties.
     *
     * @return A string representation of all properties in the registry.
     */
    @Override
    public String toString()
    {
        String out = "List of all properties:";
        for(Property property : properties)
        {
            out += "\n" + property.toString();
        }
        return out;
    }
}