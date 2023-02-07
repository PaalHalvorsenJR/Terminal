package no.uib.inf101.terminal;

// UiB INF101 ShellLab - Main.java
// Dette er filen som inneholder main-metoden.

public class Main {

  public static void main(String[] args) {
    // Create a new shell
    CommandLineInterface shell = new SimpleShell();

    // Run the shell in the terminal GUI
    Terminal gui = new Terminal(shell);
    gui.run();
  }
}