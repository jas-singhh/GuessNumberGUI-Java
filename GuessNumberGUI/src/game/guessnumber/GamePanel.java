package game.guessnumber;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {
	
	private final int xCentre = GameFrame.WIDTH / 2;
	private final int yCentre = GameFrame.HEIGHT / 2;
	
	private JLabel triesLabel;
	private JLabel label;
	private JTextField tField;
	private JButton button;
	
	private Random random;
	private Scanner scan;
	private int guess;
	private int attempts;
	
	GamePanel() {
		this.setLayout(null);
		this.setBackground(Color.white.darker());
		
		random = new Random();
		scan = new Scanner(System.in);
		
		guess = random.nextInt(100) + 1;// 1-100
		attempts = 0;
		
		triesLabel = new JLabel();
		label = new JLabel();
		tField = new JTextField();
		button = new JButton();

		triesLabel.setText("Attempts: " + attempts);
		triesLabel.setBounds(xCentre - 50, yCentre - 120, GameFrame.WIDTH, 25);
		label.setText("Enter your guess 1-100");
		label.setBounds(xCentre - 85, yCentre - 100, GameFrame.WIDTH, 25);
		tField.setBounds(xCentre - 65, yCentre - 50, 100, 20);
		button.setText("Enter");
		button.setBounds(xCentre - 65, yCentre - 20, 100, 20);
		
		this.add(triesLabel);
		this.add(label);
		this.add(tField);
		this.add(button);
		
		onButtonClick();
	}
	
	private void onButtonClick() {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button) {
					if (tField.getText().isEmpty()) {
						label.setForeground(Color.RED);
						label.setText("Enter your guess");
					} 
					
					try {
						int input = Integer.parseInt(tField.getText());
						checkInput(input);
						triesLabel.setText("Attempts: " + attempts);
					} catch (Exception exception) {
						label.setForeground(Color.RED);
						label.setText("Enter a valid number");
					}
					
					tField.setText("");// clear the text field after button click
				}
				
			}
			
		});
	}
	
	private void checkInput(int input) {
		if (input > guess) {
			label.setForeground(Color.BLACK);
			label.setText("Number is too large, try a smaller one");
			attempts++;
		} else if (input < guess) {
			label.setForeground(Color.BLACK);
			label.setText("Number is too small, try a larger one");
			attempts++;
		} else if (input == guess) {
			label.setForeground(Color.GREEN);
			label.setText("Congratulations, you guessed the number");
			tField.setEnabled(false);
			button.setEnabled(false);
		}
	}

}
