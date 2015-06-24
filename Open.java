import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.*;

public class Open
{
  public Automaton open(String url)
  {
    Automaton automaton = null;

    try
    {
      FileInputStream file = new FileInputStream(url);
      ObjectInputStream object = new ObjectInputStream(file);
      automaton = (Automaton) object.readObject();
      object.close();
      System.out.println("opened!");
    }catch(Exception e){
      System.out.println("error opening...");
      automaton = null;
    }

    return automaton;
  }
}