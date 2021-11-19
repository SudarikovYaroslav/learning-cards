package model;

import view.View;

import java.util.ArrayList;

public class Model {
    private View view;
    private ArrayList<User> usersList;
    private ArrayList<Dictionary> dictionariesList;

    public Model(View view) {
        this.view = view;
        usersList = new ArrayList<>();
        dictionariesList = new ArrayList<>();
        DataLoader loader = new DataLoader();
        usersList = loader.loadUsers();
        dictionariesList = loader.loadDictionaries();
    }

}
