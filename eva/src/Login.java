import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import java.sql.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		conn = dbConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
					if (count==1){
						System.out.println("Username and password is correct");
						frame.dispose();
						View1 emp = new View1();
						emp.setVisible(true);
						
					}
					else if(count>1){
						System.out.println("Duplicate Username and password");
					}
					else {
						System.out.println("Username and password is not correct, Try again..");
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
		
		lblNewLabel = new JLabel("New label");
		Image img = new ImageIcon(this.getClass().getResource("/hej.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(23, 26, 141, 350);
		frame.getContentPane().add(lblNewLabel);
	}
}
