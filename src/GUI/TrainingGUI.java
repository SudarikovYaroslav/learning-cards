package GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import controller.Controller;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrainingGUI implements GUI {

    private View view;
    private Controller controller;
    private final BasicGUI basicGUI;
    private GUI previousGUI;
    private JTextArea frontArea;
    private JTextArea backArea;
    private Card currentCard;
    private int currentCardIndex;
    private Dictionary currentDictionary;
    private ArrayList<Dictionary> dictionaries;
    private ArrayList<Card> missedCards;
    private ArrayList<Card> fails;



    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, Dictionary dictionary, GUI previousGUI) {
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        currentDictionary = dictionary;
        this.previousGUI = previousGUI;
    }


    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, ArrayList<Dictionary> dictionaries) {
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        this.dictionaries = dictionaries;
    }


    public void go(){
        view.setCurrentGUI(basicGUI);
        basicGUI.clear();
        basicGUI.frame.setTitle("Training");

        frontArea = new JTextArea(5,15);
        frontArea.setFont(basicGUI.bigFont);
        frontArea.setLineWrap(true);
        frontArea.setEditable(false);

        backArea = new JTextArea(5,15);
        backArea.setFont(basicGUI.bigFont);
        backArea.setLineWrap(true);
        backArea.setEditable(false);

        JScrollPane questionScroller = new JScrollPane(frontArea);
        questionScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane answerScroller = new JScrollPane(backArea);
        answerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        basicGUI.mainPanel.add(BorderLayout.NORTH, questionScroller);
        basicGUI.mainPanel.add(BorderLayout.SOUTH, answerScroller);

        JButton showAnswerButton = new JButton("Show an answer");
        JButton givHintButton = new JButton("Give a hint");
        JButton failsListButton = new JButton("Add to the Fails repetition list");
        JButton finishTrainingButton = new JButton("Finish Training");
        JButton nexCardButton = new JButton("Next Card");
        JButton missCardButton = new JButton("Miss the Card");

        showAnswerButton.addActionListener(new ShowAnswerListener());
        givHintButton.addActionListener(new GiveHintListener());
        failsListButton.addActionListener(new FailsListListener());
        finishTrainingButton.addActionListener(new FinishTrainingListener());
        nexCardButton.addActionListener(new NextCardListener());
        missCardButton.addActionListener(new MissCardListener());

        basicGUI.buttonsPanel.add(showAnswerButton);
        basicGUI.buttonsPanel.add(givHintButton);
        basicGUI.buttonsPanel.add(failsListButton);
        basicGUI.buttonsPanel.add(finishTrainingButton);
        basicGUI.buttonsPanel.add(nexCardButton);
        basicGUI.buttonsPanel.add(missCardButton);

        basicGUI.go();
    }


    public void multiTrainingGO(){
        go();
    }


    public void simpleTrainingGO(){
        go();
    }


    private class ShowAnswerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class  GiveHintListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class FailsListListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class FinishTrainingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            previousGUI.go();
        }
    }

    private class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class MissCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}