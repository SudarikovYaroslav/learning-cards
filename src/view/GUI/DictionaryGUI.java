package view.GUI;

import basicClasses.Card;
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

        Card[] listEntries = dictionary.getCards().toArray(new Card[0]);
        JList<Card> cardsJList = new JList<Card>(listEntries);
        JScrollPane listScroller = new JScrollPane(cardsJList);
        listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cardsJList.setVisibleRowCount(10);

        JLabel label = new JLabel(dictionary.getName());

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(BorderLayout.SOUTH, cardsJList);
        mainPanel.add(BorderLayout.NORTH, label);

        JButton createCardButton = new JButton("Create new CArd");
        JButton editCardButton = new JButton("Edit Card");
        JButton deleteCardButton = new JButton("Delete Card");

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.cyan);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(createCardButton);
        buttonsPanel.add(editCardButton);
        buttonsPanel.add(deleteCardButton);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonsPanel);
        frame.setSize(580,50);
        frame.setVisible(true);
    }
}
