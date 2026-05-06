package gui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class EMS extends JFrame{
	JTextField txtID, txtName, txtBirth, txtAge, txtNationality, txtContact, txtEmail, txtDepartment, txtPosition;
	JComboBox<String> cmbStatus;
	JRadioButton btnMale, btnFemale;
	DefaultTableModel model;
	JTable tblRecords;
	int i = 1;
	
	public EMS() {
		setTitle("Employee Management System");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 500);
		
		model = new DefaultTableModel(null, new String[] {"Employee ID", "Fullname", "Birth Date", "Age", "Civil Status", "Nationality", "Gender", "Contact", "Email", "Department", "Job Title / Position"}) {
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tblRecords= new JTable(model);
		tblRecords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tblRecords.getSelectedRow();
				if (selectedRow != -1) {
					txtID.setText(tblRecords.getValueAt(selectedRow, 0).toString());
					txtName.setText(tblRecords.getValueAt(selectedRow, 1).toString());
					txtBirth.setText(tblRecords.getValueAt(selectedRow, 2).toString());
					txtAge.setText(tblRecords.getValueAt(selectedRow, 3).toString());
					cmbStatus.setSelectedItem(tblRecords.getValueAt(selectedRow, 4).toString());
					txtNationality.setText(tblRecords.getValueAt(selectedRow, 5).toString());
					if (tblRecords.getValueAt(selectedRow, 6).toString().equals("Male")) btnMale.setSelected(true);
					else btnFemale.setSelected(true);
					txtContact.setText(tblRecords.getValueAt(selectedRow, 7).toString());
					txtEmail.setText(tblRecords.getValueAt(selectedRow, 8).toString());
					txtDepartment.setText(tblRecords.getValueAt(selectedRow, 9).toString());
					txtPosition.setText(tblRecords.getValueAt(selectedRow, 10).toString());
				}
			}
		});
		JScrollPane scrlTable = new JScrollPane(tblRecords);
		add(scrlTable).setBounds(30, 232, 730, 210);
		
		loadData();
		
		JLabel lblTitle = new JLabel("EMS Inc.");
		lblTitle.setFont(new Font("Serif", Font.BOLD, 16));
		add(lblTitle).setBounds(30, 20, 100, 15);
		
		JLabel lblID = new JLabel("Employee ID");
		add(lblID).setBounds(30, 50, 100, 15);
		txtID = new JTextField();
		add(txtID).setBounds(30, 70, 160, 20);
		
		JLabel lblName = new JLabel("Fullname");
		add(lblName).setBounds(30, 100, 100, 15);
		txtName = new JTextField();
		add(txtName).setBounds(30, 120, 160, 20);
		
		JLabel lblBirth = new JLabel("Date of Birth");
		add(lblBirth).setBounds(30, 150, 100, 15);
		txtBirth = new JTextField();
		add(txtBirth).setBounds(30, 170, 160, 20);
		
		JLabel lblAge = new JLabel("Age");
		add(lblAge).setBounds(220, 50, 100, 15);
		txtAge = new JTextField();
		add(txtAge).setBounds(220, 70, 160, 20);
		
		JLabel lblStatus = new JLabel("Civil Status");
		add(lblStatus).setBounds(220, 100, 100, 15);
		cmbStatus = new JComboBox<>(new String[] {"Single", "Married", "Widowed", "Separated", "Divorced"});
		add(cmbStatus).setBounds(220, 120, 160, 20);
		
		JLabel lblNationality = new JLabel("Nationality");
		add(lblNationality).setBounds(220, 150, 100, 15);
		txtNationality = new JTextField();
		add(txtNationality).setBounds(220, 170, 160, 20);
		
		JLabel lblGender = new JLabel("Gender");
		add(lblGender).setBounds(410, 50, 100, 15);
		ButtonGroup group = new ButtonGroup();
		btnMale = new JRadioButton("Male", true);
		btnFemale = new JRadioButton("Female");
		group.add(btnMale);
		group.add(btnFemale);
		add(btnMale).setBounds(410, 70, 60, 20);
		add(btnFemale).setBounds(470, 70, 70, 20);
		
		JLabel lblContact = new JLabel("Contact Number");
		add(lblContact).setBounds(410, 100, 100, 15);
		txtContact = new JTextField();
		add(txtContact).setBounds(410, 120, 160, 20);
		
		JLabel lblEmail = new JLabel("Email");
		add(lblEmail).setBounds(410, 150, 100, 15);
		txtEmail = new JTextField();
		add(txtEmail).setBounds(410, 170, 160, 20);
		
		JLabel lblDepartment = new JLabel ("Department");
		add(lblDepartment).setBounds(600, 100, 100, 15);
		txtDepartment = new JTextField();
		add(txtDepartment).setBounds(600, 120, 160, 20);
		
		JLabel lblPosition = new JLabel("Job Title / Position");
		add(lblPosition).setBounds(600, 150, 100, 15);
		txtPosition = new JTextField();
		add(txtPosition).setBounds(600, 170, 160, 20);
		
		JButton btnAdd = new JButton("Add Employee");
		btnAdd.addActionListener(e -> addEmployee());
		add(btnAdd).setBounds(235, 200, 130, 20);
		
		JButton btnUpdate = new JButton("Update Employee");
		btnUpdate.addActionListener(e -> updateEmployee());
		add(btnUpdate).setBounds(425, 200, 130, 20);
		
		JButton btnDelete = new JButton("Delete Employee");
		btnDelete.addActionListener(e -> deleteEmployee());
		add(btnDelete).setBounds(615, 200, 130, 20);
		
		setVisible(true);
	}
	
	public void loadData() {
		File data = new File("data.txt");
		if (data.exists() && data.length() > 0) {
			try (BufferedReader br = new BufferedReader(new FileReader(data))) {
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] row = line.split("#");
					model.addRow(row);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void clear() {
		txtID.setText("");
		txtName.setText("");
		txtBirth.setText("");
		txtAge.setText("");
		cmbStatus.setSelectedIndex(0);
		txtNationality.setText("");
		btnMale.setSelected(true);
		txtContact.setText("");
		txtEmail.setText("");
		txtDepartment.setText("");
		txtPosition.setText("");
	}
	
	public void addEmployee() {
		String id = txtID.getText(), 
			name = txtName.getText(),
			birthdate = txtBirth.getText(),
			age = txtAge.getText(),
			civilStatus = (String) cmbStatus.getSelectedItem(),
			nationality = txtNationality.getText(),
			gender = btnMale.isSelected() ? "Male" : "Female",
			contact = txtContact.getText(),
			email = txtEmail.getText(),
			department = txtDepartment.getText(),
			position = txtPosition.getText();
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", true))) {
			bw.write(id + "#" + name + "#" + birthdate + "#" + age + "#" + civilStatus + "#" + nationality + "#" + gender + "#" + contact + "#" + email + "#" + department + "#" + position + "\n");
			model.addRow(new String[] {id, name, birthdate, age, civilStatus, nationality, gender, contact, email, department, position});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clear();
		JOptionPane.showMessageDialog(null, "Record Saved!", "Employee Management System", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void updateEmployee() {
		int selectedRow = tblRecords.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Please select a row in the table!", "Employee Management System", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		String id = txtID.getText(), 
			name = txtName.getText(),
			birthdate = txtBirth.getText(),
			age = txtAge.getText(),
			civilStatus = (String) cmbStatus.getSelectedItem(),
			nationality = txtNationality.getText(),
			gender = btnMale.isSelected() ? "Male" : "Female",
			contact = txtContact.getText(),
			email = txtEmail.getText(),
			department = txtDepartment.getText(),
			position = txtPosition.getText();
		
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			ArrayList<String> lines = new ArrayList<>();
			String line = "";
			int rowIndex = 0;
			while ((line = br.readLine()) != null) {
				if (rowIndex++ != selectedRow) lines.add(line + "\n");
				else lines.add(id + "#" + name + "#" + birthdate + "#" + age + "#" + civilStatus + "#" + nationality + "#" + gender + "#" + contact + "#" + email + "#" + department + "#" + position + "\n");
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"))) {
				for (String l : lines) {
					bw.write(l);
				}
			}
			
			model.setValueAt(id, selectedRow, 0);
			model.setValueAt(name, selectedRow, 1);
			model.setValueAt(birthdate, selectedRow, 2);
			model.setValueAt(age, selectedRow, 3);
			model.setValueAt(civilStatus, selectedRow, 4);
			model.setValueAt(nationality, selectedRow, 5);
			model.setValueAt(gender, selectedRow, 6);
			model.setValueAt(contact, selectedRow, 7);
			model.setValueAt(email, selectedRow, 8);
			model.setValueAt(department, selectedRow, 9);
			model.setValueAt(position, selectedRow, 10);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clear();
		JOptionPane.showMessageDialog(null, "Record Updated!", "Employee Management System", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void deleteEmployee() {
		int selectedRow = tblRecords.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Please select a row in the table!", "Employee Management System", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
		if (confirm != JOptionPane.YES_OPTION) return; 
		
		try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
			ArrayList<String> lines = new ArrayList<>();
			String line = "";
			int rowIndex = 0;
			while ((line = br.readLine()) != null) {
				if (rowIndex++ != selectedRow) lines.add(line + "\n");
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"))) {
				for (String l : lines) {
					bw.write(l);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.removeRow(selectedRow);
		clear();
		JOptionPane.showMessageDialog(null, "Record Deleted!", "Employee Management System", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) {
		new EMS();
	}
}
