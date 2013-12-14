import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
//What took me the longest was this button panel. I stupidly put most of the math in here, and ran out of time
//when i wanted to change it. If i was to go back i would shorten this and compress much of the words into
//bigger methods.
//
public class ButtonPanel extends JPanel implements ActionListener{
	private JLabel label, bet, wallet;
	private JButton hit, stay, game, betb, dbl, start;
	private int value = 0;
	private Human human;
	private Dealer dealer;
	private TableApplet table;
	private int x, betv, walletv;

	public ButtonPanel(Human human, Dealer dealer, TableApplet table) {
		super();
		this.human = human;
		this.table = table;
		this.dealer = dealer;
		this.value = human.score();
		this.walletv = 100;

		label = new JLabel("");
		label.setFont(new Font("sansserif", Font.BOLD, 16));
		this.add(label);

		String title = "Hit";
		hit = new JButton(title);
		hit.setActionCommand(title);
		hit.addActionListener(this);
		this.add(hit);

		title = "Stay";
		stay = new JButton(title);
		stay.setActionCommand(title);
		stay.addActionListener(this);
		this.add(stay);

		title = "New Game";
		game = new JButton(title);
		game.setActionCommand(title);
		game.addActionListener(this);
		this.add(game);

		title = "Bet 10$";
		betb = new JButton(title);
		betb.setActionCommand(title);
		betb.addActionListener(this);
		this.add(betb);

		title = "Double Down";
		dbl = new JButton(title);
		dbl.setActionCommand(title);
		dbl.addActionListener(this);
		this.add(dbl);

		title = "Start";
		start = new JButton(title);
		start.setActionCommand(title);
		start.addActionListener(this);
		this.add(start);


		bet = new JLabel("Your Bet: " + betv);
		bet.setFont(new Font("sansserif", Font.BOLD, 16));
		this.add(bet);

		wallet = new JLabel("Your Wallet: " + walletv);
		wallet.setFont(new Font("sansserif", Font.BOLD, 16));
		this.add(wallet);

		betb.setEnabled(true);
		hit.setEnabled(false);
		stay.setEnabled(false);
		dbl.setEnabled(false);
		start.setEnabled(true);
		
	}
	public void actionPerformed(ActionEvent ae){
		if("Hit".equals(ae.getActionCommand())){
			this.human.hit(this.dealer);
			if (this.human.score() > 21) {
				label.setText("Bust! Dealer Wins!");
				this.x = 2;
				hit.setEnabled(false);
				stay.setEnabled(false);
				dbl.setEnabled(false);
			}
			betb.setEnabled(false);
			this.x = 1;
			table.repaint();
			repaint();
			validate();
		} else if ("Stay".equals(ae.getActionCommand())){
			while(this.dealer.score() < 17){
				this.dealer.hit(this.dealer);
			}
			if (playerWins()) {
				walletv += (betv*2);
				label.setText("Player Wins!");
			}
			if (dealerWins()) {
				label.setText("Dealer Wins!");
			}
			if (this.dealer.score() == this.human.score()){
				walletv += betv;
				label.setText("Tie!");
			}
			wallet.setText("Your Wallet: " + walletv);
			this.x = 2;
			betb.setEnabled(false);
			hit.setEnabled(false);
			stay.setEnabled(false);
			dbl.setEnabled(false);
			table.repaint();
			repaint();
			validate();

			
		}
		if("Start".equals(ae.getActionCommand())){
			this.x = 1;
			betb.setEnabled(false);
			hit.setEnabled(true);
			stay.setEnabled(true);
			dbl.setEnabled(true);
			start.setEnabled(false);
			if (betv == 0) {
				dbl.setEnabled(false);
			}
			repaint();
			table.repaint();
			validate();
		}
		if ("New Game".equals(ae.getActionCommand())) {
			newHand();
			label.setText("");
			betb.setEnabled(true);
			hit.setEnabled(false);
			stay.setEnabled(false);
			dbl.setEnabled(false);
			start.setEnabled(true);
			repaint();
			table.repaint();
			validate();

			
		}
		if ("Bet 10$".equals(ae.getActionCommand())){
			betv += 10;
			walletv -= 10;
			bet.setText("Your Bet: " + betv);
			wallet.setText("Your Wallet: " + walletv);
			repaint();
			validate();
		}
		if ("Double Down".equals(ae.getActionCommand())) {
			this.human.hit(this.dealer);
			betv *= 2;
			walletv -= betv;
			if (this.human.score() > 21) {
				label.setText("Bust! Dealer Wins!");
				this.x = 2;
				hit.setEnabled(false);
				stay.setEnabled(false);
				dbl.setEnabled(false);
			}
			bet.setText("Your Bet: " + betv);
			wallet.setText("Your Wallet: " + walletv);
			betb.setEnabled(false);
			this.x = 1;
			table.repaint();
			repaint();
			validate();
		}
	}
	public int stay(){
		return this.x;
	}
	public boolean playerWins(){
		if (this.human.score() <= 21) {
			if (this.human.score() > dealer.score() || this.dealer.score() > 21) {
				return true;		
			}
		}
		return false;
	}
	public boolean dealerWins(){
		if (this.dealer.score() <= 21) {
			if (this.human.score() < this.dealer.score() || this.human.score() > 21) {
				return true;
			}
		}
		return false;
	}
	public void newHand(){
		this.human = new Human();
		this.dealer = new Dealer();
		this.table.newgame(this.human, this.dealer);
		this.human.hit(this.dealer);
		this.human.hit(this.dealer);
		this.dealer.hit(this.dealer);
		this.dealer.hit(this.dealer);
		betv = 0;
		bet.setText("Your Bet: " + betv);
		this.x = 0;
	}
	public int getBet(){
		return this.betv;
	}
	public int getWallet(){
		return this.walletv;
	}
}
