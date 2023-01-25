package no.uib.inf101.terminal;

/**
 * A command line interface is a program with a text-based user interface.
 * The user can enter keys as input, and the program will respond by giving
 * text which can be displayed on a screen.
 */
public interface CommandLineInterface {

  /**
   * Called when a key is pressed.
   *
   * @param key The key that was pressed
   */
  void keyPressed(char key);

  /**
   * Get the text to display on the screen.
   * @return The text to display
   */
  String getScreenContent();
}
