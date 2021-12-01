package view.GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import model.Model;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TrainingGUI {

    private Model model;
    private Dictionary currentDictionary;

    private JTextArea questionArea;
    private JTextArea answerArea; // ??
    private Card currentCard;
    private int currentCardIndex; // ??
    private JFrame frame;
    private ArrayList<Card> missedCard;


    public TrainingGUI(Model model, Dictionary currentDictionary) {
        this.model = model;
        this.currentDictionary = currentDictionary;
    }


    public void go(){
        // Формируем и выводим на экран GUI
        frame = new JFrame("Training");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        questionArea = new JTextArea(5,20);
        questionArea.setFont(bigFont);
        questionArea.setLineWrap(true);
        questionArea.setEditable(false);

        answerArea = new JTextArea(5,20);
        answerArea.setFont(bigFont);
        answerArea.setLineWrap(true);
        answerArea.setEditable(false);

        JScrollPane questionScroller = new JScrollPane(questionArea);
        questionScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane answerScroller = new JScrollPane(answerArea);
        answerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(BorderLayout.NORTH, questionScroller);
        mainPanel.add(BorderLayout.SOUTH, answerScroller);

        JButton showAnswerButton = new JButton("Show an answer");
        JButton givHintButton = new JButton("Give a hint");
        JButton failsListButton = new JButton("Add to the Fails repetition list");
        JButton finishTrainingButton = new JButton("Finish Training");
        JButton nexCardButton = new JButton("Next Card");
        JButton missCardButton = new JButton("Miss the Card");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.cyan);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(showAnswerButton);
        buttonsPanel.add(givHintButton);
        buttonsPanel.add(failsListButton);
        buttonsPanel.add(finishTrainingButton);
        buttonsPanel.add(nexCardButton);
        buttonsPanel.add(missCardButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu main_menu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        main_menu.add(exit);
        menuBar.add(main_menu);


        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonsPanel);
        frame.setSize(580, 500);
        frame.setVisible(true);
    }


}
