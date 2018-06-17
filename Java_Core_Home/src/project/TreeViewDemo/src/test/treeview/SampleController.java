package test.treeview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class SampleController implements Initializable {
	@FXML
	private TreeView<String> treeView;
	@FXML
	private ComboBox<String> comboBox;
	ObservableList<String> list = FXCollections.observableArrayList("JAVA", "C#", "PYTHON");
	@FXML
	private Label label;	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TreeItem<String> root = new TreeItem<>();
		TreeItem<String> item1 = new TreeItem<>("Item1");
		TreeItem<String> item2 = new TreeItem<>("Item2");
		TreeItem<String> item11 = new TreeItem<>("Item11");
		TreeItem<String> item12 = new TreeItem<>("Item12");
		TreeItem<String> item21 = new TreeItem<>("Item21");
		TreeItem<String> item22 = new TreeItem<>("Item22");
		item1.getChildren().addAll(item11, item12);
		item2.getChildren().addAll(item21, item22);
		root.getChildren().addAll(item1, item2);
		
		treeView.setRoot(root);		
		root.setExpanded(true);
		item1.setExpanded(true);
		item2.setExpanded(true);
		treeView.setShowRoot(false);
		treeView.getSelectionModel().selectedItemProperty().addListener((observation, oldValue, newValue) -> {
			System.out.println(newValue.getValue());
		});;
		
		comboBox.setItems(list);		
	}
	
	public void comboBoxChanged(ActionEvent event) {
		label.setText("Ngôn ngữ của bạn chọn là " + comboBox.getValue());
	}
}
