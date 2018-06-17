package test.mycontact;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import test.mycontact.datamodel.Contact;

public class ContactController {
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField notesField;
	
	protected Contact getNewContact() {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String phoneNumber = phoneNumberField.getText();
		String notes = notesField.getText();
		
		Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
		return newContact;
	}

	protected void editContact(Contact selectedContact) {
		firstNameField.setText(selectedContact.getFirstName());
		lastNameField.setText(selectedContact.getLastName());
		phoneNumberField.setText(selectedContact.getPhoneNumber());
		notesField.setText(selectedContact.getNotes());
	}

	protected void updateContact(Contact selectedContact) {
		selectedContact.setFirstName(firstNameField.getText());
		selectedContact.setLastName(lastNameField.getText());
		selectedContact.setPhoneNumber(phoneNumberField.getText());
		selectedContact.setNotes(notesField.getText());
	}

}
