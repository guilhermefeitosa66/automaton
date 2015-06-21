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
    this.states.add(state);
  }

  public void addTransition(Transition transition)
  {
    this.transitions.add(transition);
  }

  public void addAlfabeto(String alfabeto)
  {
    this.alfabeto.add(alfabeto);
  }
}