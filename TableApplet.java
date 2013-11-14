import java.awt.*;
import java.applet.*;

public class TableApplet extends Applet {
	private Dealer dealer;
	private Human human;
	public void init() {
		this.dealer = new Dealer();
		this.human = new Human();
		this.human.hit(dealer);
		this.human.hit(dealer);
		

	}

	public void paint(Graphics g) {
		this.human.returnHand().drawHand(g, 150);
	}

}