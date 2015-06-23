import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Tools extends JFrame
{
  private int WINDOW_WIDTH = 900;
  private int WINDOW_HEIGHT = 65;
  private JButton toolNew;
  private JButton toolSave;
  private JButton toolOpen;
  private JButton toolSelect;
  private JButton toolMove;
  private JButton toolAddState;
  private JButton toolRemoveState;
  private JButton toolAddTransition;
  private JButton toolRemoveTransition;
  private int tool;
  Main main;

  public Tools(Main main)
  {
    setTitle("Automatos - Ferramentas");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setResizable(false);
    getContentPane().setBackground(Style.BACKGROUND);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    setLocation(0, 0);
    
    this.tool = 0;
    this.main = main;

    toolNew = new JButton("Novo");
    toolSave = new JButton("Salvar");
    toolOpen = new JButton("Abrir");
    toolSelect = new JButton("Selecionar");
    toolMove = new JButton("Mover");
    toolAddState = new JButton("[+]Estado");
    toolRemoveState = new JButton("[-]Estado");
    toolAddTransition = new JButton("[+]Transição");
    toolRemoveTransition = new JButton("[-]Transição");

    changeStyle(toolSelect);

    add(toolNew);
    add(toolSave);
    add(toolOpen);
    add(toolSelect);
    add(toolMove);
    add(toolAddState);
    add(toolRemoveState);
    add(toolAddTransition);
    add(toolRemoveTransition);

    toolNew.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        main.automaton = new Automaton();
        main.fileName = null;
        main.setTitle("Automato - Novo automato não salvo");
      }
    });

    toolSave.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Save save = new Save();

        if(main.fileName == null)
        {
          String fileName = JOptionPane.showInputDialog(main, "Nome do arquivo:");

          while("".equals(fileName))
          {
            JOptionPane.showMessageDialog(main, "O nome não pode ficar em branco!", "Obs!", JOptionPane.WARNING_MESSAGE);
            fileName = JOptionPane.showInputDialog(main, "Nome do arquivo:");
          }

          if(fileName != null)
          {
            main.fileName = fileName;
            save.save(main.automaton, main.fileName);
            main.setTitle("Automato - " + fileName + ".aut");
          }
        }else{
          save.save(main.automaton, main.fileName);
        }
      }
    });

    toolOpen.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Open open = new Open();
        String fileName = JOptionPane.showInputDialog(main, "Nome do arquivo:");

        while("".equals(fileName))
        {
          JOptionPane.showMessageDialog(main, "O nome não pode ficar em branco!", "Obs!", JOptionPane.WARNING_MESSAGE);
          fileName = JOptionPane.showInputDialog(main, "Nome do arquivo:");
        }

        if(fileName != null)
        {
          Automaton a = open.open(fileName);
          if(a == null)
          {
            JOptionPane.showMessageDialog(main, "Esse arquivo não existe!", "Obs!", JOptionPane.WARNING_MESSAGE);
          }else{
            main.automaton = a;
            main.fileName = fileName;
            main.setTitle("Automato - " + fileName + ".aut");
          }
        }
      }
    });

    toolSelect.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 0;
        changeStyle((JButton) e.getSource());
      }
    });

    toolMove.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 1;
        changeStyle((JButton) e.getSource());
      }
    });

    toolAddState.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 2;
        changeStyle((JButton) e.getSource());
      }
    });

    toolRemoveState.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 3;
        changeStyle((JButton) e.getSource());
      }
    });

    toolAddTransition.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 4;
        changeStyle((JButton) e.getSource());
      }
    });

    toolRemoveTransition.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 5;
        changeStyle((JButton) e.getSource());
      }
    });

    setVisible(true);
  }

  public int getTool()
  {
    return this.tool;
  }

  public void changeStyle(JButton button)
  {
    /*Button colors*/
    toolNew.setBackground(Style.BUTTON_COLOR);
    toolSave.setBackground(Style.BUTTON_COLOR);
    toolOpen.setBackground(Style.BUTTON_COLOR);
    toolSelect.setBackground(Style.BUTTON_COLOR);
    toolMove.setBackground(Style.BUTTON_COLOR);
    toolAddState.setBackground(Style.BUTTON_COLOR);
    toolRemoveState.setBackground(Style.BUTTON_COLOR);
    toolAddTransition.setBackground(Style.BUTTON_COLOR);
    toolRemoveTransition.setBackground(Style.BUTTON_COLOR);

    button.setBackground(Style.BUTTON_SELECTED_COLOR);

    /*Main objects*/
    if(main.stateOrigin != null)
      main.stateOrigin.setColor(Style.STATE_COLOR);
    if(main.stateDestination != null)
      main.stateDestination.setColor(Style.STATE_COLOR);
    if(main.stateSelected != null)
      main.stateSelected.setColor(Style.STATE_COLOR);

    main.stateOrigin = null;
    main.stateDestination = null;
    main.stateSelected = null;
    main.property.stateLabel.setText("");
    main.property.isInitial.setSelected(false);
  }

  public State selectState(MouseEvent e)
  {
    Ellipse2D clickedPoint = new Ellipse2D.Float();
    Ellipse2D stateArea = new Ellipse2D.Float();
    clickedPoint.setFrame(e.getX(), e.getY(), 1, 1);

    State state = null;

    for(State s : main.automaton.getStates())
    {
      stateArea.setFrame(s.getX(), s.getY(), Style.W, Style.H);
      Area clickedArea = new Area(stateArea);
      clickedArea.intersect(new Area(clickedPoint));
      if(!clickedArea.isEmpty())
      {
        state = s;
      }
    }
    
    if(state != null)
      state.setColor(Style.STATE_SELECTED_COLOR);
    return state;
  }

  public boolean existsState(String label)
  {
    boolean exists = false;
    for(State state : main.automaton.getStates())
      if(state.getLabel().equals(label))
        exists = true;
    return exists;
  }
}