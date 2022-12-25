package GUI;

import controller.Controller;
import model.basicClasses.Card;
import model.basicClasses.Dictionary;
import model.basicClasses.User;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Perhaps this class designed not correctly, because during training time it won't be ask controller to show
 * next card for example. I decided to do most operations directly inside this class because it has all information
 * to do this tasks by itself. But maybe it's not perfect decision, and I'll refactor it in one of the next program
 * updates.
 */
public class TrainingGUI implements GUI {
    private final User user;
    private final View view;
    private final Controller controller;
    private final BasicGUI basicGUI;
    private final GUI previousGUI;
    private final Dictionary trainingDictionary;
    private final boolean isFailsListUsed;
    private JTextArea frontArea;
    private JTextArea backArea;

    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, Dictionary dictionary, GUI previousGUI,
                       User user) {
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        trainingDictionary = copyFoTraining(dictionary);
        this.previousGUI = previousGUI;
        isFailsListUsed = false;
        this.user = user;
    }

    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, ArrayList<Dictionary> dictionaries,
                       GUI previousGUI, User user) {
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        trainingDictionary = buildMultiDictionary(dictionaries);
        this.previousGUI = previousGUI;
        isFailsListUsed = false;
        this.user = user;
    }

    /**
     * This constructor used only for init FailsTraining
     */
    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, GUI previousGUI, User user) {
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        this.previousGUI = previousGUI;
        this.user = user;
        isFailsListUsed = true;
        trainingDictionary = loadFailsDictionary(user);
    }

    public void go() {
        view.setCurrentGUI(this);
        basicGUI.clear();
        basicGUI.frame.setTitle("Training");

        frontArea = new JTextArea(5, 15);
        frontArea.setFont(basicGUI.bigFont);
        frontArea.setLineWrap(true);
        frontArea.setEditable(false);

        backArea = new JTextArea(5, 15);
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
        JButton giveHintButton = new JButton("Give a hint");
        JButton failsListButton = null;
        if (isFailsListUsed) {
            failsListButton = new JButton("Delete from Fails List");
        } else {
            failsListButton = new JButton("Add to the Fails repetition list");
        }
        JButton finishTrainingButton = new JButton("Finish Training");
        JButton nexCardButton = new JButton("Next Card");
        JButton missCardButton = new JButton("Miss the Card");

        showAnswerButton.addActionListener(new ShowAnswerListener());
        giveHintButton.addActionListener(new GiveHintListener());
        if (isFailsListUsed) {
            failsListButton.addActionListener(new FailsListDeleterListener());
        } else {
            failsListButton.addActionListener(new FailsListAdderListener());
        }
        finishTrainingButton.addActionListener(new FinishTrainingListener());
        nexCardButton.addActionListener(new NextCardListener());
        missCardButton.addActionListener(new MissCardListener());

        basicGUI.buttonsPanel.add(nexCardButton);
        basicGUI.buttonsPanel.add(missCardButton);
        basicGUI.buttonsPanel.add(giveHintButton);
        basicGUI.buttonsPanel.add(showAnswerButton);
        basicGUI.buttonsPanel.add(failsListButton);
        basicGUI.buttonsPanel.add(finishTrainingButton);

        basicGUI.go();
    }

    public void trainingGO() {
        go();
        frontArea.setText(trainingDictionary.nextCard());
    }

    /**
     * interaction with fails List which should be loaded by using controller
     * and placed in the trainingDictionary field
     */
    public void startFailsRepetition() {
        go();
        frontArea.setText(trainingDictionary.nextCard());
    }

    private int getDictionariesSize(ArrayList<Dictionary> dictionaries) {
        int totalSize = 0;

        for (Dictionary dictionary : dictionaries) {
            totalSize += dictionary.getSize();
        }

        return totalSize;
    }

    private Dictionary copyFoTraining(Dictionary dictionary) {
        return new Dictionary(dictionary);
    }

    private Dictionary buildMultiDictionary(ArrayList<Dictionary> dictionaries) {

        Dictionary multiDictionary = new Dictionary("TempMultiDictionary");
        for (Dictionary dic : dictionaries) {
            for (Card card : dic.getCards()) {
                multiDictionary.addCard(card);
            }
        }
        return multiDictionary;
    }

    private Dictionary loadFailsDictionary(User user) {
        return controller.loadFailsDictionary(user);
    }

    private class ShowAnswerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            backArea.setText(trainingDictionary.giveAnswer());
        }
    }

    private class GiveHintListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            backArea.setText(trainingDictionary.giveHint());
        }
    }

    private class FailsListAdderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = controller.addToTheFailsList(user, trainingDictionary.getCurrentCard());
            view.printMessage(message);
        }
    }

    private class FailsListDeleterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = controller.deleteFromFailsList(user, trainingDictionary.getCurrentCard());
            view.printMessage(message);
        }
    }

    private class FinishTrainingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            previousGUI.go();
        }
    }

    private class NextCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frontArea.setText(trainingDictionary.nextCard());
            backArea.setText("");
        }
    }

    private class MissCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            trainingDictionary.missCard();
            frontArea.setText(trainingDictionary.nextCard());
            backArea.setText("");
        }
    }
}