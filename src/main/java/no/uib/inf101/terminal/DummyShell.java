package no.uib.inf101.terminal;

// UiB INF101 ShellLab - DynamicShell.java
//
// Denne koden er gitt som et eksempel på en klasse som implementerer
// CommanLineInterface. Du trenger ikke gjøre noen endringer i denne
// filen for denne lab'en (med mindre du selv ønsker).

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
