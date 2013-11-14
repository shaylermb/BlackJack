class Dealer extends Player{
	private Deck deck;
	public Dealer(){
		this.deck = new Deck();
		this.deck.shuffle();
	}
	public Card getCard(){
		return deck.returnTop();
	}
}