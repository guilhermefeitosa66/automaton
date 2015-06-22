import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Property extends JFrame
{
  private int WINDOW_WIDTH = 300;
  private int WINDOW_HEIGHT = 576;
  Main main;

  private JLabel label;
  private JTextField stateLabel;

  public Property(Main main)
  {
    setTitle("Propriedades");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    getContentPane().setBackground(Style.BACKGROUND);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    setLocation(810, 0);
    setVisible(true);
    
    this.main = main;

    label = new JLabel("Rótulo:");
    stateLabel = new JTextField();

    add(label);
    add(stateLabel);
  }
}