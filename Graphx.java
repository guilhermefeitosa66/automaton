import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import java.net.*;

public class Graphx
{
  Graphics2D g2dBuffer;
  Main main;

  public Graphx(Main main)
  {
    this.main = main;
    g2dBuffer = (Graphics2D) this.main.bufferedImage.getGraphics();
    g2dBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  }

  public void grid()
  {
    /* Draw background */
    g2dBuffer.setColor(Style.BACKGROUND);
    g2dBuffer.fillRect(0, 0, main.WINDOW_WIDTH, main.WINDOW_HEIGHT);
    
    /* Draw grid */
    g2dBuffer.setColor(Style.BORDER);
    g2dBuffer.setStroke(new BasicStroke(1));

    for(int i = Style.W; i < main.WINDOW_WIDTH; i += Style.W)
      g2dBuffer.drawLine(i, 0, i, main.WINDOW_WIDTH);

    for(int i = Style.H; i < main.WINDOW_HEIGHT; i += Style.H)
      g2dBuffer.drawLine(0, i, main.WINDOW_WIDTH, i);
  }

  public void drawState(State state)
  {
    g2dBuffer.setColor(state.getColor());
    g2dBuffer.fillOval(state.getX(), state.getY(), Style.W, Style.H);
    g2dBuffer.setColor(Style.STATE_BORDER);
    g2dBuffer.drawOval(state.getX(), state.getY(), Style.W, Style.H);
    
    if(!state.getInitial())
      g2dBuffer.drawOval(state.getX() + 3, state.getY() + 3, Style.W - 6, Style.H - 6);

    g2dBuffer.setColor(Style.STATE_TEXT);
    g2dBuffer.setFont(Style.FONT);
    g2dBuffer.drawString(
      state.getLabel(),
      state.getX() + Style.TEXT_X - (state.getLabel().length() * 4),
      state.getY() + Style.TEXT_Y
    );
  }

  public void drawTransition(Transition transition)
  {

    /*Line offset*/
    int x1 = transition.getOrigin().getX() + (Style.W / 2);
    int y1 = transition.getOrigin().getY() + (Style.H / 2);
    int x2 = transition.getDestination().getX() + (Style.W / 2);
    int y2 = transition.getDestination().getY() + (Style.H / 2);
    /*Arrow offset by Lapixo*/
    int a = (int) (Style.W / 2) + 5;
    double ang = (Math.atan2(y1 - y2, x1 - x2) * 180 / Math.PI) * -1;
    int xb = (int) (x2 + Math.cos(ang / 180.0 * Math.PI) * a) - 5;
    int yb = (int) (y2 - Math.sin(ang / 180.0 * Math.PI) * a) - 5;
    /*Arrow offset by Lapixo*/

    if(transition.getOrigin() != transition.getDestination())
    {
      g2dBuffer.setColor(Style.TRANSITION_BORDER);
      g2dBuffer.drawLine(x1, y1, x2, y2);
      g2dBuffer.fillOval(xb, yb, 10, 10);

      g2dBuffer.setColor(Style.TRANSITION_TEXT);
      g2dBuffer.setFont(Style.FONT);
      g2dBuffer.drawString(transition.getLabel(), (x1 + x2) / 2, (y1 + y2) / 2);
    }else{ 
      g2dBuffer.setColor(Style.TRANSITION_BORDER);
      g2dBuffer.drawRoundRect(
        x1 - Style.W,
        (int) (y1 - (Style.H * 0.3)),
        Style.W,
        (int) (Style.H * 0.6),
        (int) (Style.W * 0.6),
        (int) (Style.H * 0.6)
      );

      g2dBuffer.fillOval(
        x1 - (Style.W / 2) - 5,
        (int) (y1 - (Style.H * 0.3) - 5),
        10, 10
      );

      g2dBuffer.setColor(Style.TRANSITION_TEXT);
      g2dBuffer.setFont(Style.FONT);
      g2dBuffer.drawString(transition.getLabel(), x1 - (Style.W) - (8 * transition.getLabel().length()), y1 + 5);
    }
  }

  public void automaton()
  {
    for(Transition transition : main.automaton.getTransitions())
      drawTransition(transition);
    for(State state : main.automaton.getStates())
      drawState(state);
  }
}