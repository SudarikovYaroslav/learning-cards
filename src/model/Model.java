package model;

import basicClasses.Dictionary;
import basicClasses.User;

import java.io.*;
import java.util.ArrayList;


public class Model {

    private ArrayList<User> availableUsersList;
    private User currentUser;
    private ArrayList<Dictionary> userDictionaries;

    private final String allUsersListPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AllUsersList.txt";
    private final String usersFolderPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/Users";


    public Model() {
        availableUsersList = new ArrayList<>();
        loadUsers();
    }


    public ArrayList<User> getAvailableUsersList() {
        return availableUsersList;
    }


    public ArrayList<Dictionary> getUserDictionaries() {
        return userDictionaries;
    }


    private void loadUsers(){
        BufferedReader reader;
        String line = null;

        try {
            reader = new BufferedReader(new FileReader(allUsersListPath));

            while ((line = reader.readLine()) != null){
                availableUsersList.add(new User(line));
            }

            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void createUser(User user){
        // Create a user.name folder if it doesn't exist
        File newUser = new File(usersFolderPath + "/" + user.getName());
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(allUsersListPath, true));
            writer.write(newUser.getName() + "\n");
            writer.close();

            newUser.mkdir();
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    public void saveUsers(){

    }


    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    private void loadUserDictionaries(){

    }


}

// C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AllUsersList.txt