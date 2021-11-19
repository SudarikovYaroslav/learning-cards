package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {
    private final String usersPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/Users.txt";
    private final String dictionariesPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/Dictionaries.txt";


    public ArrayList<User> loadUsers(){
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = null;
        String s = null;

        try {
            reader = new BufferedReader(new FileReader(usersPath));

            while ((s = reader.readLine()) != null){
                users.add(new User(s));
            }

            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return users;
    }


    // in progress
    public ArrayList<Dictionary> loadDictionaries???TypesList????(){
        ArrayList<Dictionary> dictionaries = new ArrayList<>();
        DictionaryFactory dFactory = new DictionaryFactory();
        BufferedReader reader = null;
        String s = null;

        try {

        }catch (IOException e){
            e.printStackTrace();
        }
        return dictionaries;
    }



}
