package model;
import basicClasses.Dictionary;
import basicClasses.User;
import java.io.*;
import java.util.ArrayList;

public class DataManager {
    private User user;
    private final String usersListPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/UsersList.txt";
    private final String availableDictionariesListPath = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/AvailableDictionaries.txt";
    private final String userData = "C:/Users/Ярослав/Desktop/Repo/LearningCards/data/Users/" + user.getName();


    public void setUser(User user) {
        this.user = user;
    }


    public ArrayList<User> loadUsersList() {
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = null;
        String s = null;

        try {
            reader = new BufferedReader(new FileReader(usersListPath));

            while ((s = reader.readLine()) != null) {
                users.add(new User(s));
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }


    public ArrayList<String> loadAvailableDictionaryTypes() {
        ArrayList<String> availableDictionaries = new ArrayList<>();
        BufferedReader reader = null;
        String s = null;

        try {
            reader = new BufferedReader(new FileReader(availableDictionariesListPath));

            while ((s = reader.readLine()) != null) {
                availableDictionaries.add(s);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availableDictionaries;
    }


    public void saveDictionary(Dictionary dictionary) {

        if (dictionary != null) {
            throw new NullPointerException("didn't point which dictionary should be saved");
        }

        File userFolder = new File(userData);
        File[] dictionaries = userFolder.listFiles();
        boolean exist = false;


        assert dictionaries != null; // Don't really understand what is this command do. Add by IJ advise

        if (dictionaries.length == 0){
            serializeDictionary(dictionary, userData + "/" + dictionary.getName());
        } else {

            for (int i = 0; i < dictionaries.length; i++) {
                if (dictionaries[i].getName().equals(dictionary.getName())) {
                    exist = true;
                    serializeDictionary(dictionary, dictionaries[i].getAbsolutePath());
                    return;
                }
            }

            if (exist){
                serializeDictionary(dictionary, userData + "/" + dictionary.getName());
            }
        }

    }


    private void serializeDictionary(Dictionary dictionary, String path) {
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(new File(path));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(dictionary);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    ArrayList<Dictionary> loadDictionaries(String userName){
        ArrayList<Dictionary> dictionaries = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;

        try{
            fis = new FileInputStream(userData + "/" + userName);
            ois = new ObjectInputStream(fis);

            while (ois.available() > 0){
                Object obj = ois.readObject();

                if (obj instanceof Dictionary){
                    Dictionary dictionary = (Dictionary) obj;
                    dictionaries.add(dictionary);
                }
            }

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return dictionaries;
    }
}

