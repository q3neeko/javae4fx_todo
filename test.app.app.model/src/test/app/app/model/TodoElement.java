package test.app.app.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@XmlRootElement()
@XmlType(propOrder = { "id", "title", "dueDate"})
public class TodoElement {

	private StringProperty id = new SimpleStringProperty();
	private StringProperty title = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<LocalDate>();
	
	public TodoElement() {
		this.setId(UUID.randomUUID().toString());
	}

	public TodoElement(String id, String title, LocalDate dueDate) {
		this.setId(id);
		this.title.set(title);
		this.dueDate.set(dueDate);
	}
	

	@XmlElement
	public final String getTitle() {
		return this.titleProperty().get();
	}
	
	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	
	@XmlAttribute
	public String getId() {
		return this.idProperty().get();
	}

	private void setId(String id) {
		this.idProperty().set(id);
	}
	
	public final String getDueDate() {
		return this.dueDateProperty().get().toString();
	}
	
	public final void setDueDate(final String dueDate) {
		this.dueDateProperty().set(LocalDate.parse(dueDate));
	}
	
	public final LocalDate getLocalDate() {
		return this.dueDateProperty().get();
	}
	
	public final void setDueDate(final LocalDate dueDate) {
		this.dueDateProperty().set(dueDate);
	}
	
	public final StringProperty titleProperty() {
		return this.title;
	}
	
	public final StringProperty idProperty() {
		return this.id;
	}
	
	public final ObjectProperty<LocalDate> dueDateProperty() {
		return this.dueDate;
	}
	

	public TodoElement copy() {
		return new TodoElement(getId(), getTitle(), getLocalDate());
	}
	
	@Override
	public String toString() {
		return "TODO: "+this.getId()+", "+this.getTitle() +", "+this.getLocalDate();
	}
}
