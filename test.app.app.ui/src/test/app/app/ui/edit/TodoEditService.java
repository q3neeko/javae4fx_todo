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
	private boolean deleteMode = false;
	
	public void setUpdateTodo(TodoElement todo) {
		editTodo = todo.copy();
		updateMode  = true;
	}
	
	public void setDeleteTodo(TodoElement todo) {
		editTodo = todo.copy();
		deleteMode  = true;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				if (updateMode && !deleteMode) {
					updateTodo();
				} else if(!updateMode && deleteMode) {
					deleteTodo();
				} else {
					saveNewTodo();
				}
				return null;
			}
		};
	}
	
	private void updateTodo(){
		System.out.println("update todo! "+editTodo);
		datasource.update(editTodo);
	}
	
	private void saveNewTodo() {
		System.out.println("new todo! " + editTodo);
		datasource.saveNewTodo(editTodo);
	}
	
	private void deleteTodo() {
		System.out.println("delete todo! " + editTodo);
		datasource.deleteTodo(editTodo);
	}
	
	public TodoElement getTodo() {
		return editTodo;
	}

}
