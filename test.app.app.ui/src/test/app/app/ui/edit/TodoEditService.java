package test.app.app.ui.edit;

import javax.inject.Inject;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import test.app.app.datasource.local.TodoDataSource;
import test.app.app.model.TodoElement;

public class TodoEditService extends Service<Void> {
	
	//TODO CHAGE FROM NEW TO INJECTED DS FROM LEFTPART
//	@Inject TodoDataSource datasource = new TodoDataSource();
	@Inject TodoDataSource datasource;

//	private Todo updateTodo = null;
	private TodoElement editTodo = new TodoElement();
	private boolean updateMode = false;
	
	public void setUpdateTodo(TodoElement todo) {
		editTodo = todo.copy();
		updateMode  = true;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				if (updateMode) {
					updateTodo();
				} else {
					saveNewTodo();
				}
				return null;
			}
		};
	}
	
	private void updateTodo(){
		System.out.println("update! "+editTodo);
		datasource.update(editTodo);
	}
	
	private void saveNewTodo() {
		System.out.println("new todo! " + editTodo);
		datasource.saveNewTodo(editTodo);
	}
	
	public TodoElement getTodo() {
		return editTodo;
	}

}
