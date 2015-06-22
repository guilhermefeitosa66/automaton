import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Function extends JFrame
{
  private int WINDOW_WIDTH = 300;
  private int WINDOW_HEIGHT = 420;
  Main main;
  
  private JButton f1;
  private JButton f2;
  private JButton f3;
  private JButton f4;
  private JButton f5;

  public Function(Main main)
  {
    this.main = main;

    setTitle("Funções");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    getContentPane().setBackground(Style.BACKGROUND);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    GridLayout grid = new GridLayout(5,0);
    //setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
    setLayout(grid);
    setLocation(910, 180);


    f1 = new JButton("AFN >> AFD");
    f2 = new JButton("TRIM");
    f3 = new JButton("Composição paralela");
    f4 = new JButton("Produto");
    f5 = new JButton("Minimizar");

    f1.setBackground(Style.BUTTON_COLOR);
    f2.setBackground(Style.BUTTON_COLOR);
    f3.setBackground(Style.BUTTON_COLOR);
    f4.setBackground(Style.BUTTON_COLOR);
    f5.setBackground(Style.BUTTON_COLOR);

    add(f1);
    add(f2);
    add(f3);
    add(f4);
    add(f5);

    setVisible(true);
  }
}