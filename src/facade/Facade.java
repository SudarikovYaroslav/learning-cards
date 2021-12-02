package facade;

import basicClasses.Dictionary;
import basicClasses.User;
import model.Model;

import java.util.ArrayList;

public class Facade {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public ArrayList<Dictionary> getUserDictionaries(){
        return model.getUserDictionaries();
    }

    public ArrayList<User> getUsersList(){
        return model.getUsersList();
    }
}
