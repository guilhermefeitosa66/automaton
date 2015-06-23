import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Open
{
  public Automaton open(String fileName)
  {
    Automaton automaton = null;

    try
    {
      FileInputStream file = new FileInputStream("saves/" + fileName + ".aut");
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