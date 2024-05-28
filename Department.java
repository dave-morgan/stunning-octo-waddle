
import java.util.*;
import java.io.*;
import java.nio.*;

public class Department implements DataIO {
	private int number;
	private String name;
	private int manager;
	private double budget;
	private String startDate;

	public Department() {
		number = 0;
		name = "";
		manager = 0;
		budget = 0.0;
		startDate = "";

	}

	public Department(int number, String name, int manager, double budget, String startDate) {
		this.number = number;
		this.name = name;
		this.manager = manager;
		this.budget = budget;
		this.startDate = startDate;

	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public int getManager() {
		return manager;
	}

	public double getBudget() {
		return budget;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void dataInput(Scanner input) {
		try {
			number = input.nextInt();
			// System.out.println("Number: " + number);
			name = input.next();
			// System.out.println("Bank: " + bank);
			manager = input.nextInt();
			// System.out.println("Balance: " + balance);
			budget = input.nextDouble();
			// System.out.println("Type: " + type);
			startDate = input.next();
		} catch (InputMismatchException e) {
			System.out.println("Wrong input type. " + e);
			input.next(); // Jump over the string
		}
	}

	public void dataOutput(Formatter output) {
		output.format("%s", toString());
	}

	public String toString() {
		String s = number + ", " + name + ", " + manager + ", " + budget + ", " + startDate;

		return s;
	}

	public String displayString() {
		String s = "Number: " + number + "\nName: " + name + "\nManager: " + manager + "\nBudget: " + budget
				+ "\nStart Date: " + startDate;

		return s;
	}

}
