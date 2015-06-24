import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public class Automaton implements Serializable
{
  private State initial;
  private State currentState;
  private ArrayList<State> states;
  private ArrayList<Transition> transitions;
  private ArrayList<String> alfabeto;

  public Automaton()
  {
    this.initial = null;
    this.currentState = null;
    this.states = new ArrayList<State>();
    this.transitions = new ArrayList<Transition>();
    this.alfabeto = new ArrayList<String>();
  }

  public State getInitial()
  {
    return this.initial;
  }

  public State getCurrentState()
  {
    return this.currentState;
  }

  public ArrayList<State> getStates()
  {
    return this.states;
  }

  public ArrayList<Transition> getTransitions()
  {
    return this.transitions;
  }

  public ArrayList<String> getAlfabeto()
  {
    return this.alfabeto;
  }

  public void setInitial(State initial)
  {
    this.initial = initial;
  }

  public void setCurrentState(State currentState)
  {
    this.currentState = currentState;
  }

  public void addState(State state)
  {
    if(this.states.size() == 0)
      this.initial = state;
    this.states.add(state);
  }

  public boolean addTransition(Transition transition)
  {
    boolean exists = false;

    for(int i = transitions.size() - 1; i >= 0; i--)
    {
      if(transitions.get(i).equals(transition))
      {
        exists = true;
        transitions.get(i).setLabel(transition.getLabel());
        updateAlfabeto();
      }
    }

    if(!exists)
    {
      this.transitions.add(transition);
      if(!alfabeto.contains(transition.getLabel()))
        alfabeto.add(transition.getLabel());
    }
    return exists;
  }

  public boolean removeTransition(Transition transition)
  {
    boolean remove = false;

    for(int i = transitions.size() - 1; i >= 0; i--)
    {
      if(transitions.get(i).equals(transition))
      {
        remove = true;
        transitions.remove(i);
        updateAlfabeto();
      }
    }

    return remove;
  }

  public void updateAlfabeto()
  {
    alfabeto = new ArrayList<String>();

    for(Transition t : transitions)
      if(!alfabeto.contains(t.getLabel()))
        alfabeto.add(t.getLabel());
    
    for(String s : alfabeto)
      System.out.println("alfabeto: " + s);
    System.out.println("----------------");
  }

  public boolean existsState(String label)
  {
    boolean exists = false;
    for(State state : states)
      if(state.getLabel().equals(label))
        exists = true;
    return exists;
  }

  public void removeState(State state)
  {
    if(state != null)
    {
      for(int i = transitions.size() - 1; i >= 0; i--)
        if(transitions.get(i).getOrigin().getLabel().equals(state.getLabel()) || transitions.get(i).getDestination().getLabel().equals(state.getLabel()))
          transitions.remove(i);

      for(int i = states.size() - 1; i >= 0; i--)
        if(states.get(i).getLabel().equals(state.getLabel()))
          states.remove(i);

      updateAlfabeto();
      state = null;
    }
  }
}