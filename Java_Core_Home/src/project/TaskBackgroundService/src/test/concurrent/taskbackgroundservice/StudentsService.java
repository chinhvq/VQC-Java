package test.concurrent.taskbackgroundservice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class StudentsService extends Service<ObservableList<String>> {
	
	@Override
	protected Task<ObservableList<String>> createTask() {
		return new Task<ObservableList<String>>() {

			@Override
			protected ObservableList<String> call() throws Exception {
				String[] names = { "Chinh", "Dung", "Mai Anh", "Tu", "Hoang", "Bao" };
				final ObservableList<String> students = FXCollections.observableArrayList();
				for (int i = 0; i < names.length; i++) {
					students.add(names[i]);
					updateProgress(i + 1, names.length);
					updateMessage("Add " + names[i] + " to the list");
					Thread.sleep(200);
				}
				return students;
			}
			
		};
	}

}
