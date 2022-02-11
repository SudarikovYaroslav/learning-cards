package GUI;

import controller.Controller;
import model.facade.ViewFacade;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicGUI implements GUI {
    ViewFacade viewFacade;
    Controller controller;
    JFrame frame;
    JPanel mainPanel;
    JPanel buttonsPanel;
    JMenuBar menuBar;
    Font bigFont;
    View view;

    public ViewFacade getViewFacade() {
        return viewFacade;
    }

    public BasicGUI(ViewFacade facade, View view) {
        this.viewFacade = facade;
        this.view = view;
        frame = new JFrame();
        bigFont = new Font("sanserif", Font.BOLD, 24);

        createPanels();

        menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new menuExitItemListener());
        mainMenu.add(exitMenuItem);
        menuBar.add(mainMenu);
    }

    /** Важно! при вызове go() в BasicGUI, не передавать его во view.currentGUI, это пораждает
        серьёзный сбой в работе exceptionsGUI при нажатии кнопки ОК*/
    public void go() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonsPanel);
        frame.setJMenuBar(menuBar);
        frame.setSize(580, 500);
        frame.setVisible(true);
    }

    static class menuExitItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public void clear() {
        frame.remove(mainPanel);
        frame.remove(buttonsPanel);
        frame.repaint();
        frame.revalidate();

        createPanels();
    }

    private void createPanels() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.CYAN);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.CYAN);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
    }
}
