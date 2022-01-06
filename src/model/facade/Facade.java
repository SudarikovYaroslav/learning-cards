package model.facade;

import basicClasses.Dictionary;
import basicClasses.User;
import model.Model;

public abstract class Facade {

    Model model;

    public void setModel(Model model) {
        this.model = model;
    }

}
