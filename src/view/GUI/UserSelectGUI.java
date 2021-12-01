package view.GUI;
import basicClasses.User;
import model.Model;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class UserSelectGUI {

    Model model;
    JFrame frame;
    JPanel mainPanel;
    JPanel buttonsPanel;
    JList<User> usersJList; // deal with the parameter
    ArrayList<User> users;


    public UserSelectGUI(Model model) {
        this.model = model;
    }

    public void go(){
        frame = new JFrame("Learning Cars");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        UsersSelectionListener usersSelectionListener = new UsersSelectionListener();
        users = model.getUsersList();
        User[] listEntry = users.toArray(new User[0]);
        usersJList = new JList<>(listEntry);
        usersJList.setVisibleRowCount(10);
        usersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        usersJList.addListSelectionListener(usersSelectionListener);

        JLabel label = new JLabel("Chose User ore create a new one");

        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setFont(bigFont);
        JScrollPane scroller = new JScrollPane();
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scroller);
        mainPanel.add(BorderLayout.CENTER,usersJList);
        mainPanel.add(BorderLayout.NORTH, label);

        JButton chooseUserButton = new JButton("Choose User");
        JButton addNewUserButton = new JButton("Add new User");
        JButton deleteUserButton = new JButton("Delete User");

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.CYAN);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(chooseUserButton);
        buttonsPanel.add(addNewUserButton);
        buttonsPanel.add(deleteUserButton);

        JMenuBar menuBar = new JMenuBar();// Deal with menu bar and a the label location;
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        mainMenu.add(exit);
        menuBar.add(mainMenu);



        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.EAST, buttonsPanel);
        frame.setJMenuBar(menuBar);
        frame.setSize(580,500);
        frame.setVisible(true);
    }


    private class UsersSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // should open a selected user profile
        }
    }
}

