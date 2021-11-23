package basicClasses.GUI;

import basicClasses.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class Training {

    private JTextArea display;
    private JTextArea answer;
    private ArrayList<Card> cardList;
    private Card currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;

    public void go(){
        // Формируем и выводим на экран GUI
        frame = new JFrame("Training");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(10,20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setEditable(false);

        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        nextButton = new JButton("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
    }

    private void loadFile(File file){
        // нужно создать ArrayList с карточками, считывая их из текстового файла,
        // вызванного из обработчика событий класса OpenMenuListener,
        // прочитать файл по одной строке за один раз
        // и вызвать метод makeCard() для создания новой карточки из строки
    }

    private void makeCard(String lineToParse){
        // Вызывается методом loadFile, берёт строку из текстового файла,
        // делит её на две части - переднюю и заднюю и создаёт новую карточку,
        // а затем добавляет её в ArrayList с помощью CardList
    }


    class NextCardListener implements ActionListener{
        // Если это вопрос , показываем ответ, иначе показываем следующий вопрос
        // Установим флаг для того, что мы видим, - вопрос это или ответ
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class OpenMenuListener implements ActionListener{
        // Вызываем диалоговое окно, ползволяющее пользователю выбирать,
        // какой набор карточек открыть
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
