package view.GUI;

import model.facade.ViewFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BasicGUI {
    ViewFacade facade;
    JFrame frame;
    JPanel mainPanel;
    JPanel buttonsPanel;
    JMenuBar menuBar;
    Font bigFont;


    public ViewFacade getFacade() {
        return facade;
    }

    public BasicGUI(ViewFacade facade){
        this.facade = facade;
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
        exitMenuItem.addActionListener(new menuExitItemListener());
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

    static class menuExitItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}
