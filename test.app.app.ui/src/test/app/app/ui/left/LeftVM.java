package test.app.app.ui.left;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.fx.core.di.ContextValue;

import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import test.app.app.datasource.local.TodoDataSource;
import test.app.app.model.TodoElement;
import test.app.app.ui.AppConstants;

public class LeftVM {

	@Inject private TodoDataSource ds;
	private ObservableList<TodoElement> todolist;
	
	//@Inject @ContextValue(AppConstants.SELECTED_TODO) private Property<TodoElement> selected;
	@Inject @ContextValue(value = AppConstants.SELECTED_TODO) Property<TodoElement> selected;
	
	@PostConstruct
	void postContruct() throws Exception {
		todolist = ds.getModel();
	}

	public ObservableList<TodoElement> getTodoList() {
		return todolist;
	}

	public Property<TodoElement> selectedItemProperty() {
		return selected;
	}
}
