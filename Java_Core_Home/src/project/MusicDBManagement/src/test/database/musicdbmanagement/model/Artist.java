package test.database.musicdbmanagement.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Artist {
	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty name = new SimpleStringProperty();
	
	protected Artist(int id, String name) {
		this.id.set(id);;
		this.name.set(name);
	}
	
	public int getId() {
		return id.get();
	}
	
	protected void setId(int id) {
		this.id.set(id);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}	
}
