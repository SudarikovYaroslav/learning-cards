package view;

import basicClasses.Dictionary;
import basicClasses.User;
import basicClasses.dictionaries.EnglishDictionary;
import controller.Controller;
import facade.FacadeForView;
import model.Model;
import view.GUI.UserBuilderGUI;


public class View {

    private FacadeForView facade;
    private Controller controller; // ????

    private User testUser = new User("Yar"); // line should be deleted after test
    private Dictionary currentDictionary = new EnglishDictionary("English Cards"); // line only for tests. Should be deleted


    public void setController(Controller controller){
        this.controller = controller;
    }

    public void addModel(Model model){
        facade = new FacadeForView();
        facade.setModel(model);
    }

    public void go(){
       /*UserSelectGUI usGUI = new UserSelectGUI();
       usGUI.go();*/

        UserBuilderGUI userBuilderGUI = new UserBuilderGUI(facade);
        userBuilderGUI.go();
    }
}
