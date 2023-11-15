package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Provides utility functions for managing a dictionary of municipalities.
 */
public final class MunicipalityDictionary
{
    /**
     * Inner class to represent a Municipality.
     */
    static class Municipality
    {
        public short id;
        public String name;
        /**
         * Constructor for a Municipality.
         *
         * @param id   The ID of the municipality.
         * @param name The name of the municipality.
         */
        public Municipality(short id, String name)
        {
            this.id = id;
            this.name = name;
        }
    }

    private static Municipality[] municipalities;
    private static boolean municipalitiesLoaded = false;
    private MunicipalityDictionary() {throw new RuntimeException("Static class may not be instantiated.");}
    /**
     * Loads municipalities from a file.
     *
     * @param path The path to the file containing municipality data.
     * @throws FileNotFoundException if the file is not found.
     */
    public static void LoadMunicipalities(String path) throws FileNotFoundException
    {
        assert (!municipalitiesLoaded);
        Scanner sc;
        sc = new Scanner(new File(path)); //throws FileNotFoundException
        sc.useDelimiter("\n");
        ArrayList<Municipality> temp = new ArrayList<>();
        while (sc.hasNext())
        {
            String[] s = sc.next().split(",");
            temp.add(new Municipality(Short.parseShort(s[0]), s[1].split("\r")[0]));
        }
        municipalities = temp.toArray(new Municipality[0]);
        municipalitiesLoaded = true;
    }
    /**
     * Gets the name of a municipality based on its ID.
     *
     * @param id The ID of the municipality.
     * @return The name of the municipality.
     * @throws IllegalArgumentException if the municipality does not exist.
     */
    public static String getMunicipality(int id) throws IllegalArgumentException
    {
        for (Municipality municipality : municipalities)
        {
            if (municipality.id == id)
            {
                return municipality.name;
            }
        }
        throw new IllegalArgumentException("Municipality does not exist");
    }
    /**
     * Gets the ID of a municipality based on its name.
     *
     * @param name The name of the municipality.
     * @return The ID of the municipality.
     * @throws IllegalArgumentException if the municipality does not exist.
     */
    public static short getMunicipalityId(String name) throws IllegalArgumentException
    {
        for (Municipality municipality : municipalities)
        {
            if (municipality.name.equalsIgnoreCase(name))
            {
                return municipality.id;
            }
        }
        throw new IllegalArgumentException("Municipality does not exist");
    }
}