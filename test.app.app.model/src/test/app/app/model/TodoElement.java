package test.app.app.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@XmlRootElement()
@XmlType(propOrder = { "id", "title", "dueDate"})
public class TodoElement {

	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty title = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<LocalDate>();
	
	public TodoElement() {
	}

	public TodoElement(int id, String title, LocalDate dueDate) {
		this.id.set(id);
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
	public int getId() {
		return this.idProperty().get();
	}

	public void setId(int id) {
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
	
	public final IntegerProperty idProperty() {
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
