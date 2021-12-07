package view;

import basicClasses.Dictionary;
import basicClasses.User;
import model.facade.ViewFacade;
import model.Model;
import view.GUI.BasicGUI;
import view.GUI.UserSelectGUI;


public class View {

    private ViewFacade facade;

    private User testUser = new User("Yar"); // line should be deleted after test
    private Dictionary currentDictionary = new Dictionary("English Cards"); // line only for tests. Should be deleted


    public void addModel(Model model){
        facade = new ViewFacade();
        facade.setModel(model);
    }

    public void go(){
        BasicGUI basicGUI = new BasicGUI(facade);
        UserSelectGUI userSelectGUI = new UserSelectGUI(basicGUI);
        userSelectGUI.go();
    }
}
