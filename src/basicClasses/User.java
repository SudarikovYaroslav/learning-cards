package basicClasses;

import java.util.ArrayList;

public class User {

    private String name;
    private ArrayList<Dictionary> dictionaries;

    public User(String name) {
        this.name = name;
        dictionaries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void addDictionary(Dictionary dictionary) {
        dictionaries.add(dictionary);
    }

    public void setName(String name) {
        this.name = name;
    }
}
