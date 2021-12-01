package view.GUI;
import basicClasses.Dictionary;
import basicClasses.User;
import model.Model;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UserProfileGUI {
    private Model model;
    private User user;

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JList<Dictionary> dictionariesJList;
    private JMenuBar menuBar;
    private JLabel label;


    public UserProfileGUI(Model model, User currentUser) {
        this.model = model;
        user = currentUser;
    }


    public void go(){
        JFrame frame = new JFrame(user.getName() + " profile");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label = new JLabel("Your Dictionaries");

        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        Dictionary[] listEntry = model.getUserDictionaries().toArray(new Dictionary[0]);
        dictionariesJList = new JList<>(listEntry);
        dictionariesJList.setVisibleRowCount(10);
        dictionariesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dictionariesJList.addListSelectionListener(new JListListener());

        JScrollPane scroller = new JScrollPane();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel = new JPanel();
        mainPanel.setFont(bigFont);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(BorderLayout.NORTH, label);
        mainPanel.add(BorderLayout.CENTER, dictionariesJList);
        mainPanel.add(scroller);

        JButton changeUser = new JButton("Change User");
        JButton startTraining = new JButton("Start Training");
        JButton removeDictionary = new JButton("Create new Dictionary");
        JButton createNewDictionary = new JButton("Create new Dictionary");
        JButton startFailsRepetition = new JButton("Start fails repetition");

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.cyan);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(createNewDictionary);
        buttonPanel.add(removeDictionary);
        buttonPanel.add(startTraining);
        buttonPanel.add(startFailsRepetition);
        buttonPanel.add(changeUser);

        menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        mainMenu.add(exit);
        menuBar.add(mainMenu);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonPanel);
        frame.setJMenuBar(menuBar);

        frame.setSize(580, 500);
        frame.setVisible(true);
    }



    private class JListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {

        }
    }
}
