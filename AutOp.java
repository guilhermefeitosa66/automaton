import java.util.*;

public class AutOp
{
  public Automaton parallelComposition(Automaton a, Automaton b)
  {
    Automaton automaton = new Automaton();
    Transition transition;
    State origin  = null;
    State destination = null;
    List<String> evUniA = new ArrayList<String>();
    List<String> evUniB = new ArrayList<String>();

    for(int i = 0; i < a.getAlfabeto().size(); i++)
    {
      if(b.getAlfabeto().contains(a.getAlfabeto().get(i)) == false)
        evUniA.add(a.getAlfabeto().get(i));
    }

    for(int i = 0; i < b.getAlfabeto().size(); i++)
    {
      if(a.getAlfabeto().contains(b.getAlfabeto().get(i)) == false)
        evUniB.add(b.getAlfabeto().get(i));
    }

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
    
    //criar transições
    //combina todos os estados
    for(int i = 0; i < a.getStates().size(); i++)
    {
      for(int j = 0; j < b.getStates().size(); j++)
      {
        for(int k = 0; k < a.getTransitions().size(); k++)
        {
          for(int l = 0; l < b.getTransitions().size(); l++)
          {
            //filtra as transições que partem do estado que está sendo analisado e possuem eventos em comum 
            if ((a.getStates().get(i) == a.getTransitions().get(k).getOrigin()) && (b.getStates().get(j) == b.getTransitions().get(l).getOrigin()))
            {
              for(int m = 0; m < automaton.getStates().size(); m++)
              {
                if(automaton.getStates().get(m).getLabel().equals(a.getStates().get(i).getLabel() + "," + b.getStates().get(j).getLabel()))
                {
                  //recuperando o objeto estado origin da composição paralela
                  origin = automaton.getStates().get(m);
                }
              }
              
              //eventos comuns
              if(a.getTransitions().get(k).getLabel().equals(b.getTransitions().get(l).getLabel()))
              {
                for(int m = 0; m < automaton.getStates().size(); m++)
                {
                  //recuperando o destination
                  if(automaton.getStates().get(m).getLabel().equals(a.getTransitions().get(k).getDestination().getLabel() + "," + b.getTransitions().get(l).getDestination().getLabel()))
                  {
                    destination = automaton.getStates().get(m);
                  }
                }

                transition = new Transition(origin, destination, a.getTransitions().get(k).getLabel());
                automaton.addTransition(transition);
              }else{
                //pegar eventos privados
                
                //procurar o evento de A1 em sua lista de eventos privados
                //Caso o evento seja privado de A1, adiciona a transição
                //OrigemA1, OrigemA2; DestinoA1, OrigemA2, evento de A1
                
                for(int m = 0; m < evUniA.size(); m++)
                {
                  if(evUniA.get(m).equals(a.getTransitions().get(k).getLabel()))
                  {
                    for(int n = 0; n < automaton.getStates().size(); n++)
                    {
                      if(automaton.getStates().get(n).getLabel().equals(a.getTransitions().get(k).getDestination().getLabel() + "," + b.getStates().get(j).getLabel()))
                      {
                        destination = automaton.getStates().get(n);
                      }
                    }

                    transition = new Transition(origin, destination, a.getTransitions().get(k).getLabel());
                    automaton.addTransition(transition);
                  }
                }
                
                //procurar o evento de A2 em sua lista de eventos privados
                //Caso o evento seja privado de A2, adiciona a transição
                //OrigemA1, OrigemA2; OrigemA1, DestinoA2, evento de A2
                
                for(int m = 0; m < evUniB.size(); m++)
                {
                  if(evUniB.get(m).equals(b.getTransitions().get(l).getLabel()))
                  {
                  for(int n = 0; n < automaton.getStates().size(); n++)
                  {
                    if(automaton.getStates().get(n).getLabel().equals(a.getStates().get(i).getLabel() + "," + b.getTransitions().get(l).getDestination().getLabel()))
                    {
                      destination = automaton.getStates().get(n);
                    }
                  }
                  transition = new Transition(origin, destination, b.getTransitions().get(l).getLabel());
                  automaton.addTransition(transition);
                }
              }
            }
            }
          }
        }
      }
    }

    return automaton;
  }
}