import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

public class State implements Serializable
{
  private String label;
  private boolean initial;
  private int x, y;
  private Color color;

  public State(int x, int y, String label, boolean initial)
  {
    this.x = x;
    this.y = y;
    this.label = label;
    this.initial = initial;
    this.color = Style.defaultStateColor;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public String getLabel()
  {
    return this.label;
  }

  public boolean getInitial()
  {
    return this.initial;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public void setInitial(boolean initial)
  {
    this.initial = initial;
  }

  public void setColor(Color color)
  {
    this.color = color;
  }

  public void offset(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public boolean equals(State state)
  {
    if(state == null)
      return false;
    else if(state.label == this.label && state.initial == this.initial)
      return true;
    else
      return false;
  }

  public State clone()
  {
    return new State(this.x, this.y, this.label, this.initial);
  }

  // public Ellipse2D shape()
  // {
  //   Ellipse2D s = new Ellipse2D.Float();
  //   s.setFrame(this.x, this.y, 30, 30);
  //   return s;
  // }
}