package no.uib.inf101.terminal;

public class DummyShell implements CommandLineInterface {

  private String screenContent = "$ ";

  @Override
  public void keyPressed(char keyCode) {
    if (keyCode == '\n') {
      screenContent += "\n$ ";
    } else {
      screenContent += keyCode;
    }
  }

  @Override
  public String getScreenContent() {
    return screenContent;
  }
}
