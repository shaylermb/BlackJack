import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

public class ButtonPanel extends JPanel implements ActionListener{
	private JLabel label;
	private JButton hit, stay;
	private int value = 0;
	private Human human;
	private Dealer dealer;
	private TableApplet table;
	private int x;

	public ButtonPanel(Human human, Dealer dealer, TableApplet table) {
		super();
		this.human = human;
		this.table = table;
		this.dealer = dealer;
		this.value = human.score();
		label = new JLabel(value+"");
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
		
	}
	public void actionPerformed(ActionEvent ae){
		if("Hit".equals(ae.getActionCommand())){
			this.human.hit(this.dealer);
			if (this.human.score() >= 21) {
				label.setText("Bust! Dealer Wins!");
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
			} else if(this.human.score() == this.dealer.score()){
				label.setText("Tie!");
			} else if(this.dealer.score() > 21){
				label.setText("Dealer Bust!");
			} else {
				label.setText("Dealer Wins!");
			}
			this.x = 1;
			table.repaint();
			validate();
		}
	}
	public int stay(){
		return this.x;
	}
	
}