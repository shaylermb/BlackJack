public class Player{
	public Hand hand;
	public Player(){
		this.hand = new Hand();
		

	}
	public void addCard(Card a){
		this.hand.add(a);
	}
	public int score(){
		return hand.getValue();
	}
	public Hand returnHand(){
		return this.hand;
	}
}