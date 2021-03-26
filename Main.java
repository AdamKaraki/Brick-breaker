package brick;

import javax.swing.*;

public class Main {
	

	
	public static void main(String[] args) {
		
		JFrame jframe = new JFrame("Brick Destroyer");
		Player Player =new Player();
		jframe.setBounds(10,10,700,620);
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.setLocationRelativeTo(null);
		jframe.add(Player);

		
	}

}
