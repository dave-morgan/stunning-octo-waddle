import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * This is the GUI class from your Deparment details
 * You can create other versions for each panel you need
 * Ideally you would have another general GUI class that would build these GUI panels
 */
class DepartmentGUI {
  public GridPane container;
  public ListView<Integer> departmentNumbers;
  public TextField name, manager, budget, startDate;

  private DepartmentGUI(Builder builder) {
    container = builder.container;
    departmentNumbers = builder.departmentNumbers;
    name = builder.name;
    manager = builder.manager;
    budget = builder.budget;
    startDate = builder.startDate;
  }

  public void addDepartmentNumbersListener(ChangeListener<Integer> listener) {
    departmentNumbers.getSelectionModel().selectedItemProperty().addListener(listener);
  }

  /**
   * This is the builder class for the DepartmentGUI
   * A builder class is a design pattern that allows you to create objects with a lot of parameters
   * each method returns the builder object so you can chain the methods
   * the build method creates the object with the parameters you set
   */
  public static class Builder {
    private GridPane container;
    private ListView<Integer> departmentNumbers;
    private TextField name, manager, budget, startDate;

    public Builder() {
      container = new GridPane();
      departmentNumbers = new ListView<>();
      name = new TextField();
      manager = new TextField();
      budget = new TextField();
      startDate = new TextField();
    }

    // Container

    public Builder setContainerConfiguration(String style, int hGap, int vGap, Pos alignment) {
      container.setStyle(style);
      container.setHgap(hGap);
      container.setVgap(vGap);
      container.setAlignment(alignment);
      return this;
    }

    public Builder addContainerRowLabel(int rowIndex, String labelText) {
      container.addRow(rowIndex, new Label(labelText));
      return this;
    }

    public Builder addContainerRowScrollPane(int rowIndex, ScrollPane scrollPane) {
      container.addRow(rowIndex, scrollPane);
      return this;
    }

    public Builder addContainerRowTextField(int rowIndex, TextField textField) {
      container.addRow(rowIndex, textField);
      return this;
    }

    // Department Numbers
    // This could also be made to pass a lambda function that returns a listener
    // instead of listener and this would be called from within here and passed the build context.
    public Builder addDepartmentNumbersListener(ChangeListener<Integer> listener) {
      departmentNumbers.getSelectionModel().selectedItemProperty().addListener(listener);
      return this;
    }

    public Builder addDepartmentNumbers(ArrayList<Department> departments) {
      for(Department department:departments) {
        departmentNumbers.getItems().add(department.getNumber());
      }
      return this;
    }

    public Builder setDepartmentNumbersView(int rowIndex, String labelText, int viewWidth, int viewHeight) {
      ScrollPane scrollPane = new ScrollPane();
      scrollPane.setPrefViewportWidth(viewWidth);
      scrollPane.setPrefViewportHeight(viewHeight);
      scrollPane.setContent(departmentNumbers);

      this.addContainerRowLabel(rowIndex, labelText);
      this.addContainerRowScrollPane(rowIndex, scrollPane);

      return this;
    }

    public Builder setNameView(int rowIndex, String labelText, boolean isEditable) {
      name.setEditable(isEditable);
      this.addContainerRowLabel(rowIndex, labelText);
      this.addContainerRowTextField(rowIndex, name);
      return this;
    }

    public Builder setManagerView(int rowIndex, String labelText, boolean isEditable) {
      manager.setEditable(isEditable);
      this.addContainerRowLabel(rowIndex, labelText);
      this.addContainerRowTextField(rowIndex, manager);
      return this;
    }

    public Builder setBudgetView(int rowIndex, String labelText, boolean isEditable) {
      budget.setEditable(isEditable);
      this.addContainerRowLabel(rowIndex, labelText);
      this.addContainerRowTextField(rowIndex, budget);
      return this;
    }

    public Builder setStartDateView(int rowIndex, String labelText, boolean isEditable) {
      startDate.setEditable(isEditable);
      this.addContainerRowLabel(rowIndex, labelText);
      this.addContainerRowTextField(rowIndex, startDate);
      return this;
    }


    public DepartmentGUI build() {
      return new DepartmentGUI(this);
    }

  }
}