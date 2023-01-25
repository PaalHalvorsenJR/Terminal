package no.uib.inf101.terminal;

import java.util.ArrayList;

public class EchoShell implements CommandLineInterface {
  ArrayList<String> outputLines = new ArrayList<>();
  String currentLine = "";

  @Override
  public void keyPressed(char keyCode) {
    if (keyCode == '\n') {
      outputLines.add("$ " + currentLine);
      outputLines.add("Oh, an echo! listen: " + currentLine);
      currentLine = "";
    } else {
      currentLine += keyCode;
    }
  }

  @Override
  public String getScreenContent() {
    String result = "";
    for (String line : outputLines) {
      result += line + "\n";
    }
    return result + "$ " + currentLine;
  }
}
