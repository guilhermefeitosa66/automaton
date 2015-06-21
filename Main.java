import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.JFrame;

public class Main extends JFrame implements MouseListener, MouseMotionListener
{
  BufferedImage bufferedImage;
  private int FPS = 30;
  private int WINDOW_WIDTH = 800;
  private int WINDOW_HEIGHT = 500;
  Graphics2D g2dFrame;
  Graphics2D g2dBuffer;

  Tools tools;

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

    g2dFrame = (Graphics2D) getGraphics();
    g2dBuffer = (Graphics2D) bufferedImage.getGraphics();
    g2dBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    addMouseListener(this);
    addMouseMotionListener(this);

    tools = new Tools(this);
  }

  public void display()
  { 
    /* Set background color */
    g2dBuffer.setColor(Style.BACKGROUND);
    g2dBuffer.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    
    /* Draw grid */
    g2dBuffer.setColor(Style.BORDER);
    g2dBuffer.setStroke(new BasicStroke(1));

    for(int i = 30; i < WINDOW_WIDTH; i += 30)
      g2dBuffer.drawLine(i, 0, i, WINDOW_WIDTH);

    for(int i = 30; i < WINDOW_HEIGHT; i += 30)
      g2dBuffer.drawLine(0, i, WINDOW_WIDTH, i);
    
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
    // if(tools.getTool() == 1)
    // {
    //   if(stateSelected != null)
    //   {
    //     stateSelected.offset(
    //       e.getX() - (e.getX() % 15), 
    //       e.getY() - (e.getY() % 15)
    //     );
    //   }
    // }
  }

  public static void main(String[] args)
  {
    Main application = new Main();
    application.start();
  }
}