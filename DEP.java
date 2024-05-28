
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



import java.util.*;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;

public class DEP extends Application implements EventHandler<ActionEvent> {
	private ArrayList<Department> departments;
	private ArrayList<Employee> employees;
	private ArrayList<Project> projects;
	private ArrayList<WorksOn> workson;
	private GridPane departmentPane;
	private GridPane projectPane;
	private TextField name, manager, budget, startDate, title, sponsor, dNumber;
	private TextField messages;
	private ListView<Integer> departList;
	private ListView<Integer> proList;

	
	@Override
	public void init() {
		departments = new ArrayList<Department>();
		loadDepartments();
		
		projects = new ArrayList<Project>();
		loadProjects();
	}
	
	private void setDepartmentPane() {
		departmentPane = new GridPane();
		
		departmentPane.setStyle("--fx-background-color: white;");
		departmentPane.setHgap(10);
		departmentPane.setVgap(5);
		
		departList = new ListView<Integer>();
		departList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer> () {
			 public void changed(ObservableValue<? extends Integer> v, Integer oldV, Integer newV) {
				
				 Department depart = getDepartment(newV);
				
				if(depart != null) {
					name.setText(depart.getName());
					manager.setText(String.valueOf(depart.getManager()));
        			budget.setText(String.format("%.2f", depart.getBudget()));
        			startDate.setText(depart.getStartDate());
    			}
				
				else {
					messages.setText(String.format("Cannot find the department %d", newV));
				}
			}
		});
		
		
		departmentPane.addRow(0, new Label("Department"));
		departmentPane.addRow(0, new Label("information"));
		 
		for(Department depart:departments) {
         	departList.getItems().add(depart.getNumber());
 		}
		 
		
		ScrollPane sp = new ScrollPane();
 		sp.setPrefViewportWidth(100);
 		sp.setPrefViewportHeight(100);
 		sp.setContent(departList);
 		

 		departmentPane.addRow(1, new Label("Department Number"));
 		departmentPane.addRow(1, sp);
 		
 		departmentPane.addRow(2, new Label("Name"));
 		name = new TextField();
 		name.setEditable(false);
 		departmentPane.addRow(2, name);
 		
 		
 		departmentPane.addRow(3, new Label("Manager"));
 		manager = new TextField();
 		manager.setEditable(false);
		departmentPane.addRow(3, manager);
		 
		departmentPane.addRow(4, new Label("Budget"));
		budget = new TextField();
		budget.setEditable(false);
		departmentPane.addRow(4, budget);
		
		departmentPane.addRow(5, new Label("Start date"));
		startDate = new TextField();
		startDate.setEditable(false);
		departmentPane.addRow(5, startDate);
		
		departmentPane.setAlignment(Pos.TOP_LEFT);
	}
	
	
	
	private void setProjectPane() {
		projectPane = new GridPane();
		
		projectPane.setStyle("--fx-background-color: white;");
		projectPane.setHgap(10);
		projectPane.setVgap(5);
		
		proList = new ListView<Integer>();
		proList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer> () {
			public void changed(ObservableValue<? extends Integer> v, Integer oldV, Integer newV) {
				
				Project pro = getProject(newV);
				if(pro != null) {
					title.setText(pro.getTitle());
					sponsor.setText(pro.getSponsor());
					dNumber.setText(String.valueOf(pro.getdNumber()));
        			budget.setText(String.format("%.2f", pro.getBudget()));
    			}
				
				else {
					messages.setText(String.format("Cannot find the project %d", newV));
				}
			}
		});
		
		
		projectPane.addRow(0, new Label("Project"));
		projectPane.addRow(0, new Label("information"));
		 
		for(Project pro:projects) {
         	proList.getItems().add(pro.getNumber());
 		}
		 
		//might need to change this
		ScrollPane sp = new ScrollPane();
 		sp.setPrefViewportWidth(100);
 		sp.setPrefViewportHeight(100);
 		sp.setContent(proList);
 		

 		projectPane.addRow(1, new Label("Project Number"));
 		projectPane.addRow(1, sp);
 		
 		projectPane.addRow(2, new Label("Title"));
 		title = new TextField();
 		title.setEditable(false);
 		projectPane.addRow(2, title);
 		
 		
 		projectPane.addRow(3, new Label("Sponsor"));
 		sponsor = new TextField();
 		sponsor.setEditable(false);
 		projectPane.addRow(3, sponsor);
 		
 		projectPane.addRow(5, new Label("Department"));
 		dNumber = new TextField();
 		dNumber.setEditable(false);
		projectPane.addRow(5, dNumber);
		 
 		projectPane.addRow(6, new Label("Budget"));
		budget = new TextField();
		budget.setEditable(false);
		projectPane.addRow(6, budget);
		
		
		
		projectPane.setAlignment(Pos.TOP_CENTER);
	}
	
