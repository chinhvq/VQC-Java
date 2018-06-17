package test.projectmanagement.datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LogOperation {
	private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
	private SimpleStringProperty operation = new SimpleStringProperty("");
	private SimpleStringProperty tableName = new SimpleStringProperty("");
	private SimpleStringProperty at_time = new SimpleStringProperty("");
	private SimpleStringProperty result = new SimpleStringProperty("");
	private SimpleStringProperty reason = new SimpleStringProperty("");

	public LogOperation(int id, String operation, String tableName, String at_time, String result, String reason) {
		this.id.set(id);
		this.operation.set(operation);
		this.tableName.set(tableName);
		this.at_time.set(at_time);
		this.result.set(result);
		this.reason.set(reason);
	}

	public LogOperation(String operation, String tableName, String at_time, String result, String reason) {
		this.operation.set(operation);
		this.tableName.set(tableName);
		this.at_time.set(at_time);
		this.result.set(result);
		this.reason.set(reason);
	}

	public void setOperation(String operation) {
		this.operation.set(operation);
	}

	public void setTableName(String tableName) {
		this.tableName.set(tableName);
	}

	public void setAt_time(String at_time) {
		this.at_time.set(at_time);
	}

	public void setResult(String result) {
		this.result.set(result);
	}

	public void setReason(String reason) {
		this.reason.set(reason);
	}

	public String getOperation() {
		return operation.get();
	}

	public String getTableName() {
		return tableName.get();
	}

	public String getAt_time() {
		return at_time.get();
	}

	public String getResult() {
		return result.get();
	}

	public String getReason() {
		return reason.get();
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

}
