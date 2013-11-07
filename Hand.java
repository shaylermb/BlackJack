public class Hand {
	private Card[] hand;
	private int cards;
	public Hand(){
		this.hand = new Card[11];

	}
	public void add(Card a){
		this.hand[cards] = a;
		this.cards++;
	}

}