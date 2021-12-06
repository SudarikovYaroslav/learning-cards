package model;

import basicClasses.Dictionary;
import basicClasses.User;
import java.util.ArrayList;


public class Model {

    private ArrayList<User> usersList;
    private DataManager dataManager;

    private User currentUser;
    private ArrayList<Dictionary> userDictionaries;


    public Model() {
        usersList = new ArrayList<>();
        dataManager = new DataManager();

        usersList = dataManager.loadUsersList();
    }


    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        dataManager.setUser(currentUser);
    }

    public void loadUserDictionaries(){
        userDictionaries = dataManager.loadDictionaries();
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public ArrayList<Dictionary> getUserDictionaries() {
        return userDictionaries;
    }
}
