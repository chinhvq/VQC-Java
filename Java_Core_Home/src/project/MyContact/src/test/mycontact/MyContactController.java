package test.mycontact;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import test.mycontact.datamodel.Contact;
import test.mycontact.datamodel.ContactData;

public class MyContactController {

	@FXML
	BorderPane mainPane;
	@FXML
	TableView<Contact> contactsTable;

	private ContactData data;

	public void initialize() {
		data = new ContactData();
		data.loadContacts();
		contactsTable.setItems(data.getContacts());
	}

	@FXML
	protected void showAddContactDialog() {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainPane.getScene().getWindow());
		dialog.setTitle("Add new contact");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("ContactDialog.fxml"));
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
			ContactController contactController = fxmlLoader.getController();
			Contact newContact = contactController.getNewContact();
			data.addContact(newContact);
			data.saveContacts();
		}
	}

	@FXML
	public void showEditContactDialog() {

		Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
		if (selectedContact == null) {
			showAlert("information", "edit");
			return;
		}

		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainPane.getScene().getWindow());
		dialog.setTitle("Edit contact");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("ContactDialog.fxml"));
		try {
			dialog.getDialogPane().setContent(fxmlLoader.load());
		} catch (IOException e) {
			System.out.println("Cannot load the dialog");
			e.printStackTrace();
			return;
		}

		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		ContactController contactController = fxmlLoader.getController();
		contactController.editContact(selectedContact);

		Optional<ButtonType> result = dialog.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			contactController.updateContact(selectedContact);
			data.saveContacts();
		}
	}


	private void showAlert(String alertType, String alertMessage) {
		Alert alert = new Alert(AlertType.valueOf(alertType.toUpperCase()));
		alert.setTitle("No Contact Selected");
		alert.setHeaderText(null);
		alert.setContentText("Please select a contact to " + alertMessage);
		alert.showAndWait();
	}

	@FXML
	public void showDeleteContact() {
		Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
		if (selectedContact == null) {
			showAlert("confirmation", "delete");
			return;
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Contact");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure to delete contact " + selectedContact.getFirstName() + " "
				+ selectedContact.getLastName());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			data.deleteContact(selectedContact);
			data.saveContacts();
		}
	}

	public void exitApplication() {
		Platform.exit();
	}

}
