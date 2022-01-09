package basicClasses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public class Dictionary implements Serializable {

    private String name;
    private LinkedList<Card> cards;


    public Dictionary(String name) {
        this.name = name;
        cards = new LinkedList<>();
    }


    public String getName() {
        return name;
    }


    public LinkedList<Card> getCards() {
        return cards;
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

    public void editCard(Card changedCard, String front, String back){
        for (Card card : cards){
            if (card.getFront().equals(changedCard.getFront())){
                card.setFront(front);
                card.setBack(back);
                return;
            }
        }
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    public void nextCard(){} // need to be realised in this class

    public String giveAHint(){
        return "";
    }; // Даёт подсказку
}
