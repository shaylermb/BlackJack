import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonPanel extends JPanel implements ActionListener{
	private JLabel label;
	private JButton hit, stay;
	private int value = 0;
	private Human human;
	private Dealer dealer;

	public ButtonPanel(Human human, Dealer dealer) {
		super();
		this.human = human;
		this.dealer = dealer;
		this.value = human.score();
		label = new JLabel(value+"");
		label.setFont(new Font("sansserif", Font.BOLD, 32));
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
			if (this.human.score() <21){
				label.setText(this.human.score() +"");
			}
			repaint();
		} else if ("Stay".equals(ae.getActionCommand())){
			if(this.human.score() > this.dealer.score()){
				label.setText("Player Wins!");
			} else {
				label.setText("Dealer Wins!");
			}
			repaint();
		}
	}
	
}