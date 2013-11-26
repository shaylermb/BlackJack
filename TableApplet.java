import java.awt.*;
import java.applet.*;
//This was the easiest of the set, simple math. possibly could shorten and sharpen some of the math used to deal
//the cards correctly.
public class TableApplet extends Applet {
	private Dealer dealer;
	private Human human;
	private ButtonPanel bp;
	private JLabel wallet;
	private Score score;
	public void init() {
		this.dealer = new Dealer();
		this.human = new Human();
		Dimension size = new Dimension(2000,100);
		this.human.hit(dealer);
		this.human.hit(dealer);
		this.dealer.hit(dealer);
		this.dealer.hit(dealer);
		this.bp = new ButtonPanel(this.human, this.dealer, this);
		this.bp.setPreferredSize(size);
		this.add(bp);

	}

	public void paint(Graphics g) {
		super.paint(g);
		if (this.bp.stay() == 0) {
			this.dealer.returnHand().drawDealer(g, 350);
			this.human.returnHand().drawDealer(g, 150);
		}
		if (this.bp.stay() == 1) {
			this.human.returnHand().drawHand(g,150);
			this.dealer.returnHand().drawDealer(g, 350);
		}
		if (this.bp.stay() == 2) {
			this.dealer.returnHand().drawHand(g, 350);
			this.human.returnHand().drawHand(g,150);
		}
		

	}
	public void newgame(Human human, Dealer dealer){
		this.human = human;
		this.dealer = dealer;
	}

}