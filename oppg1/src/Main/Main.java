package Main;
import Main.InputUtil.InputUtil;
import Main.Menu.*;

import javax.lang.model.type.ArrayType;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main class for the program.
 */
public class Main
{
    static PropertyRegistry pr = new PropertyRegistry();
    static Scanner sc = new Scanner(System.in);
    static boolean flag = true;
    public static void main(String[] args)
    {
        try
        {
            MunicipalityDictionary.LoadMunicipalities("src/Main/Data/municipalities.csv");
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        Menu main =    new Menu(new Page[]
                {
                        new Page("Select action:", new Option[]
                                {
                                        new Option("1. Register new property", 2),
                                        new Option("2. Show all properties", 3),
                                        new Option("3. Get property by ID", 4),
                                        new Option("4. Get average area", 5),
                                        new Option("5. Remove property by ...", 1),
                                        new Option("5. Exit program", 9)
                                }),
                        new Page("Remove property by:", new Option[]
                                {
                                        new Option("1. ID", 6),
                                        new Option("2. Name", 7),
                                        new Option("3. Owner", 8),
                                        new Option("4. Return to main menu", 0)
                                }),
                        new Endpoint(Utility.RegisterProperty, 0),      //2
                        new Endpoint(Utility.DisplayProperties, 0),     //3
                        new Endpoint(Utility.GetPropertyByID, 0),       //4
                        new Endpoint(Utility.GetAverageArea, 0),        //5
                        new Endpoint(Utility.RemovePropertyById, 0),    //6
                        new Endpoint(Utility.RemovePropertyByName, 0),  //7
                        new Endpoint(Utility.RemovePropertyByOwner, 0), //8
                        new Endpoint(Utility.ExitProgram, 0)            //9
                });
        while(flag)
        {
            main.Run(sc);
        }
    }
    public class Utility
    {
        public static Function RegisterProperty = () ->
        {
            ID id;
            String name;
            float area;
            String owner;
            while(true)
            {
                try
                {
                    System.out.print("Enter ID: ");
                    id = new ID(sc.nextLine());
                    if(pr.PropertyExists(id))
                    {
                        throw new IllegalArgumentException("Property already exists");
                    }
                    break;
                } catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter an ID on the format: AAAA-BB/CC");
                }
            }
            while(true)
            {
                try
                {
                    System.out.print("Enter name(can be blank): ");
                    name = sc.nextLine();
                    new Property(id, name, 0.0f, "nAn");
                    break;
                } catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            while(true)
            {
                try
                {
                    area = InputUtil.InputFloat("Enter area: ", "You must enter a valid flaot", 0, Float.MAX_VALUE, sc);
                    sc.nextLine();
                    new Property(id, name, area, "nAn");
                    break;
                } catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            while(true)
            {
                try
                {
                    System.out.print("Enter owner: ");
                    owner = sc.nextLine();
                    pr.AddProperty(id, name, area, owner);
                    break;
                } catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        };
        public static Function DisplayProperties = () ->
        {
            System.out.println(pr.toString());
        };
        public static Function GetPropertyByID = () ->
        {
            boolean flag = true;
            while(flag)
            {
                try
                {
                    System.out.println("Enter ID: ");
                    System.out.println(pr.GetPropertyByID(new ID(sc.nextLine())));
                    flag = false;
                } catch(IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter an ID on the format: AAAA-BB/CC");
                }
            }
        };
        public static Function GetAverageArea = () ->
        {
            try
            {
                System.out.println(pr.GetAverageArea());
            } catch(ArithmeticException e)
            {
                System.out.println(e.getMessage());
            }
        };
        public static Function RemovePropertyById = () ->
        {
            try
            {
                ID id;
                while(true)
                {
                    System.out.println("Enter ID: ");
                    try
                    {
                        id = new ID(sc.nextLine());
                        break;
                    } catch(IllegalArgumentException e)
                    {
                        System.out.println(e.getMessage());
                        System.out.println("Please enter an ID on the format: AAAA-BB/CC");
                    }
                }
                System.out.println(pr.GetPropertyByID(id));
            } catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        };
        public static Function RemovePropertyByName = () ->
        {
            try
            {
                System.out.println("Enter name:");
                Property[] properties = pr.GetPropertiesByName(sc.nextLine());
                if(properties.length == 1)
                {
                    System.out.println("Removed property:" + properties[0].toString());
                }
                else
                {
                    System.out.println("Removed the following properties:");
                    for(Property property : properties)
                    {
                        System.out.println(property.toString());
                    }
                }
            } catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        };
        public static Function RemovePropertyByOwner = () ->
        {
            try
            {
                System.out.println("Enter owner:");
                Property[] properties = pr.GetPropertiesByOwner(sc.nextLine());
                if(properties.length == 1)
                {
                    System.out.println("Removed property:" + properties[0].toString());
                }
                else
                {
                    System.out.println("Removed the following properties:");
                    for(Property property : properties)
                    {
                        System.out.println(property.toString());
                    }
                }
            } catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        };
        public static Function ExitProgram = () ->
        {
            flag = false;
        };
    }
}