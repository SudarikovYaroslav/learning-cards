package view.GUI;

import basicClasses.Card;
import basicClasses.Dictionary;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrainingGUI {

    private Model model;
    private Dictionary currentDictionary;

    private JTextArea questionArea;
    private JTextArea answerArea; // ??
    private ArrayList<Card> cardList;
    private Card currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private boolean isShowAnswer;


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
        mainPanel.add(BorderLayout.NORTH, questionScroller);
        mainPanel.add(BorderLayout.SOUTH, answerScroller);

        JPanel buttonsPanel = new JPanel();
        

        JMenuBar menuBar = new JMenuBar();
        JMenu main_menu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        menuBar.add(main_menu);


        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640, 500);
        frame.setVisible(true);
    }

    private void loadFile(File file){
        // нужно создать ArrayList с карточками, считывая их из текстового файла,
        // вызванного из обработчика событий класса OpenMenuListener,
        // прочитать файл по одной строке за один раз
        // и вызвать метод makeCard() для создания новой карточки из строки
        cardList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null){
                makeCard(line);
            }
            reader.close();
        } catch (IOException e){
            System.out.println("couldn't read the card file");
            e.printStackTrace();
        }

        // Пришло времпоказать первую карточку
        showNextCard();
    }

    private void makeCard(String lineToParse){
        // Вызывается методом loadFile, берёт строку из текстового файла,
        // делит её на две части - переднюю и заднюю и создаёт новую карточку,
        // а затем добавляет её в ArrayList с помощью CardList
        String[] result = lineToParse.split("/");
        Card card = new Card(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }


    private void showNextCard(){
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        questionArea.setText(currentCard.getBack());
        nextButton.setText("Show answer");
        isShowAnswer = true;
    }

    class NextCardListener implements ActionListener{
        // Если это вопрос , показываем ответ, иначе показываем следующий вопрос
        // Установим флаг для того, что мы видим, - вопрос это или ответ
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isShowAnswer){
                // показываем ответ, так как вопрос уже был показан
                questionArea.setText(currentCard.getBack());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            } else {
                // Показываем следующий вопрос
                if (currentCardIndex < cardList.size()) {
                    showNextCard();
                } else {
                    // Больше карточек нет
                    questionArea.setText("That was last card");
                    nextButton.setEnabled(false);
                }
            }
        }
    }

    class OpenMenuListener implements ActionListener{
        // Вызываем диалоговое окно, ползволяющее пользователю выбирать,
        // какой набор карточек открыть
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

}
