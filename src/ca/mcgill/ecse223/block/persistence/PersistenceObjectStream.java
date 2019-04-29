package ca.mcgill.ecse223.block.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenceObjectStream {
  
  private static String fileName = "output.txt";
  
  public static void serialize(Object object) {
    FileOutputStream fileOut;
    try {
      fileOut = new FileOutputStream(fileName);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(object);
      out.close();
      fileOut.close();
    } catch (Exception e) {
      // TODO Auto-generated catch block
    	
      throw new RuntimeException("Could not save data to file '" + fileName + "'.");
      
    }
  }
  
  public static void setFileName(String fileName) {
    PersistenceObjectStream.fileName = fileName;
  }

  public static Object deserialize() {
    Object o = null;
    try {
    FileInputStream fileInput = new FileInputStream(fileName);
    ObjectInputStream in = new ObjectInputStream(fileInput);
    o = in.readObject();
    in.close();
    fileInput.close();
    }catch(Exception e){
      o = null;
    }
    
    return o;
    
  }
 
}