//	private void setButtonPane() {
//		buttonPane = new FlowPane();
//		buttonPane.setStyle("-fx-background-color: white;");
//		buttonPane.setHgap(10);
//		
//		add = new Button("Add");
//		add.setOnAction(this);
//		buttonPane.getChildren().add(add);
//		
//		delete = new Button("Delete");
//		delete.setOnAction(this);
//		buttonPane.getChildren().add(delete);
//		
//		save = new Button("Save");
//		save.setOnAction(this);
//		buttonPane.getChildren().add(save);
//		
//		buttonPane.setAlignment(Pos.CENTER);	
		
//	public void setMessagePane() {
//		messagePane = new FlowPane();
//			
//		messagePane.getChildren().add(new Label("Message"));
//			
//		messages = new TextField();
//		messagePane.getChildren().add(messages);
//			
//		messagePane.setAlignment(Pos.CENTER);
//	}
	
	@Override
	public void start(Stage primaryStage){
		BorderPane root = new BorderPane();
		
		setDepartmentPane();
		root.setLeft(departmentPane);
		setProjectPane();
		root.setCenter(projectPane);
		
//		setButtonPane();
//		root.setCenter(buttonPane);
//		
//		setMessagePane();
//		root.setBottom(messagePane);
//		
		Scene scene = new Scene(root, 800, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Department management system");
		primaryStage.show();			
    }
	
	public static void main(String[] args) {
        launch(args);
    }
	
	
	private void loadDepartments() {
		String filename = "departments.txt";
		Path path = Paths.get(filename);
		int cnt = 0;
		try {
			if(Files.exists(path)) {		
				if(!Files.isDirectory(path)) { //Not a directory, read data
					Scanner fin = new Scanner(path);
					fin.useDelimiter(", |\r\n|\t|\n");
					//Clear the container
					departments.clear();
					while(fin.hasNext()) {
						Department depart = new Department();
						depart.dataInput(fin);
						departments.add(depart);
						cnt ++;
					}
					fin.close();
				}
				else
					System.out.printf("File %s does not exist", path);
			}
		}
		catch (IOException err) {
			System.out.println("Exception: " + err);
		}
		
		if(cnt >= 1)
			System.out.println(String.format("%d departments have been loaded.", cnt));
		else
			System.out.println("No department loaded.");
	}
	
	
	public Department getDepartment(int number) {
		for(Department depart:departments) {
			if(depart.getNumber() == number)
				return depart;
		}
		
		return null;
	}
	
	public boolean isExists(int number) {
		for(Department depart:departments) {
			if(depart.getNumber() == number)
				return true;
		}
		return false;
	}
	
	private void loadProjects() {
		String filename = "projects.txt";
		Path path = Paths.get(filename);
		int cnt = 0;
		try {
			if(Files.exists(path)) {		
				if(!Files.isDirectory(path)) { //Not a directory, read data
					Scanner fin = new Scanner(path);
					fin.useDelimiter(", |\r\n|\t|\n");
					//Clear the container
					projects.clear();
					while(fin.hasNext()) {
						Project pro = new Project();
						pro.dataInput(fin);
						projects.add(pro);
						cnt ++;
					}
					fin.close();
				}
				else
					System.out.printf("File %s does not exist", path);
			}
		}
		catch (IOException err) {
			System.out.println("Exception: " + err);
		}
		
		if(cnt >= 1)
			System.out.println(String.format("%d project have been loaded.", cnt));
		else
			System.out.println("No project loaded.");
	}
	
	
	public Project getProject(int number) {
		for(Project pro:projects) {
			if(pro.getNumber() == number)
				return pro;
		}
		
		return null;
	}
	
	public boolean isExistsPro(int number) {
		for(Project pro:projects) {
			if(pro.getNumber() == number)
				return true;
		}
		return false;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}}
	
	
	
//	
//	@Override
//	public void handle(ActionEvent e) {
//		if((Button)e.getSource() == add) {//Add button clicked
//			addDepartment();
//		}
//		else if((Button)e.getSource() == delete) { //Delete button clicked
//			deleteDepartment();
//		}
//		else if((Button)e.getSource() == save) { //Save button clicked
//			saveDepartment);
//			messages.setText(String.format("%d accounts are saved", departments.size()));
//		}
//		else {
//			messages.setText("Wrong button");
//		}
//	}
		

