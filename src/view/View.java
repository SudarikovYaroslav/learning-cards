package view;

import basicClasses.Dictionary;
import basicClasses.User;
import basicClasses.dictionaries.EnglishDictionary;
import controller.Controller;
import facade.Facade;
import view.GUI.*;


public class View {

    private Facade facade;
    private Controller controller; // ????

    private User testUser = new User("Yar"); // line should be deleted after test
    private Dictionary currentDictionary = new EnglishDictionary("English Cards"); // line only for tests. Should be deleted


    public View() {
        facade = new Facade();
    }

    public void setController(Controller controller){
        this.controller = controller;
    }


    public void go(){
       /*UserSelectGUI usGUI = new UserSelectGUI();
       usGUI.go();*/

        /*UserBuilderGUI userBuilderGUI = new UserBuilderGUI();
        userBuilderGUI.go();*/
    }
}
