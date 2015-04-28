import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class View2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// View2 window = new View2();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	//
	Connection conn = null;
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public View2() {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				View2 window = new View2();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		
		conn = dbConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(204, 135, 200, 50);
		frame.getContentPane().add(lblUser);

		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(204, 233, 200, 50);
		frame.getContentPane().add(lblPass);

		textField = new JTextField();
		textField.setBounds(411, 135, 200, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Login ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM EmployeeInfo WHERE Username =? AND password=?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());

					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count = count + 1;

					}
					if (count == 1) {
						System.out.println("Username and password is correct");
						frame.dispose();
						View1 emp = new View1();
						emp.setVisible(true);

					} else if (count > 1) {
						System.out.println("Duplicate Username and password");
					} else {
						System.out
								.println("Username and password is not correct, Try again..");
					}
					rs.close();
					pst.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}

			}
		});
		btnNewButton.setBounds(238, 341, 141, 35);
		frame.getContentPane().add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('$');
		passwordField.setBounds(411, 233, 200, 50);
		frame.getContentPane().add(passwordField);
		
		JButton btnSkapaUser = new JButton("Skapa user");
		btnSkapaUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				View1 emp = new View1();
				emp.setVisible(true);
				
			}
		});
		btnSkapaUser.setBounds(0, 143, 141, 35);
		frame.getContentPane().add(btnSkapaUser);
	}
}
