import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Main extends JFrame implements MouseListener, MouseMotionListener
{
  int WINDOW_WIDTH = 800;
  int WINDOW_HEIGHT = 500;
  int FPS = 30;
  BufferedImage bufferedImage;
  Graphics2D g2dFrame;
  //Graphics2D g2dBuffer;
  Graphx graphx;
  Tools tools;
  Automaton automaton;
  State stateSelected, stateOrigin, stateDestination;

  public void init()
  {
    setTitle("Automatos");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(null);
    setVisible(true);
    setLocation(0, 100);
    bufferedImage = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

    addMouseListener(this);
    addMouseMotionListener(this);

    g2dFrame = (Graphics2D) getGraphics();
    graphx = new Graphx(this);
    tools = new Tools(this);
    automaton = new Automaton();
    stateSelected = null;
    stateOrigin = null;
    stateDestination = null;

    /*Test*/
    State x = new State(5 * Style.W, 1 * Style.H, "X", false);
    State y = new State(8 * Style.W, 3 * Style.H, "Y", true);
    State z = new State(5 * Style.W, 5 * Style.H, "Z", false);
    automaton.addState(x);
    automaton.addState(y);
    automaton.addState(z);
    automaton.addTransition(new Transition(x, x, "a"));
    automaton.addTransition(new Transition(y, y, "b"));
    automaton.addTransition(new Transition(z, z, "b"));
    automaton.addTransition(new Transition(x, z, "g"));
    automaton.addTransition(new Transition(z, y, "a,g"));
    automaton.addTransition(new Transition(y, x, "a"));
  }

  public void display()
  { 
    graphx.grid();
    graphx.automaton();
    g2dFrame.drawImage(bufferedImage, 0, 0, this);
  }

  public void start()
  {
    init();

    while(true)
    {
      display();
      try
      {
        Thread.sleep(1000/FPS);
      }catch(Exception e) {
        System.out.println("Start error.");
      }
    }
  }

  public void mouseClicked(MouseEvent e)
  {
    if(tools.getTool() == 1) /*Select state*/
    {
      if(stateSelected == null)
      {
        stateSelected = tools.selectState(e);
      }else{
        stateSelected.setColor(Style.STATE_COLOR);
        stateSelected = null;
      }
    }

    if(tools.getTool() == 2) /*Add state*/
    {
      String label = JOptionPane.showInputDialog(this, "Rótulo:");

      while(label.equals(""))
      {
        JOptionPane.showMessageDialog(this, "O rótulo não pode ficar em branco!", "Obs!", JOptionPane.WARNING_MESSAGE);
        label = JOptionPane.showInputDialog(this, "Rótulo:");
      }

      if(label != null)
      {
        if(automaton.existsState(label))
          JOptionPane.showMessageDialog(this, "Já existe um estado com esse rótulo!", "Woops!", JOptionPane.ERROR_MESSAGE);
        else
          automaton.addState(new State(
            e.getX() - (Style.W / 2),
            e.getY() - (Style.H / 2),
            label,
            true
          ));
      }
    }

    if(tools.getTool() == 3) /*Remove state*/
    {
      automaton.removeState(tools.selectState(e));
    }

    if(tools.getTool() == 4) /*Add transition*/
    {
      State state = tools.selectState(e);

      if(state != null)
      {
        if(stateOrigin == null)
        {
          stateOrigin = state;
        }else{
          stateDestination = state;
          String label = JOptionPane.showInputDialog(this, "Rótulo:");

          while(label.equals(""))
          {
            JOptionPane.showMessageDialog(this, "O rótulo não pode ficar em branco!", "Obs!", JOptionPane.WARNING_MESSAGE);
            label = JOptionPane.showInputDialog(this, "Rótulo:");
          }

          if(label != null)
          {
            Transition transition = new Transition(stateOrigin, stateDestination, label);
            
            if(automaton.addTransition(transition))
              JOptionPane.showMessageDialog(this, "Essa trasição já existe!\nApenas o rótulo foi alterado.", "Obs!", JOptionPane.WARNING_MESSAGE);

            stateOrigin.setColor(Style.STATE_COLOR);
            stateDestination.setColor(Style.STATE_COLOR);
            stateOrigin = null;
            stateDestination = null;
          }else{
            stateOrigin.setColor(Style.STATE_COLOR);
            stateDestination.setColor(Style.STATE_COLOR);
            stateOrigin = null;
            stateDestination = null;
          }
        }
      }
    }

    if(tools.getTool() == 5) /*Remove transition*/
    {
    }
  }
 
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
  public void mousePressed(MouseEvent e){}
  public void mouseReleased(MouseEvent e){}
  public void mouseDragged(MouseEvent e){}

  public void mouseMoved(MouseEvent e)
  {
    if(tools.getTool() == 1)
    {
      if(stateSelected != null)
      {
        stateSelected.offset(
          e.getX() - (e.getX() % (Style.W / 2)), 
          e.getY() - (e.getY() % (Style.H / 2))
        );
      }
    }
  }

  public static void main(String[] args)
  {
    Main application = new Main();
    application.start();
  }
}