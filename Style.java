import java.awt.*;

public class Style
{
  static int W = 50;
  static int H = 50;

  static Color STATE_COLOR = new Color(255, 132, 0);
  static Color STATE_SELECTED_COLOR = new Color(205, 82, 0);
  static Color STATE_BORDER = new Color(0, 0, 0);
  static Color TRANSITION_BORDER = new Color(100, 100, 100);

  static Color BUTTON_COLOR = new Color(255, 255, 255);
  static Color BUTTON_SELECTED_COLOR = new Color(200, 200, 200);
  
  static Color BACKGROUND = new Color(255, 255, 255);
  static Color BORDER = new Color(230, 230, 230);
  static Color STATE_TEXT = new Color(0, 0, 0);
  static Color TRANSITION_TEXT = new Color(0, 0, 0);
  static int FONT_SIZE = 14;
  static Font FONT = new Font("Arial", Font.BOLD, FONT_SIZE);

  static int TEXT_X = (W / 2);
  static int TEXT_Y = (H / 2) + (FONT_SIZE / 3);
}