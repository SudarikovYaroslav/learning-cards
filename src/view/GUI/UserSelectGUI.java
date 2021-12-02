package view.GUI;
import basicClasses.User;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class UserSelectGUI extends BasicGUI{

    private JList<User> usersJList;
    private ArrayList<User> users;


    public void go(){
        frame.setTitle("Learning Cars");

        UsersSelectionListener usersSelectionListener = new UsersSelectionListener();
        users = facade.getUsersList();
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

        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main menu");
        JMenuItem exit = new JMenuItem("Exit");
        mainMenu.add(exit);
        menuBar.add(mainMenu);

        this.frameGo();
    }


    private class UsersSelectionListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            // should open a selected user profile
        }
    }
}