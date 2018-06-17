package test.todolist;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import test.todolist.datamodel.ToDoData;
import test.todolist.datamodel.ToDoItem;

public class DialogController {
	@FXML
	private TextField shortDescriptionField;
	@FXML
	private TextArea detailsArea;
	@FXML
	private DatePicker deadlinePicker;
	
	@FXML
	public ToDoItem processResult() {
		String shortDescription = shortDescriptionField.getText().trim();
		String details = detailsArea.getText().trim();
		LocalDate deadlineValue = deadlinePicker.getValue();
		
		ToDoItem newItem = new ToDoItem(shortDescription, details, deadlineValue);
		ToDoData.getInstance().addToDoItem(newItem);
		return newItem;
	}
}
