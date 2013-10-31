import java.util.Random;
public class Deck {

    private Card[] deck;  
    private int cardsUsed; 
    private Random r = new Random;
    
    public Deck() {
      deck = new Card[52];
      int cardCt = 0;
      for ( int suit = 0; suit <= 3; suit++ ) {
        for ( int value = 1; value <= 13; value++ ) {
           deck[cardCt] = new Card(value,suit);
            cardCt++;
        }
      }
      cardsUsed = 0;
      Hand hand = new Hand();
    }
    
    public void shuffle() {
      for (int i = 52; i>=1; i--) {
        Card a = deck[i];
        int rand = r.nextInt(i);
        deck[i] = deck[rand];
        deck[rand] = a;
      }
      cardsUsed = 0;
    }
    
    public int cardsLeft() {
      return 52 - cardsUsed;
    }
    
    public Card dealCard() {
      if (cardsUsed == 52)
          shuffle();
      cardsUsed++;
      return deck[cardsUsed - 1];
    }
    public void hit(){
      hand.add(deck[i]);
      hand.add
    }

} // end class Deck