import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Property extends JFrame
{
  private int WINDOW_WIDTH = 300;
  private int WINDOW_HEIGHT = 150;
  Main main;

  private JLabel label;
  private JLabel labelInitial;
  
  JTextField stateLabel;
  JCheckBox isInitial;
  private JButton apply;
  private JButton setInitial;

  public Property(Main main)
  {
    setTitle("Propriedades");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    getContentPane().setBackground(Style.BACKGROUND);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.RIGHT));
    setLocation(910, 0);
    
    this.main = main;

    label = new JLabel("Rótulo:");
    stateLabel = new JTextField();
    stateLabel.setColumns(12);
    
    labelInitial = new JLabel("Final:");
    isInitial = new JCheckBox();
    isInitial.setBackground(Style.BUTTON_COLOR);

    apply = new JButton("Aplicar");
    setInitial = new JButton("Definir como inicial");

    apply.setBackground(Style.BUTTON_COLOR);
    setInitial.setBackground(Style.BUTTON_COLOR);

    add(label);
    add(stateLabel);
    
    add(labelInitial);
    add(isInitial);
    
    add(setInitial);
    add(apply);

    apply.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(main.stateSelected != null)
        {
          main.stateSelected.setLabel(stateLabel.getText());
          main.stateSelected.setInitial(!isInitial.isSelected());
        }
      }
    });
    
    setInitial.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(main.stateSelected != null)
        {
          main.automaton.setInitial(main.stateSelected);
        }
      }
    });

    setVisible(true);
  }
}