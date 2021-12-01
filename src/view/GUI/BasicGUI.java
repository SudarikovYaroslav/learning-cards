package view.GUI;

import javax.swing.*;
import java.awt.*;

public abstract class BasicGUI {
    JFrame frame;
    JPanel mainPanel;
    JPanel buttonsPanel;
    JMenuBar menuBar;
    Font bigFont;

    public BasicGUI(){
        frame = new JFrame();
        bigFont = new Font("sanserif", Font.BOLD, 24);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.CYAN);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        mainMenu.add(exitMenuItem);
        menuBar.add(mainMenu);


    }

    void frameGo(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonsPanel);
        frame.setJMenuBar(menuBar);
        frame.setSize(580,500);
        frame.setVisible(true);
    }
}
