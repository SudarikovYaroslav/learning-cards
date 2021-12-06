package model.facade;

import model.Model;

public abstract class Facade {

    Model model;

    public void setModel(Model model) {
        this.model = model;
    }
}
