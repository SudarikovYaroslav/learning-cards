import java.util.HashMap;
import java.util.Iterator;

public class Dictionary {

    private String name;
    private String front;
    private String back;

    private HashMap<String, String> frontSideSelection;
    private HashMap<String, String> backSideSelection;

    public Dictionary(String name, String front, String back) {
        this.name = name;
        this.front = front;
        this.back = back;

        frontSideSelection = new HashMap<>();
        backSideSelection = new HashMap<>();
    }


    public String getName() {
        return name;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public HashMap<String, String> getFrontSideSelection() {
        return frontSideSelection;
    }

    public HashMap<String, String> getBackSideSelection() {
        return backSideSelection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setFrontSideSelection(HashMap<String, String> frontSideSelection) {
        this.frontSideSelection = frontSideSelection;
    }

    public void setBackSideSelection(HashMap<String, String> backSideSelection) {
        this.backSideSelection = backSideSelection;
    }

    public String printDictionaryContent(){
         
    }
}
