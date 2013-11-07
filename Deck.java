import java.util.Random;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Deck {

    private Card[] deck;  
    private int cardsUsed; 
    private Hand hand;
    public Deck() {
      deck = new Card[52];
      int cardCt = 0;
      for ( int suit = 0; suit <= 3; suit++ ) {
        for ( int value = 1; value <= 14; value++ ) {
            deck[cardCt] = new Card(value,suit);
            cardCt++;
        }
      }
      cardsUsed = 0;
      this.hand = new Hand();
    }
    
    public void shuffle() {
      Random r = new Random();
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
      cardsUsed++;
      return deck[cardsUsed - 1];
    }
    public void hit(){ 
      hand.add(deck[0]);
      dealCard();
    }
    public void drawDeck(Graphics g, int y){
      int x = 50;
      for (int i = 0; i<deck.length; i++) {
        this.deck[i].draw(g, card.getTitle, new Rectangle(x, y, 200, 300));
      }
      x += 25;
    }


} 