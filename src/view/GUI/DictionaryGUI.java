package view.GUI;

import basicClasses.Dictionary;
import model.Model;

import javax.swing.*;
import java.awt.*;

public class DictionaryGUI {

    Model model;
    Dictionary dictionary;

    JFrame frame;
    JPanel mainPanel;
    JPanel buttonsPanel;
    JMenuBar menuBar;

    public DictionaryGUI(Model model, Dictionary dictionary) {
        this.model = model;
        this.dictionary = dictionary;
    }

    public void go(){
        frame = new JFrame("Dictionary " + dictionary.getName());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        mainMenu.add(exit);
        menuBar.add(mainMenu);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JButton createCardButton = new JButton("Create new CArd");
        JButton editCardButton = new JButton("Edit Card");
        JButton deleteCardButton = new JButton("Delete Card");

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.cyan);
        buttonsPanel.add(createCardButton);
        buttonsPanel.add(editCardButton);
        buttonsPanel.add(deleteCardButton);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonsPanel);
        frame.setSize(680,50);
        frame.setVisible(true);
    }
}
