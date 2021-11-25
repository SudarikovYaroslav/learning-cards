package view;

import model.Model;
import view.GUI.UserSelectGUI;

public class View {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void go(){
        UserSelectGUI userSelect = new UserSelectGUI(model);
        userSelect.go();
    }
}
