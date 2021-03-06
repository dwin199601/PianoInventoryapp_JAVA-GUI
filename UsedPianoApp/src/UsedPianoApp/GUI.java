package UsedPianoApp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {

	String price;
	String interRate=" ";
	String monthlyPayment = " ";
	ArrayList <String> allDataToStore = new ArrayList<String>();
	JLabel WarningMssdownPay = new JLabel();
	JLabel downPay = new JLabel("Down Paymnet%");
	JTextField paymentField = new JTextField(5);
	JLabel duration = new JLabel("Select Loan Duration");
	String loandDur [] = { "12 month" , "24 month" };
	JList loandurList = new JList(loandDur);
	JList tableList = new JList();
	JButton placeOrder = new JButton("Place Order");
	JLabel interRateLab = new JLabel();
	JButton saveResult = new JButton("Save payments");
	
	JTextArea output = new JTextArea();
	
	
	public GUI () {
		super("piano loan pay");
		setSize(900, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setResizable(true);
        WarningMssdownPay.setFont(new Font("Arial", Font.BOLD, 20));
        loandurList.setVisibleRowCount(2);
        output.setFont(new Font("Arrial", Font.BOLD, 13));
        output.setRows(150);
        output.setColumns(20);
        output.setVisible(false);
        output.setBackground(Color.orange);
       
        output.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        //output.setBorder(BorderFactory.createLineBorder(Color.black));
       
        //JScrollPane js = new JScrollPane(loandurList);
       
        loadDate();
        add(WarningMssdownPay);
        add(downPay);
        add(paymentField);
        add(duration);
        add(loandurList);
        add(interRateLab);
        add(tableList);
        add(placeOrder);
        add(saveResult);
        add(output);
        placeOrder.addActionListener(this);
        saveResult.addActionListener(this);
	}
	public void loadDate() {
		
		 try {
	           
	            ArrayList<String> result = PianoDBmanager.getProd();
	            // Vector to connect different data types
	            Vector itemsVector = new Vector(result);
	            tableList = new JList(itemsVector);
              
	          
	           
	        }

	        catch (SQLException e) {

	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	public void addFinalDataToJlist(ArrayList<String> allData) {
		 output.setVisible(true);
		try{
			String str =" ";
			
			int size = allData.size();
			for (int i=0; i<size; i++) {
				 str += allData.get(i).toString();	
				 
				}
			
			output.setText(str);
			
		}
		
		catch(Exception ex) {
			System.out.println("Error "  + ex);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		// TODO Auto-generated method stub
		String output =" ";
		String durationOfLoan = (String) loandurList.getSelectedValue();
		String durNumber = durationOfLoan.substring(0,2);
		
		PianoOrder objecthHere = new PianoOrder();
		
		int dur = Integer.parseInt(durNumber);
      
		Object source =e.getSource();
		
			
		if(source==placeOrder) {
			
		String downPayment = paymentField.getText();
		double downInt = Double.parseDouble(downPayment);
				//Integer.parseInt(downPayment);
		
		if(downInt<30) {
			output= "Down payment cannot be less than 30%. Enter again!";
			//WarningMssdownPay.setText(output);
			JOptionPane.showMessageDialog(null, output);
			
		}
		else if(downInt>100) {
			output= "Down payment cannot be more than 100%.Enter again!";
			//WarningMssdownPay.setText(output);
			JOptionPane.showMessageDialog(null, output);
		}
		
		
		if(durationOfLoan.equals("12 month")) {
			if (downInt>=30) {
				interRate = "4";
				interRateLab.setText("Interest rate is " + interRate + "%");
				System.out.println("The interest rate is " + interRate + "%");
				
			}
			else if(downInt>=50) {
				interRate ="3.5";
				interRateLab.setText("Interest rate is " + interRate+ "%");
				System.out.println("The interest rate is " + interRate+ "%");
			}
			else if(downInt>=70) {
				interRate ="3";
				interRateLab.setText("Interest rate is " + interRate+ "%");
				System.out.println("The interest rate is " + interRate+ "%");
			}
		}
		else if(durationOfLoan.equals("24 month")) {
			if (downInt>=30) {
				interRate ="3.5";
				interRateLab.setText("Interest rate is " + interRate+ "%");
				System.out.println("The interest rate is " + interRate+ "%");
			}
			else if(downInt>=50) {
				interRate ="3";
				interRateLab.setText("Interest rate is " + interRate+ "%");
				System.out.println("The interest rate is " + interRate+ "%");
			}
			else if(downInt>=70) {
				interRate = "3";
				interRateLab.setText("Interest rate is " + interRate+ "%");
				System.out.println("The interest rate is " + interRate+ "%");
			}
		}
		
		String Selected = (String) tableList.getSelectedValue();
		String [] values = Selected.split(" ");
		String price = values[3];
		System.out.println("Selected item price: " + price);
		double PriceDouble = Double.parseDouble(price);
		
		double intRate = Double.parseDouble(interRate)/100;
		//intRate = intRate/100;
		
		monthlyPayment = objecthHere.paymentSchedule(dur, intRate, downInt, PriceDouble);
		System.out.println(monthlyPayment);
		allDataToStore.add(monthlyPayment);
		addFinalDataToJlist(allDataToStore);
		
		
		}
		if(source == saveResult) {
			
			try {
				objecthHere.saveResult(allDataToStore);
				JOptionPane.showMessageDialog(null, "Thank you! All data was saved to the file!");
			}
			catch(Exception ex) {
				System.out.println("Error" + ex);
				JOptionPane.showMessageDialog(null, "Hm, it seems that something goes wrong, don't forget to enter down payment, choose month and item before saving");
			}
			
		}
		
		}
		catch(Exception ex) {
			System.out.println("Error: " + ex);
			JOptionPane.showMessageDialog(null, "Don't forget to enter downpayment, select loan duration and choose item!");
		}
		
	}
}
