package facade;

import basicClasses.Dictionary;
import basicClasses.User;

import java.util.ArrayList;

public class FacadeForView extends Facade{

    public ArrayList<Dictionary> getUserDictionaries(){
        return model.getUserDictionaries();
    }

    public ArrayList<User> getUsersList(){
        return model.getUsersList();
    }

}
