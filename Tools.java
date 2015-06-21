import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Tools extends JFrame
{
  private int WINDOW_WIDTH = 800;
  private int WINDOW_HEIGHT = 65;
  private JButton toolNew;
  private JButton toolSave;
  private JButton toolOpen;
  private JButton toolSelect;
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
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    setLocation(0, 0);
    setLayout(new FlowLayout());
    
    this.tool = 1;
    this.main = main;

    toolNew = new JButton("Novo");
    toolSave = new JButton("Salvar");
    toolOpen = new JButton("Abrir");
    toolSelect = new JButton("Mover");
    toolAddState = new JButton("[+]Estado");
    toolRemoveState = new JButton("[-]Estado");
    toolAddTransition = new JButton("[+]Transição");
    toolRemoveTransition = new JButton("[-]Transição");

    defaults();
    toolSelect.setBackground(Style.selectedButtonColor);

    add(toolNew);
    add(toolSave);
    add(toolOpen);
    add(toolSelect);
    add(toolAddState);
    add(toolRemoveState);
    add(toolAddTransition);
    add(toolRemoveTransition);

    toolNew.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        //main.grafo = new Grafo();
      }
    });

    toolSave.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        // String fileName = JOptionPane.showInputDialog(main, "Nome do arquivo");
        // Save save = new Save();
        // save.save(main.grafo, fileName);
      }
    });

    toolOpen.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        // String fileName = JOptionPane.showInputDialog(main, "Nome do arquivo");
        // Open open = new Open();
        // main.grafo = open.open(fileName);
      }
    });

    toolSelect.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 1;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(Style.selectedButtonColor);
      }
    });

    toolAddState.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 2;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(Style.selectedButtonColor);
      }
    });

    toolRemoveState.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 3;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(Style.selectedButtonColor);
      }
    });

    toolAddTransition.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 4;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(Style.selectedButtonColor);
      }
    });

    toolRemoveTransition.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        tool = 5;
        JButton button = (JButton) e.getSource();
        defaults();
        button.setBackground(Style.selectedButtonColor);
      }
    });
  }

  public int getTool()
  {
    return this.tool;
  }

  public void defaults()
  {
    /*Button colors*/
    toolNew.setBackground(Style.defaultButtonColor);
    toolSave.setBackground(Style.defaultButtonColor);
    toolOpen.setBackground(Style.defaultButtonColor);
    toolSelect.setBackground(Style.defaultButtonColor);
    toolAddState.setBackground(Style.defaultButtonColor);
    toolRemoveState.setBackground(Style.defaultButtonColor);
    toolAddTransition.setBackground(Style.defaultButtonColor);
    toolRemoveTransition.setBackground(Style.defaultButtonColor);

    /*Main objects*/
    // if(this.main.stateA != null){this.main.stateA.color = new Color(255, 132, 0);}
    // if(this.main.stateB != null){this.main.stateB.color = new Color(255, 132, 0);}
    // if(this.main.stateSelected != null){this.main.stateSelected.color = new Color(255, 132, 0);}
    // this.main.stateA = null;
    // this.main.stateB = null;
    // this.main.stateSelected = null;
  }
}