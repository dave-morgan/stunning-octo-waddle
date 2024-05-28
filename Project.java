
import java.util.*;
import java.io.*;
import java.nio.*;


public class Project implements DataIO  {
	private int number;
	private String title;
	private String sponsor;
	private int dNumber;
	private double budget;

	public Project() {
		number = 0;
		title = "";
		sponsor = "";
		dNumber = 0;
		budget = 0.0;
	}

	public Project(int number, String title, String sponsor, int dNumber, double budget) {
		this.number = number;
		this.title = title;
		this.sponsor = sponsor;
		this.dNumber = dNumber;
		this.budget = budget;
	}

	public int getNumber() {
		return number;
	}

	    public String getTitle() {
	        return title;
	    }

	    public String getSponsor() {
	        return sponsor;
	    }
	    
	    public int getdNumber() {
	    	return dNumber;
	    }

	    public double getBudget() {
	        return budget;
	    }
	    

		public void setNumber(int number) {
			this.number = number;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setSponsor(String sponsor) {
			this.sponsor = sponsor;
		}

		public void setdNumber(int dNumber) {
			this.dNumber = dNumber;
		}

		public void setBudget(double budget) {
			this.budget = budget;
		}

		public void dataInput(Scanner input) {
			try {
				number = input.nextInt();
				// System.out.println("Number: " + number);
				title = input.next();
				// System.out.println("Bank: " + bank);
				sponsor = input.next();
				// System.out.println("Balance: " + balance);
				dNumber = input.nextInt();
				// System.out.println("Type: " + type);
				budget = input.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Wrong input type. " + e);
				input.next(); // Jump over the string
			}
		}

		public void dataOutput(Formatter output) {
			output.format("%s", toString());
		}

		public String toString() {
			String s = number + ", " + title + ", " + sponsor + ", " + dNumber + ", " + budget;

			return s;
		}

		public String displayString() {
			String s = "Project Number: " + number + "\nTitle: " + title + "\nSponsor: " + sponsor + "\nDepartment: " + dNumber
					+ "\nBudget: " + budget;

			return s;
		}
	    

}