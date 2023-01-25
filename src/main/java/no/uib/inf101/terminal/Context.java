package no.uib.inf101.terminal;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Context {

  private final File home;
  private File cwd;

  public Context(File home) {
    try {
      this.home = home.getCanonicalFile();
      this.cwd = this.home;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean goToPath(String path) {
    File newDir = new File(cwd, path);
    if (!newDir.isDirectory()) {
      return false;
    }
    try {
      cwd = newDir.getCanonicalFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return true;
  }

  public File getCwd() {
    return this.cwd;
  }

  public void goToHome() {
    this.cwd = this.home;
  }

  public boolean isAtHome() {
    return Objects.equals(this.cwd, this.home);
  }
}
