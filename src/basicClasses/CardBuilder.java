package basicClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CardBuilder {

    private JTextArea frontSide;
    private JTextArea backSide;
    private ArrayList<Card> cardList;
    private JFrame frame;


    public void go(){
        // формируем и выводим на экран GUI включая создание и регистрацию
        // слушателей для событий

        frame = new JFrame("Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);


        frontSide = new JTextArea(6,20);
        frontSide.setLineWrap(true);
        frontSide.setWrapStyleWord(true);
        frontSide.setFont(bigFont);
        JScrollPane frontScroller = new JScrollPane(frontSide);
        frontScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frontScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        backSide = new JTextArea(6 ,20);
        backSide.setLineWrap(true);
        backSide.setWrapStyleWord(true);
        backSide.setFont(bigFont);
        JScrollPane backScroller = new JScrollPane(backSide);
        backScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        backScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        JButton nextButton = new JButton("Next Card");

        cardList = new ArrayList<>();

        JLabel frontLabel = new JLabel("Question: ");
        JLabel backLabel = new JLabel("Answer: ");

        mainPanel.add(frontLabel);
        mainPanel.add(frontScroller);
        mainPanel.add(backLabel);
        mainPanel.add(backScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        mainPanel.add(menuBar);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500,600);
        frame.setVisible(true);
    }


    private class NextCardListener implements ActionListener{
        // Срабатывает при нажатии пользователем кнопки "Next Card"
        @Override
        public void actionPerformed(ActionEvent e) {
            //добавляем текущую карточку в список и очищаем текстовые области
            Card card = new Card(frontSide.getText(), backSide.getText()); // текст берём из JTextArea
            cardList.add(card);
            clearCard();
        }
    }


    private class SaveMenuListener implements ActionListener{
        // Запускается при выборе команды save из меню  File. Означает, что пользователь хочет сохранить все карточки
        // в текущем списке в виде набора
        @Override
        public void actionPerformed(ActionEvent e) {
            Card card = new Card(frontSide.getText(), backSide.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }


    private class NewMenuListener implements ActionListener{
        // Запускается при выборе команды New из меню File означает, что пользователь хочет создать новый набор
        @Override
        public void actionPerformed(ActionEvent e) {
            //Очищаем список карточек и текстовые области
            cardList.clear();
            clearCard();
        }
    }


    private void clearCard(){
        frontSide.setText("");
        backSide.setText("");
        frontSide.requestFocus();
    }


    private void saveFile(File file){
        // Вызывается классом SaveMenuListener. Непосредственно записывает данные в файл
        // Проходим по списку карточек и записываем каждый элемент в текстовый файл,
        // который потом можно будет прчитать
        //(т.е. с чистыми разделителями между частями)
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Card card : cardList){
                System.out.println("Test string: " + card.getFront() + " : " + card.getBack());
                writer.write(card.getFront() + "/");
                writer.write(card.getBack() + "\n");
            }
            writer.close();
            System.out.println("*********** cards have been saved ***********");
        } catch (IOException e){
            System.out.println("Couldn't wright the cardList out");
            e.printStackTrace();
        }
    }
}
