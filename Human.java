class Human extends Player {
	private Human human;
	public Human(){

	}
	public void hit(Dealer dealer){
		addCard(dealer.getCard());
	}
}