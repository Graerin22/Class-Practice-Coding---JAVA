package guiTable;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ExpenseTracker extends JFrame{
	JTextField txtReceipt, txtStore, txtTotal, txtTax, txtFinal;
	DefaultTableModel tblModelRecords;
	int i = 1;
	
	ExpenseTracker() {
		setTitle("Expense Tracker");
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 320);
		
		tblModelRecords = new DefaultTableModel(null, new String[] {"Receipt Number", "Store Name", "Total Cost", "Tax (12%)", "Final Amount"});
		JTable tblRecords = new JTable(tblModelRecords);
		tblRecords.setEnabled(false);
		tblRecords.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblRecords.setPreferredScrollableViewportSize(new Dimension(350, 200));
		JScrollPane scrlTable = new JScrollPane(tblRecords);
		add(scrlTable).setBounds(315, 40, 350, 200);
		
		File data = new File("data.txt");
		if (data.exists() && data.length() > 0) {
			try (BufferedReader br = new BufferedReader(new FileReader(data))) {
				String line = "";
				String receipt = "", store = "", total = "", tax = "", finalAmount = "";
				while ((line = br.readLine()) != null) {
					if (line.contains("Receipt Number")) receipt = line.split(":")[1].trim();
					else if (line.contains("Store Name")) store = line.split(":")[1].trim();
					else if (line.contains("Total Cost")) total = line.split(":")[1].trim();
					else if (line.contains("Tax")) tax = line.split(":")[1].trim();
					else if (line.contains("Final Amount")) {
						finalAmount = line.split(":")[1].trim();
						tblModelRecords.addRow(new String[] {receipt, store, total, tax, finalAmount});
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JLabel lblTitle = new JLabel("Expense Tracker");
		lblTitle.setFont(new Font("", Font.BOLD, 16));
		add(lblTitle).setBounds(95, 10, 200, 30);
		
		JLabel lblReceipt = new JLabel("Receipt number:");
		add(lblReceipt).setBounds(30, 45, 100, 20);
		txtReceipt = new JTextField();
		add(txtReceipt).setBounds(140, 45, 150, 20);
		
		JLabel lblStore = new JLabel("Store Name:");
		add(lblStore).setBounds(30, 80, 100, 20);
		txtStore = new JTextField();
		add(txtStore).setBounds(140, 80, 150, 20);
		
		JLabel lblTotal = new JLabel("Total Cost:");
		add(lblTotal).setBounds(30, 115, 100, 20);
		txtTotal = new JTextField();
		add(txtTotal).setBounds(140, 115, 150, 20);
		
		JLabel lblTax = new JLabel("Tax (12%):");
		add(lblTax).setBounds(30, 160, 100, 20);
		txtTax = new JTextField();
		txtTax.setEditable(false);
		add(txtTax).setBounds(140, 160, 150, 20);
		
		JLabel lblFinal = new JLabel("Final Amount:");
		add(lblFinal).setBounds(30, 195, 100, 20);
		txtFinal = new JTextField();
		txtFinal.setEditable(false);
		add(txtFinal).setBounds(140, 195, 150, 20);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addToFileAndGraph();
			}
		});
		add(btnRecord).setBounds(50, 235, 100, 25);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		add(btnClear).setBounds(170, 235, 100, 25);
		
		setVisible(true);
	}
	
	public void addToFileAndGraph() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt", true))) {
			double total = Double.parseDouble(txtTotal.getText());
			String receipt = txtReceipt.getText();
			String store = txtStore.getText();
			double tax = total * 0.12;
			double finalAmount = total + tax;
			
			txtTax.setText(String.format("%.2f", tax));
			txtFinal.setText(String.format("%.2f", finalAmount));
			
			bw.write("=== Record " + (i++) + " ===\n"
					+ "Receipt Number: " + receipt
					+ "\nStore Name: " + store
					+ "\nTotal Cost: " + String.format("%.2f", total)
					+ "\nTax (12%): " + txtTax.getText()
					+ "\nFinal Amount: " + txtFinal.getText()
					+ "\n\n");
			
			tblModelRecords.addRow(new String[] {receipt, store, String.format("%.2f", total), txtTax.getText(), txtFinal.getText()});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clear() {
		txtReceipt.setText("");
		txtStore.setText("");
		txtTotal.setText("");
		txtTax.setText("");
		txtFinal.setText("");
	}
	
	public static void main(String[] args) {
		new ExpenseTracker();
	}
}
