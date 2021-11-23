package basicClasses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public abstract class Dictionary implements Serializable {

    private String name;
    private LinkedList<Card> cards;

    public Dictionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Нужно сделать, чтобы ввод на стороны карточки происходил мз графического интерфейса
    public void createNewCard(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String front = null;
        String back = null;

        try{
            front = reader.readLine();
            back = reader.readLine();
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        Card newCard = new Card(front, back);
        cards.add(newCard);
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    public void nextCard(){} // need to be realised in this class

    protected abstract void giveAHint(); // Даёт подсказку
}
