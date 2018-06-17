package test.database.musicdbmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import test.database.musicdbmanagement.model.Album;
import test.database.musicdbmanagement.model.Artist;
import test.database.musicdbmanagement.model.DataSource;

public class MusicDBController {
	@FXML
	private TableView artistTable;
	@FXML
	private ProgressBar progressBar;

	@FXML
	public void listArtilist() {
		Task<ObservableList<Artist>> task = new GetAllArtistTask();
		artistTable.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> progressBar.setVisible(false));
		task.setOnFailed(e -> progressBar.setVisible(false));
		new Thread(task).start();
	}

	@FXML
	public void listAlbumForArtist() {
		final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
		if (artist == null) {
			System.out.println("No Artist selected");
			return;
		}
		Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {

			@Override
			protected ObservableList<Album> call() throws Exception {
				return FXCollections
						.observableArrayList(DataSource.getInstance().queryAlbumsForArtistID(artist.getId()));
			}
		};

		new Thread(task).start();
		artistTable.itemsProperty().bind(task.valueProperty());
	}

	@FXML
	public void updateArtist() {
		final Artist artist = (Artist) artistTable.getItems().get(2);

		Task<Boolean> task = new Task<Boolean>() {
			@Override
			protected Boolean call() throws Exception {
				return DataSource.getInstance().updateArtistName("AC/DC", artist.getId());
			}
		};
		task.setOnSucceeded(e -> {
			if (task.valueProperty().get()) {
				artist.setName("AC/DC");
				artistTable.refresh();
			}
		});
		new Thread(task).start();
	}

	@FXML
	public void insertRandomArtist() {

		Task<Boolean> task = new Task<Boolean>() {
			long startTime;
			long finishTime;
			boolean result;

			@Override
			protected Boolean call() throws Exception {
				startTime = System.currentTimeMillis();
				result = DataSource.getInstance().insertRandomArtist("RandomArtist-");
				finishTime = System.currentTimeMillis();
				System.out.println("Time need - " + (finishTime - startTime) + " ms");
				return result;
			}
		};
		task.setOnSucceeded(e -> {
			if (task.valueProperty().get()) {
				System.out.println("Insert Random Artist - Successfully");
			}
		});
		new Thread(task).start();
	}

	@FXML
	public void insertRandomArtistMultiThread() {
		createThread("Task 1", 10001, 20000);
		createThread("Task 2", 20001, 30000);
		createThread("Task 3", 30001, 40000);
		createThread("Task 4", 40001, 50000);
		createThread("Task 5", 50001, 60000);
		createThread("Task 6", 60001, 70000);
		createThread("Task 7", 70001, 70000);
		createThread("Task 8", 80001, 90000);
		createThread("Task 9", 90001, 100000);
	}

	private void createThread(String threadName, int startIndex, int endIndex) {
		Task<Boolean> task = new InsertRandomArtistMultiThread(threadName, startIndex, endIndex);
		task.setOnSucceeded(e -> {
			if (task.valueProperty().get()) {
				System.out.println("Insert Random Artist MultiThread - Successfully" + threadName + "\t"
						+ Thread.currentThread().getName());
			} else {
				System.out.println("Insert Random Artist MultiThread - Failed " + threadName + "\t"
						+ Thread.currentThread().getName());
			}
		});
		new Thread(task).start();
	}
}

class GetAllArtistTask extends Task<ObservableList<Artist>> {

	@Override
	protected ObservableList<Artist> call() throws Exception {
		return FXCollections.observableArrayList(DataSource.getInstance().queryArtist(DataSource.ORDER_BY_ASC));
	}
}

class InsertRandomArtistMultiThread extends Task<Boolean> {
	long startTime, finishTime, duration;
	boolean result;
	private String name;
	private int startIndex;
	private int endIndex;

	public InsertRandomArtistMultiThread(String name, int startIndex, int endIndex) {
		this.name = name;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	protected Boolean call() throws Exception {
		startTime = System.currentTimeMillis();
		System.out.println("Start Time" + startTime + "\t" + this.name + "\t" + Thread.currentThread().getName());
		result = DataSource.getInstance().insertRandomArtistRange("RandomArtist-", this.startIndex, this.endIndex);
		finishTime = System.currentTimeMillis();
		duration = finishTime - startTime;
		System.out.println("Finish Time" + finishTime + "\t" + this.name + "\t" + Thread.currentThread().getName());
		System.out.println("Time need - " + duration + " ms -> = " + duration / 60000 + " mins -\t" + this.name + "\t"
				+ Thread.currentThread().getName());
		return result;
	}
}