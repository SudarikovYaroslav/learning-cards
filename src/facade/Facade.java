package facade;

import basicClasses.Dictionary;
import basicClasses.User;
import model.Model;

import java.util.ArrayList;

public abstract class Facade {

    Model model;

    public void setModel(Model model) {
        this.model = model;
    }
}
