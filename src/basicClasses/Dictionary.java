package basicClasses;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Dictionary implements Serializable {

    private String name;
    private LinkedList<Card> cards;
    private int counter;
    private int hintCounter;
    private ArrayList<Card> missedCards;


    public Dictionary(String name) {
        this.name = name;
        cards = new LinkedList<>();
        missedCards = new ArrayList<>();
        counter = 0;
        hintCounter = 0;
    }


    public String getName() {
        return name;
    }


    public LinkedList<Card> getCards() {
        return cards;
    }

    // perhaps I should delete this method because it won't be used
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


    public String nextCard(){
        hintCounter = 0;
        String front = "";

        if (counter < cards.size()){
            front = cards.get(counter).getFront();
            counter++;
        } else {
            cards.clear();
            cards.addAll(missedCards);
            missedCards.clear();
            counter = 0;
        }

        if (front.length() == 0){
            front = "All cards run out";
        }

        return front;
    }


    public String giveAnswer(){
        return cards.get(counter - 1).getBack();
    }


    public void missCard(){
        missedCards.add(cards.get(counter));
        nextCard();
    }


    public String giveHint(){
        String hint = "";
        String[] answer = giveAnswer().split(" ");

        if (answer.length > 1 && hintCounter < answer.length){
            hint = answer[hintCounter];
            hintCounter++;
        } else {
            int wordSize = answer[0].length();

            if (wordSize == 1){
                giveAnswer();
            } else if (wordSize < 6){
                hint = answer[0].substring(0, hintCounter);
                hintCounter++;
            } else if (wordSize > 6){
                hintCounter = 3;
                hint = answer[0].substring(0, hintCounter);
                hintCounter += 3;

                while (hintCounter >= answer.length){
                    hintCounter--;
                }
            }
        }
        return hint;
    }


    public int getSize(){
        return cards.size();
    }
}
