package Main;
/**
 * Represents an ID consisting of municipality, lot, and section numbers.
 */
public class ID
{
    public final short municipalityNumber;
    public final int lotNumber;
    public final short sectionNumber;
    /**
     * Constructs an ID with given municipality number, lot number, and section number.
     * Validates each part using MunicipalityDictionary and specific rules.
     *
     * @param municipalityNumber The municipality number of the ID.
     * @param lotNumber          The lot number of the ID.
     * @param sectionNumber      The section number of the ID.
     * @throws IllegalArgumentException If any of the parameters are invalid.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ID(short municipalityNumber, int lotNumber, short sectionNumber) throws IllegalArgumentException
    {
        MunicipalityDictionary.getMunicipality(municipalityNumber); //Throws IllegalArgumentException
        if(lotNumber < 0)
        {
            throw new IllegalArgumentException("Lot number cannot be negative");
        }
        else if(lotNumber > 99999)
        {
            throw new IllegalArgumentException("Lot number cannot be longer than 5 digits");
        }
        if(sectionNumber < 0)
        {
            throw new IllegalArgumentException("Section number cannot be negative");
        }
        else if(sectionNumber > 9999)
        {
            throw new IllegalArgumentException("Section number cannot be longer than 4 digits");
        }
        this.municipalityNumber = municipalityNumber;
        this.lotNumber = lotNumber;
        this.sectionNumber = sectionNumber;
    }
    /**
     * Constructs an ID by parsing a String representation of the ID.
     *
     * @param id The string representation of the ID.
     * @throws IllegalArgumentException If the string format is incorrect.
     */
    public ID(String id) throws IllegalArgumentException
    {
        this(parseID(id)); //Throws IllegalArgumentException
    }
    /**
     * Copy constructor to create a new ID from an existing ID.
     *
     * @param id The existing ID object.
     */
    public ID(ID id)
    {
        this.municipalityNumber = id.municipalityNumber;
        this.lotNumber = id.lotNumber;
        this.sectionNumber = id.sectionNumber;
    }
    /**
     * Converts the ID object to its string representation.
     *
     * @return The string representation of the ID in the format "AAAA-BB/CC".
     */
    @Override
    public String toString()
    {
        return Short.toString(municipalityNumber) + "-" + Integer.toString(lotNumber) + "/" + Short.toString(sectionNumber);
    }
    /**
     * Compares this ID object to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof ID id))
        {
            return false;
        }
        return(municipalityNumber == id.municipalityNumber && lotNumber == id.lotNumber && sectionNumber == id.sectionNumber);
    }
    /**
     * Parses a string representation of an ID and creates an ID object.
     *
     * @param s The string to parse as an ID.
     * @return The parsed ID object.
     * @throws IllegalArgumentException if the provided string is empty or has an invalid format.
     */
    public static ID parseID(String s) throws IllegalArgumentException
    {
        if(s.isEmpty())
        {
            throw new IllegalArgumentException("ID is empty");
        }
        String[] IDSplit = s.split("-");
        try
        {
            short municipalityNumber = Short.parseShort(IDSplit[0]); //Throws NumberFormatException
            String[] IDSplitSub = IDSplit[1].split("/"); //throws ArrayIndexOutOfBoundsException
            if(IDSplitSub.length != 2)
            {
                throw new ArrayIndexOutOfBoundsException();
            }
            return new ID(municipalityNumber, Integer.parseInt(IDSplitSub[0]), Short.parseShort(IDSplitSub[1])); //throws IllegalArgumentException and NumberFormatException
        } catch(ArrayIndexOutOfBoundsException | NumberFormatException e)
        {
            throw new IllegalArgumentException("ID format is wrong.");
        }
    }
}