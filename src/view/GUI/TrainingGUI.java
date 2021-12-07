package view.GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrainingGUI {

    private final BasicGUI basicGUI;
    private Dictionary currentDictionary;
    private JTextArea questionArea;
    private JTextArea answerArea; // ??
    private Card currentCard;
    private int currentCardIndex; // ??
    private ArrayList<Card> missedCard;


    public TrainingGUI(BasicGUI basicGUI, Dictionary currentDictionary) {
        this.basicGUI = basicGUI;
        this.currentDictionary = currentDictionary;
    }


    public void go(){
        basicGUI.frame.setTitle("Training");

        questionArea = new JTextArea(5,15);
        questionArea.setFont(basicGUI.bigFont);
        questionArea.setLineWrap(true);
        questionArea.setEditable(false);

        answerArea = new JTextArea(5,15);
        answerArea.setFont(basicGUI.bigFont);
        answerArea.setLineWrap(true);
        answerArea.setEditable(false);

        JScrollPane questionScroller = new JScrollPane(questionArea);
        questionScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane answerScroller = new JScrollPane(answerArea);
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

        basicGUI.frameGo();
    }


    private static class ShowAnswerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class  GiveHintListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class FailsListListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class FinishTrainingListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class MissCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}