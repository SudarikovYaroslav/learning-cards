package view.GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import model.facade.ViewFacade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrainingGUI extends BasicGUI{

    private Dictionary currentDictionary;
    private JTextArea questionArea;
    private JTextArea answerArea; // ??
    private Card currentCard;
    private int currentCardIndex; // ??
    private ArrayList<Card> missedCard;


    public TrainingGUI(ViewFacade facade, Dictionary currentDictionary) {
        super(facade);
        this.currentDictionary = currentDictionary;
    }


    public void go(){
        frame.setTitle("Training");

        questionArea = new JTextArea(5,15);
        questionArea.setFont(bigFont);
        questionArea.setLineWrap(true);
        questionArea.setEditable(false);

        answerArea = new JTextArea(5,15);
        answerArea.setFont(bigFont);
        answerArea.setLineWrap(true);
        answerArea.setEditable(false);

        JScrollPane questionScroller = new JScrollPane(questionArea);
        questionScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane answerScroller = new JScrollPane(answerArea);
        answerScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(BorderLayout.NORTH, questionScroller);
        mainPanel.add(BorderLayout.SOUTH, answerScroller);

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

        buttonsPanel.add(showAnswerButton);
        buttonsPanel.add(givHintButton);
        buttonsPanel.add(failsListButton);
        buttonsPanel.add(finishTrainingButton);
        buttonsPanel.add(nexCardButton);
        buttonsPanel.add(missCardButton);

        this.frameGo();
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