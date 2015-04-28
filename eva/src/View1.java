import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPasswordField;

public class View1 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBox;
	private static View1 frame; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new View1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField txtEid;
	private JTextField txtData;
	private JTextField txtSurname;
	private JTextField txtAge;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JList list;
	private JTextField textSearch;
	private JComboBox comboBoxSelector;
	private JTextField txtUser;
	private JPasswordField pwdPassword;

	public void refreshTable() {
		try {
			String query = "Select * FROM EmployeeInfo";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void fillComboBox() {
		try {
			String query = "SELECT * FROM EmployeeInfo";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				comboBox.addItem(rs.getString("Name"));
			}
		} catch (Exception e) {
		}
	}

	public void loadList() {
		try {
			String query = "SELECT * FROM EmployeeInfo";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			DefaultListModel DLM = new DefaultListModel();
			while (rs.next()) {
				DLM.addElement(rs.getString("Name"));
			}
			list.setModel(DLM);
			pst.close();
			rs.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Create the frame.
	 */
	public View1() {
		conn = dbConnection.dbConnector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1384, 988);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);


		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSecondForm = new JLabel("Second form");
		lblSecondForm.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblSecondForm.setBounds(804, 63, 202, 31);
		contentPane.add(lblSecondForm);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 73, 328, 309);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					String eid_ = (table.getModel().getValueAt(row, 0))
							.toString();

					String query = "SELECT * FROM EmployeeInfo WHERE EID = '"
							+ eid_ + "' ";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						txtEid.setText(rs.getString("EID"));
						txtData.setText(rs.getString("Name"));
						txtSurname.setText(rs.getString("Surname"));
						txtAge.setText(rs.getString("Age"));
					}

					pst.close();

				} catch (Exception e6) {
					e6.printStackTrace();
				}
				refreshTable();
			}
		});
		scrollPane.setViewportView(table);

		JButton loadTable = new JButton("Load employee data");
		loadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Select * FROM EmployeeInfo";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		loadTable.setBounds(768, 299, 236, 50);
		contentPane.add(loadTable);

		txtEid = new JTextField();
		txtEid.setText("eid");
		txtEid.setBounds(31, 240, 200, 50);
		contentPane.add(txtEid);
		txtEid.setColumns(10);

		txtData = new JTextField();
		txtData.setText("name");
		txtData.setBounds(31, 311, 200, 50);
		contentPane.add(txtData);
		txtData.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setText("Surname");
		txtSurname.setBounds(31, 387, 200, 50);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);

		txtUser = new JTextField();
		txtUser.setText("Username");
		txtUser.setColumns(10);
		txtUser.setBounds(31, 460, 200, 50);
		contentPane.add(txtUser);

		pwdPassword = new JPasswordField();
		pwdPassword.setText("Password");
		pwdPassword.setBounds(31, 531, 202, 50);
		contentPane.add(pwdPassword);

		txtAge = new JTextField();
		txtAge.setText("Age");
		txtAge.setBounds(31, 602, 200, 50);
		contentPane.add(txtAge);
		txtAge.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO EmployeeInfo (EID,Name,Surname,Username,password,Age) VALUES (?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, txtEid.getText());
					pst.setString(2, txtData.getText());
					pst.setString(3, txtSurname.getText());
					pst.setString(4, txtUser.getText());
					pst.setString(5, pwdPassword.getText());
					pst.setString(6, txtAge.getText());

					pst.execute();
					System.out.println("Data saved");

					pst.close();

				} catch (Exception e3) {
					e3.printStackTrace();
				}
				refreshTable();
			}
		});
		btnSave.setBounds(53, 704, 141, 35);
		contentPane.add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE EmployeeInfo SET EID='"
							+ txtEid.getText() + "' ," + " name='"
							+ txtData.getText() + "' ," + " surname='"
							+ txtSurname.getText() + "' ," + " age='"
							+ txtUser.getText() + "' ," + " Username='"
							+ pwdPassword.getText() + "' ," + " pasword='"
							+ txtAge.getText() + "' WHERE EID='"
							+ txtEid.getText() + "' ";
					PreparedStatement pst = conn.prepareStatement(query);

					pst.execute();
					System.out.println("Data updated");

					pst.close();

				} catch (Exception e4) {
					e4.printStackTrace();
				}
				refreshTable();
			}
		});
		btnUpdate.setBounds(53, 742, 141, 35);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null,
						"Do you really want to delete?", "Delete",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (action == 0) {
					try {
						String query = "DELETE FROM EmployeeInfo WHERE EID='"
								+ txtEid.getText() + "' ";
						PreparedStatement pst = conn.prepareStatement(query);

						pst.execute();
						System.out.println("Data delete");

						pst.close();

					} catch (Exception e5) {
						e5.printStackTrace();
					}
					refreshTable();
				}
			}
		});
		btnDelete.setBounds(53, 786, 141, 35);
		contentPane.add(btnDelete);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM EmployeeInfo WHERE name = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, (String) comboBox.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						txtEid.setText(rs.getString("EID"));
						txtData.setText(rs.getString("Name"));
						txtSurname.setText(rs.getString("Surname"));
						txtAge.setText(rs.getString("Age"));
					}

					pst.close();

				} catch (Exception e5) {
					e5.printStackTrace();
				}
				refreshTable();

			}

		});
		comboBox.setBounds(31, 72, 248, 50);
		contentPane.add(comboBox);

		list = new JList();
		list.setBounds(358, 426, 328, 208);
		contentPane.add(list);

		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection = (String) comboBoxSelector
							.getSelectedItem();
					String query = "SELECT EID,Name,Surname,Age FROM EmployeeInfo WHERE "
							+ selection + " =? ";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textSearch.getText());
					ResultSet rs = pst.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		});
		textSearch.setBounds(695, 424, 186, 32);
		contentPane.add(textSearch);
		textSearch.setColumns(10);

		comboBoxSelector = new JComboBox();
		comboBoxSelector.setModel(new DefaultComboBoxModel(new String[] {
				"EID", "Name", "Surname", "Age" }));
		comboBoxSelector.setBounds(695, 490, 177, 31);
		contentPane.add(comboBoxSelector);
		
		JButton btnNewButton = new JButton("Logga in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.out.println("1");
				View2 log = new View2();
				System.out.println("2");
				log.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 56));
		btnNewButton.setBounds(358, 655, 571, 208);
		contentPane.add(btnNewButton);

		refreshTable();
		fillComboBox();
		loadList();
	}
}
