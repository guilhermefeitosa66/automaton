import java.io.Serializable;

public class Transition implements Serializable
{
  private State origin;
  private State destination;
  private String label;

  public Transition(State origin, State destination, String label)
  {
    this.origin = origin;
    this.destination = destination;
    this.label = label;
  }

  public State getOrigin()
  {
    return this.origin;
  }

  public State getDestination()
  {
    return this.destination;
  }

  public String getLabel()
  {
    return this.label;
  }

  public void setOrigin(State origin)
  {
    this.origin = origin;
  }

  public void setDestination(State destination)
  {
    this.destination = destination;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }
}