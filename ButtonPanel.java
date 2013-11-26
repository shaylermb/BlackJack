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
				walletv -= betv;
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
			if(this.human.score() > this.dealer.score()){
				label.setText("Player Wins!");
				walletv += (betv*2);
				betv = 0;
			} else if(this.human.score() == this.dealer.score()){
				label.setText("Tie!");
			} else if(this.dealer.score() > 21){
				label.setText("Dealer Bust!");
				walletv += (betv*2);
				betv = 0;
				wallet.setText(walletv + "");
				
			} else {
				label.setText("Dealer Wins!");
				walletv -= betv;
				betv = 0;
			}
			this.x = 1;
			table.repaint();
			validate();
		}
		if ("New Game".equals(ae.getActionCommand())) {
			this.table = new TableApplet();
			System.out.println("New Game");
			table.repaint();
			repaint();
			validate();
			
		}
		if ("Bet 10$".equals(ae.getActionCommand())){
			betv += 10;
			bet.setText(betv + "");
			table.repaint();
			repaint();
			System.out.println(betv);
		}
	}
	public int stay(){
		return this.x;
	}
	
}