//	public DEP() {
//
//		this.employees = new ArrayList<>();
//		this.projects = new ArrayList<>();
//		this.workson = new ArrayList<>();
//	}

            
            
            // Add labels to the GridPane
           
            
            
            
//            depPane.add(new Label("Department" + "\nNumber"), 0, 1);
            
            
            
    		

            

           
           
//            
//            
//			Scene scene = new Scene(root,400,400);
//			primaryStage.setScene(scene);
//			primaryStage.show();
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
////
//	public static void main(String[] arg) {
//		DEP dep = new DEP();
//		dep.loadDepartments();
//		dep.loadEmployees();
//		dep.loadProjects();
//		dep.loadWorksOn();
//		
//		launch(arg);
//	}

//	public void loadDepartments() {
//		ArrayList<ArrayList<String>> deconstructedFile = this.readFile("departments.txt");
//		deconstructedFile.forEach((fileline) -> {
//			//1, SALES, 110, 1234.00, 02/01/2012
//			int number = Integer.parseInt(fileline.get(0));
//			String name = fileline.get(1);
//			int manager = Integer.parseInt(fileline.get(2));
//			double budget = Double.parseDouble(fileline.get(3));
//			String startDate = fileline.get(4);
//					
//			Department department = new Department(number, name, manager, budget, startDate);
//			this.departments.add(department);
//			
//		});
//	}

//	public void loadEmployees() {
//		ArrayList<ArrayList<String>> deconstructedFile = this.readFile("employees.txt");
//		deconstructedFile.forEach((fileline) -> {
//			// A, 600, Willy, 01/01/1988, 41 Station Street Wollongong NSW 2500, M, 250.5,
//			// 0, 9, Cook Read
//			String employeeType = fileline.get(0);
//			int number = Integer.parseInt(fileline.get(1));
//			String name = fileline.get(2);
//			String dob = fileline.get(3);
//			String address = fileline.get(4);
//			String gender = fileline.get(5);
//			double salary = Double.parseDouble(fileline.get(6));
//			int supervisor = Integer.parseInt(fileline.get(7));
//			int dNumber = Integer.parseInt(fileline.get(8));
//			String skillsOrLanguages = fileline.get(9);
//
//			if (employeeType.equals("A")) {
//				Admin admin = new Admin(number, name, dob, address, gender, salary, supervisor, dNumber,
//						skillsOrLanguages);
//				this.employees.add(admin);
//
//				return;
//			}
//
//			if (employeeType.equals("D")) {
//				Developer developer = new Developer(number, name, dob, address, gender, salary, supervisor, dNumber,
//						skillsOrLanguages);
//				this.employees.add(developer);
//
//				return;
//			}
//
//		});
//	}
//
//	public void loadProjects() {
//		
//		ArrayList<ArrayList<String>> deconstructedFile = this.readFile("projects.txt");
//		deconstructedFile.forEach((fileline) -> {
//			//1001, Computation, Microsoft, 8, 25000
//			int number = Integer.parseInt(fileline.get(0));
//			String title = fileline.get(1);
//			String sponser = fileline.get(2);
//			int dNumber = Integer.parseInt(fileline.get(3));
//			double budget = Double.parseDouble(fileline.get(4));
//					
//			Project project = new Project(number, title, sponser, dNumber, budget);
//			this.projects.add(project);
//			
//		});
//		
//	}
//
//	public void loadWorksOn() {
//		ArrayList<ArrayList<String>> deconstructedFile = this.readFile("workson.txt");
//		deconstructedFile.forEach((fileline) -> {
//			//800, 1001, 10
//			int eNumber = Integer.parseInt(fileline.get(0));
//			int pNumber = Integer.parseInt(fileline.get(1));
//			int hours = Integer.parseInt(fileline.get(2));
//		
//					
//			WorksOn workson = new WorksOn(eNumber, pNumber, hours);
//			this.workson.add(workson);
//			
//		});
//	}
//	
//
//	private ArrayList<ArrayList<String>> readFile(String filename) {
//		ArrayList<ArrayList<String>> filelist = new ArrayList<>();
//		try {
//			Scanner fileScanner = new Scanner(new File(filename));
//
//			while (fileScanner.hasNextLine()) {
//				// Split each line into array strings seperated by , and push to filelist
//				String line = fileScanner.nextLine();
//				ArrayList<String> wordsOfLine = new ArrayList<String>(Arrays.asList(line.split(", ")));
//				
//				filelist.add(wordsOfLine);
//			}
//
//			fileScanner.close();
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
//
//		return filelist;
//	}
