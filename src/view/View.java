package view;

import basicClasses.Dictionary;
import basicClasses.User;
import basicClasses.dictionaries.EnglishDictionary;
import controller.Controller;
import model.Model;
import view.GUI.*;


public class View {

    private Model model;
    private Controller controller;

    private User testUser = new User("Yar"); // line should be deleted after test
    private Dictionary currentDictionary = new EnglishDictionary("English Cards"); // line only for tests. Should be deleted


    public void setModel(Model model) {
        this.model = model;
    }


    public void setController(Controller controller){
        this.controller = controller;
    }


    public void go(){
       /*UserSelectGUI usGUI = new UserSelectGUI(model);
       usGUI.go();*/

        UserBuilderGUI userBuilderGUI = new UserBuilderGUI();
        userBuilderGUI.go();
    }
}
