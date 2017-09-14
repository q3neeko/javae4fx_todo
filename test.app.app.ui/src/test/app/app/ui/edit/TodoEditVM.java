package test.app.app.ui.edit;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TodoEditVM {
	
	@Inject private TodoEditService service;
	private StringProperty title = new SimpleStringProperty();
	private Property<LocalDate> date = new SimpleObjectProperty<>();
	
	@PostConstruct
	void postContruct() throws Exception {
		title.bindBidirectional(service.getTodo().titleProperty());
		date.bindBidirectional(service.getTodo().dueDateProperty());
	}	

	public final StringProperty titleProperty() {
		return this.title;
	}
	
	public final Property<LocalDate> dateProperty() {
		return this.date;
	}
	
}
