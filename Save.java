import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;

public class Save
{
  public void save(Automaton automaton, String url)
  {
    try
    {
      FileOutputStream file = new FileOutputStream(url+".aut");
      ObjectOutputStream object = new ObjectOutputStream(file);
      object.writeObject(automaton);
      object.close();
      System.out.println("saved!");
    }catch(Exception e){
      System.out.println("error saving...");
    }
  }
}