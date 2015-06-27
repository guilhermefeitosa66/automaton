import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.filechooser.*;

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

  JFileChooser chooser;
  FileNameExtensionFilter filter;

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


    f1 = new JButton("AFND => AFD");
    f2 = new JButton("TRIM");
    f3 = new JButton("Composição paralela");
    f4 = new JButton("Produto");
    f5 = new JButton("Minimizar");

    chooser = new JFileChooser();
    filter = new FileNameExtensionFilter("Automato *.aut", "aut", "AUT");
    chooser.setFileFilter(filter);
    chooser.setMultiSelectionEnabled(true);

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

    f3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Open open = new Open();
        String fileNameA = null;
        String fileNameB = null;
        int returnVal = chooser.showOpenDialog(main);

        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
          if(chooser.getSelectedFiles().length >= 2)
          {
            fileNameA = chooser.getSelectedFiles()[0].getPath();
            fileNameB = chooser.getSelectedFiles()[1].getPath();
            System.out.println("path: " + fileNameA);
            System.out.println("path: " + fileNameB);
          }else{
            JOptionPane.showMessageDialog(main, "Selecione pelo menos dois automatos!", "Obs!", JOptionPane.WARNING_MESSAGE);
          }
        }

        if(fileNameA != null && fileNameB != null)
        {
          Automaton a = open.open(fileNameA);
          Automaton b = open.open(fileNameB);
          if(a == null || b == null)
          {
            JOptionPane.showMessageDialog(main, "Erro ao abrir arquivo!", "Obs!", JOptionPane.WARNING_MESSAGE);
          }else{
            main.automaton = new Operation().parallelComposition(a, b);
            main.fileName = null;
            main.setTitle("Automato - Composição paralela");
          }
        }
      }
    });
  }
}