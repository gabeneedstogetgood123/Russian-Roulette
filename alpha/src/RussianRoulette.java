import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class RussianRoulette extends JPanel implements ActionListener {

	Chamber[] chamber;
	static final long CENTER = 200;
	static final int DELTA = 45;
	// the three buttons
	JButton load = new JButton("Load");
	JButton spin = new JButton("Spin");
	JButton trigger = new JButton("Trigger");
	// label for Player
	JLabel label = new JLabel("");
	// the first chamber
	int firstChamber;
	int playerNumber = 1;

	RussianRoulette() {

		super(null);

		chamber = new Chamber[6];
		for(int i = 0; i < chamber.length; i++) {
			chamber[i] = new Chamber(i);
		}

		// the label
		label.setSize(200, 24);
		label.setLocation(10, 10);
		add(label);
		
		// the buttons
		JPanel p = new JPanel(new GridLayout(1,3, 10, 10));
		load.addActionListener(this);
		p.add(load);
		spin.addActionListener(this);
		spin.setEnabled(false);
		p.add(spin);
		trigger.addActionListener(this);
		trigger.setEnabled(false);
		p.add(trigger);
		p.setSize(300, 20);
		p.setLocation(50, 325);
		add(p);
	}
	// call back to tell me number of barel in canon
	void setFirstChamber(int n) {
		if(n == 6)
			n = 0;
		firstChamber = n;
		trigger.setEnabled(true);
		label.setText("Player 1 your turn");
	}
	// to update the label when the trigger is done
	void triggerDone() {
		firstChamber--;
		if(firstChamber < 0)
			firstChamber = 5;
		playerNumber++;
		label.setText("Player " + playerNumber + " your turn");		
	}
	public void actionPerformed(ActionEvent e) {
		Object b = e.getSource();
		if(b == load) {
			load.setEnabled(false);
			spin.setEnabled(true);
			chamber[0].hasBullet = true;
			repaint();
			return;
		}
		if(b == spin) {
			spin.setEnabled(false);
			Thread t = new Chamber(this, false);
			t.start();
			return;
		}
		// ok it is a trigger
		if(chamber[firstChamber].hasBullet) {
			label.setText("Player " + playerNumber + " you're dead");
			trigger.setEnabled(false);
			return;
		}
		Thread t = new Chamber(this, true);
		t.start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		// the ouside of the barrel
		int from = 50;
		int size = 250;

		g.fillOval(from, from, size, size);
		g.setColor(Color.LIGHT_GRAY);
		from += 5;
		size -= 10;
		g.fillOval(from, from, size, size);

		// the inside of the barrel
		from += 50;
		size -= 100;
		g.setColor(Color.BLACK);
		g.fillOval(from, from, size, size);
		g.setColor(Color.WHITE);
		from += 5;
		size -= 10;
		g.fillOval(from, from, size, size);

		// the chambers
		for(int i = 0; i < chamber.length; i++) {
			g.setColor(Color.BLACK);
			int x = (int) CENTER +  (int) Math.round(chamber[i].cos * 100.0);
			x -= 50;
			int y = (int) CENTER +  (int) Math.round(chamber[i].sin * 100.0);
			y -= 50;
			g.fillOval(x, y, DELTA, DELTA);
			if(!chamber[i].hasBullet) {
				g.setColor(Color.LIGHT_GRAY);
				x += 3;
				y += 3;
				g.fillOval(x, y, DELTA-6, DELTA-6);			
			}
		}
	}
	public static void main(String[] arg) {
		JFrame frame = new JFrame("Roulette");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new RussianRoulette(), BorderLayout.CENTER);
		frame.setSize(400,400);
		frame.setVisible(true);
	}

	static Random ran = new Random();
	class Chamber extends Thread{
		// proper to each barrel
		int number;
		double sin, cos, angle;
		boolean hasBullet;
		// for the trhread
		RussianRoulette panel;
		boolean trigger;   // if we rotate for spin or trigger

		// constructor for a barrel
		Chamber(int number) {
			this.number = number;
			angle = 60.0 * number;
			computeSinCos();
		}
		// for the thread
		Chamber(RussianRoulette p, boolean type) {
			panel = p;
			trigger = type;
		}
		private void computeSinCos() {
			double radian = Math.toRadians(angle - 90.0);
			sin = Math.sin(radian);
			cos = Math.cos(radian);
		}
		void spin(double degrees) {
			angle += degrees;
			computeSinCos();
		}
		public String toString() {
			return "Barrel[" + number + "] angle: " + angle + " sin: " + sin + " cos: " + cos;
		}

		public void run() {
			// if it is a trigger rotate 60 degrees
			if(trigger) {
				for(int i = 0; i < 60; i++) {
					for(int j = 0; j < panel.chamber.length; j++) 
						panel.chamber[j].spin(1.0);
					panel.repaint();
					try { Thread.sleep(10L); } catch(Exception e) {}
				}
				panel.triggerDone();
				return;
			}
			
			// number of time we will spin
			int nb = ran.nextInt(5) + 2;
			nb *= 360;
			// offset of the bullet
			int off = ran.nextInt(6);
			int first = 6 - off;
			off *= 60;
			int total = nb + off;
			for(int i = 0; i < total; i+= 2) {
				for(int j = 0; j < panel.chamber.length; j++) 
					panel.chamber[j].spin(2.0);
				panel.repaint();
				try { Thread.sleep(5L); } catch(Exception e) {}
			}
			panel.setFirstChamber(first);
			
		}
	}

}
