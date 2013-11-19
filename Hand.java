import java.awt.Graphics;
import java.awt.Rectangle;
public class Hand {
	private Card[] hand;
	private int cards;
	private int sum;
	public Hand(){
		this.hand = new Card[11];

	}
	public void add(Card a){
		this.hand[cards] = a;
		this.cards++;

	}
	public int cards(){
		return this.cards;
	}
	public int getValue(){
		this.sum = 0;
		for (int i = 0; i<cards; i++) {
			this.sum += hand[i].getValue();
		}
		
		return this.sum;
	}
	public void drawHand(Graphics g, int y){
	  int x = 50;
	  for (int i = 0; i<this.cards; i++) {
	    this.hand[i].draw(g, hand[i].getTitle(), new Rectangle(x, y, 100, 150));
	    x += 25;
	  }
	  
	}

}