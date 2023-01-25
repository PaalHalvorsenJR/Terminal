package no.uib.inf101.terminal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Terminal extends JPanel {
  private final CommandLineInterface terminal;

  public Terminal(CommandLineInterface cli) {
    this.terminal = cli;
    this.setPreferredSize(new Dimension(400, 300));
    this.setFocusable(true);
    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        Terminal.this.terminal.keyPressed(e.getKeyChar());
        Terminal.this.repaint();
      }
    });
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    String content = this.terminal.getScreenContent();

    // Calculate number of lines that can fit display
    int lineHeight = g.getFontMetrics().getHeight() + 5;
    int lines = this.getHeight() / lineHeight;

    // Draw the text on the screen, line by line
    g.setColor(Color.BLACK);
    g.setFont(new Font("Monospaced", Font.PLAIN, 12));
    int y = lineHeight;

    // Iterate through the last 'lines' lines of the content
    String[] linesOfContent = content.split("\n");
    for (int i = Math.max(0, linesOfContent.length - lines); i < linesOfContent.length; i++) {
      g.drawString(linesOfContent[i], 5, y);
      y += lineHeight;
    }
  }

  public void run() {
    JFrame frame = new JFrame("INF101 Terminal");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(this);
    frame.pack();
    frame.setVisible(true);
  }
}
