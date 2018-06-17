package test.todolist;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import test.todolist.datamodel.ToDoData;
import test.todolist.datamodel.ToDoItem;

public class MainController {
	private List<ToDoItem> toDoItems;

	@FXML
	private ListView<ToDoItem> todoListView;
	@FXML
	private TextArea itemDetailsTextArea;
	@FXML
	private Label deadlineLabel;
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private ContextMenu listContextMenu;
	@FXML
	private ToggleButton filterToggleButton;
	private FilteredList<ToDoItem> filteredList;
	private Predicate<ToDoItem> wantAllItems;
	private Predicate<ToDoItem> wantTodayItems;

	public void initialize() {
		// menu item to delete todoItem whenever user right click on the item
		listContextMenu = new ContextMenu();
		MenuItem deleteMenuItem = new MenuItem("Delete");
		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
				deleteItem(item);
			}
		});
		listContextMenu.getItems().addAll(deleteMenuItem);

		// whenever the item of ListView is changed or selected ==> ChangeListener
		todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {
			@Override
			public void changed(ObservableValue<? extends ToDoItem> observable, ToDoItem oldValue, ToDoItem newValue) {
				if (newValue != null) {
					ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
					itemDetailsTextArea.setText(item.getDetails());
					DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d, yyyy");
					deadlineLabel.setText(df.format(item.getDeadline()));
				}
			}
		});

		wantAllItems = new Predicate<ToDoItem>() {
			// by default on start up we show all ToDo Items show we return true for
			// Predicate test
			@Override
			public boolean test(ToDoItem t) {
				return true;
			}
		};
		wantTodayItems = new Predicate<ToDoItem>() {

			@Override
			public boolean test(ToDoItem t) {
				return t.getDeadline().equals(LocalDate.now());
			}
		};

		filteredList = new FilteredList<>(ToDoData.getInstance().getToDoItems(), wantAllItems);

		// sort the ToDo Item which has been filtered inside the ListView by the
		// deadline
		// due date
		SortedList<ToDoItem> sortedList = new SortedList<>(filteredList, new Comparator<ToDoItem>() {

			@Override
			public int compare(ToDoItem o1, ToDoItem o2) {
				int result = o1.getDeadline().compareTo(o2.getDeadline());
				if (result == 0) {
					result = o1.getShortDescription().compareTo(o2.getShortDescription());
				}
				return result;
			}
		});

		// add ListView with sorted ToDo Item list, and binding it with ListView
		todoListView.setItems(sortedList);
		// bind todoList View with Singleton ToDoItems ObservationList
		// todoListView.setItems(ToDoData.getInstance().getToDoItems());

		// add ToDoItem into ListView without binding
		// todoListView.getItems().setAll(ToDoData.getInstance().getToDoItems());
		todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		todoListView.getSelectionModel().selectFirst();

		// set item with due date of today to red color
		todoListView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {

			@Override
			public ListCell<ToDoItem> call(ListView<ToDoItem> param) {
				ListCell<ToDoItem> cell = new ListCell<ToDoItem>() {

					@Override
					protected void updateItem(ToDoItem item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setText(null);
						} else {
							setText(item.getShortDescription());
							if (item.getDeadline().equals(LocalDate.now())) {
								setTextFill(Color.RED);
							} else if (item.getDeadline().equals(LocalDate.now().plusDays(1))) {
								setTextFill(Color.GREEN);
							} else if (item.getDeadline().isBefore(LocalDate.now())) {
								setTextFill(Color.FIREBRICK);
							}

						}
					}
				};

				// add ContextMenu into the ListView whenever user right click into the item
				cell.emptyProperty().addListener((obs, wasEmpty, isempty) -> {
					if (isempty) {
						cell.setContextMenu(null);
					} else {
						cell.setContextMenu(listContextMenu);
					}
				});

				return cell;
			}
		});
	}

	@FXML
	protected void showNewItemDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add New Item");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Cannot load the dialog");
			e.printStackTrace();
			return;
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		Optional<ButtonType> result = dialog.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			DialogController dialogController = fxmlLoader.getController();
			ToDoItem newItem = dialogController.processResult();
			// add new created item into the List View ==> now no need because we already
			// binding the data with List View
			// todoListView.getItems().setAll(ToDoData.getInstance().getToDoItems());
			// auto select new item to show in the detail panel
			todoListView.getSelectionModel().select(newItem);
		}
	}

	@FXML
	protected void handleKeyPress(KeyEvent keyEvent) {
		ToDoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			if (keyEvent.getCode().equals(KeyCode.DELETE)) {
				deleteItem(selectedItem);
			}
		}
	}

	@FXML
	protected void handleClickListView() {
		ToDoItem item = todoListView.getSelectionModel().getSelectedItem();
		itemDetailsTextArea.setText(item.getDetails());
		deadlineLabel.setText(item.getDeadline().toString());
	}

	private void deleteItem(ToDoItem item) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete ToDo Item");
		alert.setHeaderText("Delete Items : " + item.getShortDescription());
		alert.setContentText("Are you sure to delete ? PRESS OK to confirm , CANCEL to ignore the delete");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			ToDoData.getInstance().deleteToDoItem(item);
		}
	}

	@FXML
	protected void handleFilteredList() {
		ToDoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
		if (filterToggleButton.isSelected()) {
			filteredList.setPredicate(wantTodayItems);
			if (filteredList.isEmpty()) {
				itemDetailsTextArea.clear();
				deadlineLabel.setText("");
			} else if (filteredList.contains(selectedItem)) {
				todoListView.getSelectionModel().select(selectedItem);
			} else {
				todoListView.getSelectionModel().selectFirst();
			}
		} else {
			filteredList.setPredicate(wantAllItems);
			todoListView.getSelectionModel().select(selectedItem);
		}
	}

	@FXML
	protected void exitAction(ActionEvent event) {
		Platform.exit();
	}

}
