package no.uib.inf101.terminal;

public class Main {

  public static void main(String[] args) {
    // Create a new shell
    CommandLineInterface shell = new DummyShell();

    // Run the shell in the terminal GUI
    Terminal gui = new Terminal(shell);
    gui.run();
  }
}