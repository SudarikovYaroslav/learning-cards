package model.basicClasses;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private String name;
    private final ArrayList<Dictionary> dictionaries;

    public User(String name) {
        this.name = name;
        dictionaries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Dictionary> getDictionaries() {
        return dictionaries;
    }

    public void addDictionary(Dictionary dictionary) {
        dictionaries.add(dictionary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(dictionaries, user.dictionaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dictionaries);
    }
}
