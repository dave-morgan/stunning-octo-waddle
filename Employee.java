
import java.util.Formatter;
import java.util.Scanner;

public class Employee implements DataIO {
	private int number;
	private String name;
	private String dob;
	private String address;
	private String gender;
	private double salary;
	private int supervisor;
	private int dNumber;

	public Employee() {
	}

	public Employee(int number, String name, String dob, String address, String gender, double salary, int supervisor,
			int dNumber) {
		this.number = number;
		this.name = name;
		this.dob = dob;
		this.address = address;
		this.gender = gender;
		this.salary = salary;
		this.supervisor = supervisor;
		this.dNumber = dNumber;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getDob() {
		return dob;
	}

	public String getAddress() {
		return address;
	}

	public String getGender() {
		return gender;
	}

	public double getSalary() {
		return salary;
	}

	public int getSupervisor() {
		return supervisor;
	}

	public int getdNumber() {
		return dNumber;
	}

	@Override
	public void dataInput(Scanner input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dataOutput(Formatter output) {
		// TODO Auto-generated method stub

	}
}

class Admin extends Employee {
	private String skills;

	public Admin() {
	}

	public Admin(int number, String name, String dob, String address, String gender, double salary, int supervisor,
			int dNumber, String skills) {
		super(number, name, dob, address, gender, salary, supervisor, dNumber);
		this.skills = skills;
	}
//	public String getSkills() {
//        return skills;
//    }

}

class Developer extends Employee {
	private String languages;

	public Developer() {
	}

	public Developer(int number, String name, String dob, String address, String gender, double salary, int supervisor,
			int dNumber, String languages) {
		super(number, name, dob, address, gender, salary, supervisor, dNumber);
		this.languages = languages;
	}

//	public String getLanguages() {
//        return languages;
//    }

}