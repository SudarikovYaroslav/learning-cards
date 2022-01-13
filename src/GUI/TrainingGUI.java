package GUI;
import basicClasses.Card;
import basicClasses.Dictionary;
import basicClasses.User;
import controller.Controller;
import view.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// Perhaps this class designed not correctly, because during training time it won't be ask controller to show
// next card for example. I decided to do most operations directly inside this class because it have all information
// to do this tasks by itself. But may be it's not rely good decision and I'll refactor it in the next program update.
public class TrainingGUI implements GUI {

    private View view;
    private Controller controller;
    private final BasicGUI basicGUI;
    private GUI previousGUI;
    private JTextArea frontArea;
    private JTextArea backArea;
    private Card currentCard;
    private int currentCardIndex;
    private Dictionary trainingDictionary;
    private ArrayList<Dictionary> dictionaries;
    private ArrayList<Card> missedCards;
    private ArrayList<Card> fails;
    private User user; // ?
    private int availableCards; // ?
    private int counter; // ?



    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, Dictionary dictionary, GUI previousGUI) {
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        trainingDictionary = copyFoTraining(dictionary);
        this.previousGUI = previousGUI;
        availableCards = dictionary.getSize(); // ?
    }


    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, ArrayList<Dictionary> dictionaries, GUI previousGUI) {
        // сделать из всех словарей один и присвоить его переменной trainingDictionary;
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        trainingDictionary = buildMultiDictionary(dictionaries);
        this.previousGUI = previousGUI;
        availableCards = getDictionariesSize(dictionaries);
    }


    // Remove This constructor to the Fails RepetitionGUI;
    // have to get fails repetition list from user
    public TrainingGUI(BasicGUI basicGUI, View view, Controller controller, User user){
        this.basicGUI = basicGUI;
        this.view = view;
        this.controller = controller;
        this.user = user; // perhaps instead user field it should init field ArrayList<Card> fails = user.getFailsList();
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
        JButton giveHintButton = new JButton("Give a hint");
        JButton failsListButton = new JButton("Add to the Fails repetition list");
        JButton finishTrainingButton = new JButton("Finish Training");
        JButton nexCardButton = new JButton("Next Card");
        JButton missCardButton = new JButton("Miss the Card");

        showAnswerButton.addActionListener(new ShowAnswerListener());
        giveHintButton.addActionListener(new GiveHintListener());
        failsListButton.addActionListener(new FailsListListener());
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

    
    public void trainingGO(){
        go();
        frontArea.setText(trainingDictionary.nextCard());
    }


    public void failsRepetitionGO(){ go(); }


    private int getDictionariesSize(ArrayList<Dictionary> dictionaries){
        int totalSize = 0;

        for (Dictionary dictionary : dictionaries){
            totalSize += dictionary.getSize();
        }

        return totalSize;
    }


    private Dictionary copyFoTraining(Dictionary dictionary){
       return new Dictionary(dictionary);
    }


    private Dictionary buildMultiDictionary(ArrayList<Dictionary> dictionaries){

        Dictionary multiDictionary = new Dictionary("TempMultiDictionary");
        for (Dictionary dic : dictionaries){
            for (Card card : dic.getCards()){
                multiDictionary.addCard(card);
            }
        }
        return multiDictionary;
    }

    private class ShowAnswerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            backArea.setText(trainingDictionary.giveAnswer());
        }
    }


    private class  GiveHintListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            backArea.setText(trainingDictionary.giveHint());
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
            frontArea.setText(trainingDictionary.nextCard());
            backArea.setText("");
        }
    }


    private class MissCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            trainingDictionary.missCard();
            frontArea.setText(trainingDictionary.nextCard());
            backArea.setText("");
        }
    }
}