package forms;

import java.io.*;

public class FileHandler{
	
	public static void SaveObject(Object object, String filename)
	{
        try
        {   
            //Saving of object in a file
        	FileOutputStream file = new FileOutputStream(filename);
        	ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(object);
            
            file.close();
            out.close();
             
            System.out.println("Object has been serialized");
 
        } 
        
        catch (IOException e) 
        {
			e.printStackTrace();
		}
	}
	
	public static Object LoadObject(String filename)
	{
		Object object = null;
		try
        {   
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            object = in.readObject();
             
            in.close();
            file.close();
             
            System.out.println("Form loaded");
        }
         
        catch(IOException e)
        {
            e.printStackTrace();
        }
         
        catch(ClassNotFoundException e)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        
        return object;
	}

}
