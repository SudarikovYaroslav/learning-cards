package model;

import basicClasses.Dictionary;
import basicClasses.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Model {

    private ArrayList<User> availableUsersList;
    private DataManager dataManager;
    private User currentUser;
    private ArrayList<Dictionary> userDictionaries;

    private final String allUsersPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AllUsersList.txt";


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
            reader = new BufferedReader(new FileReader(allUsersPath));

            while ((line = reader.readLine()) != null){
                availableUsersList.add(new User(line));
            }

            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    private void loadUserDictionaries(){

    }


}

// C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AllUsersList.txt