import java.awt.*;
import java.applet.*;

public class TableApplet extends Applet {
	private Dealer dealer;
	private Human human;
	private ButtonPanel bp;
	public void init() {
		this.dealer = new Dealer();
		this.human = new Human();
		this.human.hit(dealer);
		this.human.hit(dealer);
		this.dealer.hit(dealer);
		this.dealer.hit(dealer);
		this.bp = new ButtonPanel(this.human, this.dealer, this);
		this.add(bp);

	}

	public void paint(Graphics g) {
		super.paint(g);
		this.human.returnHand().drawHand(g, 150);
		if (this.bp.stay() == 0) {
			this.dealer.returnHand().drawDealer(g, 350);
		}
		if (this.bp.stay() == 1) {
			this.dealer.returnHand().drawHand(g, 350);
		}
		

	}
	public void newgame(Human human, Dealer dealer){
		this.human = human;
		this.dealer = dealer;
	}

}