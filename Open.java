import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Open
{
  public Automaton open(String fileName)
  {
    Automaton automaton;
    try
    {
      FileInputStream file = new FileInputStream("saves/" + fileName + ".automaton");
      ObjectInputStream object = new ObjectInputStream(file);
      automaton = (Automaton) object.readObject();
      object.close();
      System.out.println("opened!");
      return automaton;
    }catch(Exception e){
      System.out.println("error opening...");
      //return null;
      return new Automaton();
    }
  }
}