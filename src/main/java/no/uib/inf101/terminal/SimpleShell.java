package no.uib.inf101.terminal;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

// TODO: Let SimpleShell implement CommandLineInterface
public class SimpleShell {

  //////////////////////////////////////////////////////////////////////
  /// Instance variables ///////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////

  /** The prompt to show before each command */
  private final String prompt = "$ ";
  /** The context variable contains cwd and home directories etc. */
  private final Context context = new Context(
      new File(System.getProperty("user.dir"))
  );
  /** A list of historic commands and their outputs */
  private final ArrayList<String> history = new ArrayList<>();
  /** The command currently being typed */
  private String currentCommand = "";

  //////////////////////////////////////////////////////////////////////
  /// Public methods                                              //////
  /// (methods expected to be used by someone outside this class) //////
  //////////////////////////////////////////////////////////////////////

  public SimpleShell() {
    // TODO: Install core commands SimpleShell supports (pwd, ls, cd)
  }

  // TODO: rename method to fit new interface, annotate with @Override
  // Note: methods with @Override generally do not need javadoc comments,
  // since the javadoc comment is inherited. You should hence remove the
  // javadoc comment here unless there is something special about this
  // implementation. You should remove to-do comments when you are done.
  /**
   * Called when a key is pressed.
   *
   * @param key The key that was pressed
   */
  public void aKeyIsPressed(char key) {
    if (key == '\n') {
      this.processCurrentCommandLine();
    } else if (key >= ' ' && key <= '~') {
      this.currentCommand += key;
    }
  }

  // TODO: rename method to fit new interface, annotate with @Override
  /**
   * Get the text which the terminal should display
   *
   * @return the text
   */
  public String whatTheScreenLooksLike() {
    String s = "";
    for (String line : this.history) {
      s += line;
    }
    s += this.prompt;
    s += this.currentCommand;
    return s;
  }

  //////////////////////////////////////////////////////////////////////
  /// Private methods                                ///////////////////
  /// (helper methods used internally in this class) ///////////////////
  //////////////////////////////////////////////////////////////////////

  /**
   * Process the current command line. This entails splitting it into
   * a command name and arguments; executing the command; and adding
   * the result to the history.
   */
  private void processCurrentCommandLine() {
    String result = "";
    if (this.currentCommand.length() > 0) {
      String[] args = this.currentCommand.split(" ");
      String commandName = args[0];
      String[] commandArgs = new String[args.length - 1];
      System.arraycopy(args, 1, commandArgs, 0, commandArgs.length);
      result = this.executeCommand(commandName, commandArgs);
      if (result.length() > 0 && result.charAt(result.length() - 1) != '\n') {
        result += '\n';
      }
    }
    this.history.add(this.prompt + this.currentCommand + "\n" + result);
    this.currentCommand = "";
  }

  /**
   * Execute a command with the given name and arguments. The command
   * name could be "ls", "cd", "pwd", etc., and the arguments are the
   * arguments to the command. For example for the command "cd foo", the
   * command name is "cd" and the argument comes in the array ["foo"].
   *
   * @param commandName  The name of the command to execute
   * @param args  The arguments to the command
   * @return  The output of the command
   */
  private String executeCommand(String commandName, String[] args) {
    // TODO: Change sequence of if/else if below to a lookup in a map
    if (Objects.equals(commandName, "pwd")) {
      return this.doPwd(args);
    } else if (Objects.equals(commandName, "cd")) {
      return this.doCd(args);
    } else if (Objects.equals(commandName, "ls")) {
      return this.doLs(args);
    } else {
      return "Command not found: \"" + commandName + "\"";
    }
  }

  // TODO: remove this method and replace it with Command -type object
  private String doPwd(String[] args) {
    return this.context.getCwd().getAbsolutePath();
  }

  // TODO: remove this method and replace it with Command -type object
  private String doCd(String[] args) {
    if (args.length == 0) {
      this.context.goToHome();
      return "";
    } else if (args.length > 1) {
      return "cd: too many arguments";
    }
    String path = args[0];
    if (this.context.goToPath(path)) {
      return "";
    } else {
      return "cd: no such file or directory: " + path;
    }
  }

  // TODO: remove this method and replace it with Command -type object
  private String doLs(String[] args) {
    File cwd = this.context.getCwd();
    String s = "";
    for (File file : cwd.listFiles()) {
      s += file.getName();
      s += " ";
    }
    return s;
  }
}
