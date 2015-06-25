import java.util.*;

public class Operation
{
  public static Automaton AFNDtoAFD(){}

  public static Automaton trim(){}

  public static Automaton parallelComposition(Automaton a, Automaton b)
  {
    Automaton automaton = new Automaton();

    /* Creating states */
    for(int i = 0; i < a.getStates().size(); i++)
    {
      for(int j = 0; j < b.getStates().size(); j++)
      {
        State s = null;
        State sa = a.getStates().get(i);
        State sb = b.getStates().get(j);

        if(!sa.getInitial() && !sb.getInitial())
        {
          s = new State(Style.W, Style.H, sa.getLabel() + "," + sb.getLabel(), false);
        }else{
          s = new State(Style.W, Style.H, sa.getLabel() + "," + sb.getLabel(), true);
        }

        automaton.addState(s);

        if(a.getInitial().equals(sa) && b.getInitial().equals(sb))
        {
          automaton.setInitial(s);
        }
      }
    }/* Creating states END*/
  }

  public static Automaton product(){}

  public static Automaton minimize(){}
}