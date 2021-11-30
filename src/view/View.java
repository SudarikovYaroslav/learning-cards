package view;

import basicClasses.Dictionary;
import basicClasses.User;
import basicClasses.dictionaries.EnglishDictionary;
import model.Model;
import view.GUI.DictionaryGUI;


public class View {

    private Model model;
    private User testUser = new User("Yar"); // line should be deleted after test
    private Dictionary currentDictionary = new EnglishDictionary("English Cards"); // line only for tests. Should be deleted

    public void setModel(Model model) {
        this.model = model;
    }

    public void go(){
        DictionaryGUI dictionaryGUI = new DictionaryGUI(model, currentDictionary);
        dictionaryGUI.go();
    }
}
