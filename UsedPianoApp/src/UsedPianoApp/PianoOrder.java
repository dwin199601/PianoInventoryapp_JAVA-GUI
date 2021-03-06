package UsedPianoApp;

import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PianoOrder extends Instrument implements Order {

	protected double downPayment; //from 30% to 100%
	protected int loanDuration; //12 to 24 months
	protected double interRate;
	protected double loanBalance;
	double taxPay;
	double paymentMonth=0;
	double LoanBalance = 0;
    ArrayList <String> allData = new ArrayList<String>();
	
	public String paymentSchedule(int dur, double intRate, 
			double downPay, double price) {
		String output = "\n";
		double downPayAmount = price * downPay/100;
		double inBalance = 0;
		inBalance = price - downPayAmount;
		System.out.println("Initial balance " + inBalance);
		double Principal = Math.round((inBalance/dur)*100.0)/100.0; //ROUND TO TWO DECIMAL POINTS 
		
		System.out.println("Principal: " + Principal);
		//double paymentMonth=0;
		double monthInterestRate=0;
		//double interestAccumulated  = intRate / dur;
		double interestAccumulated =0;
	    LoanBalance = inBalance;
		//intRate = intRate/100;
		monthInterestRate = intRate/dur;
		output += "Initial price for the item: $" + price + "\n";
		output += "Left price after paying down payment: $" + inBalance + "\n";
		for(int i =0; i<dur; i++) {
			 DecimalFormat df = new DecimalFormat("###.##");
			interestAccumulated = LoanBalance * monthInterestRate;
			System.out.println("\nInterest accumulated: "  + df.format(interestAccumulated));
			paymentMonth = 	Principal + interestAccumulated;
			LoanBalance =LoanBalance - Principal;
			 
			output += "\nLoan payment for the " + (i+1) + ""
					+ " month is $" + 	df.format(paymentMonth) + "\tLoan balance for this month is $"
							+ " " +df.format(LoanBalance);
			allData.add(output);
			//JOptionPane.showMessageDialog(null, "\nLoan payment for the " + (i+1) + " month is " + 	df.format(paymentMonth) + "\t\tLoan balance for this month is " +df.format(LoanBalance));
		}
		
		return output;
	}
	
	 public void saveResult(ArrayList<String> Allinfo) throws IOException {
			
		FileWriter filetoWrite = new FileWriter("C:\\MY DOC\\java 2175\\FINAL_EXAM\\LoanPay.txt");
		
		int size = Allinfo.size();
		for (int i=0; i<size; i++) {
		String str = Allinfo.get(i).toString();
		filetoWrite.write(str);
		if(i<size-1)
			filetoWrite.write("\n");
					
		}
		filetoWrite.close();
       }
	
	
	
	
	
	

	public double saleTax(int tax, double price) {
		return taxPay = tax * price; 
		
	}
	
	
	
	@Override
	public double totalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}







	@Override
	public double saleTax() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
