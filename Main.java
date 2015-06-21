import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.JFrame;

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
    State s1 = new State(3 * Style.W, 2 * Style.H, "AAA", true);
    State s2 = new State(7 * Style.W, 2 * Style.H, "BBB", false);
    automaton.addState(s1);
    automaton.addState(s2);
    automaton.addTransition(new Transition(s1, s2, "hello"));
    //automaton.addTransition(new Transition(s2, s1, "hello"));
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
    }

    if(tools.getTool() == 3) /*Remove state*/
    {
    }

    if(tools.getTool() == 4) /*Add transition*/
    {
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