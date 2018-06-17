package test.todolist.datamodel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoData {
	private static ToDoData instance = new ToDoData();
	private static String filename = "ToDoListItems.txt";

	private DateTimeFormatter formatter;
	private ObservableList<ToDoItem> toDoItems;

	public static ToDoData getInstance() {
		return instance;
	}

	public ObservableList<ToDoItem> getToDoItems() {
		return toDoItems;
	}

	public void addToDoItem(ToDoItem toDoItem) {
		if (toDoItem != null)
			toDoItems.add(toDoItem);
	}

	public void setToDoItems(ObservableList<ToDoItem> toDoItems) {
		this.toDoItems = toDoItems;
	}

	private ToDoData() {
		this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	}

	public void loadToDoItems() throws IOException {
		toDoItems = FXCollections.observableArrayList();
		Path path = Paths.get(filename);
		String input;
		try (BufferedReader buffer = Files.newBufferedReader(path)) {
			while ((input = buffer.readLine()) != null) {
				String[] itemPieces = input.split("\t");
				String shortDescription = itemPieces[0];
				String details = itemPieces[1];
				String deadline = itemPieces[2];
				LocalDate date = LocalDate.parse(deadline, formatter);
				ToDoItem item = new ToDoItem(shortDescription, details, date);
				toDoItems.add(item);
			}
		}
	}

	public void storeToDoItems() throws IOException {
		Path path = Paths.get(filename);
		try (BufferedWriter buffer = Files.newBufferedWriter(path)) {
			for (ToDoItem item : toDoItems) {
				buffer.write(String.format("%s\t%s\t%s", item.getShortDescription(), item.getDetails(),
						item.getDeadline().format(formatter)));
				buffer.newLine();
			}
		}
	}

	public void deleteToDoItem(ToDoItem item) {
		if(item != null) {
			toDoItems.remove(item);
		}
	}
}
