package pigiadibooks.admincreation;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class AdminCreationMain {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(new AccountCreationPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
