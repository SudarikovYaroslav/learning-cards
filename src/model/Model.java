package model;

import basicClasses.Dictionary;
import basicClasses.User;
import view.View;
import java.util.ArrayList;


public class Model {
    private View view;
    private User user;
    private ArrayList<Dictionary> userDictionaries;
    private DataManager dataManager;
    private ArrayList<User> usersList;
    private ArrayList<String> availableDictionaryTypes;

    public Model() {
        usersList = new ArrayList<>(); // line should be deleted after UserSelected GUI pass test successfully
        userDictionaries = new ArrayList<>(); // line should be deleted after UserProfile GUI pass test successfully
        /*dataManager = new DataManager();
        availableDictionaryTypes = dataManager.loadAvailableDictionaryTypes();
        usersList = dataManager.loadUsersList();
        userDictionaries = new ArrayList<>();*/
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void loadUserDictionaries(){
        userDictionaries = dataManager.loadDictionaries(user.getName());
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }

    public ArrayList<Dictionary> getUserDictionaries() {
        return userDictionaries;
    }
}
