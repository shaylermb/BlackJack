import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

public class ButtonPanel extends JPanel implements ActionListener{
	private JLabel label, bet, wallet;
	private JButton hit, stay, game, betb;
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

		label = new JLabel(value+"");
		label.setFont(new Font("sansserif", Font.BOLD, 16));
		this.add(label);

		bet = new JLabel(betv+"");
		label.setFont(new Font("sansserif", Font.BOLD, 16));
		this.add(bet);

		wallet = new JLabel(walletv+"");
		label.setFont(new Font("sansserif", Font.BOLD, 16));
		this.add(wallet);

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
		
	}
	public void actionPerformed(ActionEvent ae){
		if("Hit".equals(ae.getActionCommand())){
			this.human.hit(this.dealer);
			if (this.human.score() >= 21) {
				label.setText("Bust! Dealer Wins!");
				this.x = 1;

			} 
			if (this.human.score() < 21){
				label.setText(this.human.score() +"");
			}
			table.repaint();
			repaint();
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
			betv = 0;
			this.x = 1;
			table.repaint();
			repaint();
			validate();
			
		}
		if ("New Game".equals(ae.getActionCommand())) {
			newHand();
			System.out.println("New Game");
			label.setText(this.human.score() +"");
			repaint();
			table.repaint();

			
		}
		if ("Bet 10$".equals(ae.getActionCommand())){
			betv += 10;
			walletv -= 10;
			bet.setText(betv + "");
			wallet.setText(walletv + "");
			table.repaint();
			repaint();
			System.out.println(betv);
		}
	}
	public int stay(){
		return this.x;
	}
	public boolean playerWins(){
		if (this.human.score() < 21) {
			if (this.human.score() > dealer.score() || this.dealer.score() > 21) {
				return true;		
			}
		}
		return false;
	}
	public boolean dealerWins(){
		if (this.dealer.score() < 21) {
			if (this.human.score() < this.dealer.score() || this.dealer.score() > 21) {
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
		bet.setText(betv + "");
		wallet.setText(walletv + "");
		this.x = 0;
	}
	// if(this.human.score() > this.dealer.score()){
	// 			label.setText("Player Wins!");
	// 			walletv += (betv*2);
	// 			betv = 0;
	// 		} else if(this.human.score() == this.dealer.score()){
	// 			label.setText("Tie!");
	// 		} else if(this.dealer.score() > 21){
	// 			label.setText("Dealer Bust!");
	// 			walletv += (betv*2);
	// 			wallet.setText(walletv + "");
				
	// 		} else {
	// 			label.setText("Dealer Wins!");
	// 			walletv -= betv;
	// 			betv = 0;
	// 		} 
}
