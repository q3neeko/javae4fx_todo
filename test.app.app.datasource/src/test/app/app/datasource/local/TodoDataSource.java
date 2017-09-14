package test.app.app.datasource.local;

import java.time.LocalDate;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.app.app.model.TodoElement;

public class TodoDataSource{
	
	private ObservableList<TodoElement> todoList = FXCollections.observableArrayList();
	
	public ObservableList<TodoElement> getModel() {
		return getTodoList();
	}
	
	public TodoDataSource() {
		
		//TODO XML read
		for (int i = 0; i < 5 ; i++) {
			TodoElement item = new TodoElement();
			item.setId(i);
			item.setTitle("Title"+i);
			item.setDetails("Details"+i);
			item.setDueDate(LocalDate.now().plusDays(i));
			todoList.add(item);
		}
	}
	
	public ObservableList<TodoElement> getTodoList(){
		return todoList;
	}

	private int findTodoInList(TodoElement _changedTodo) {
		for (TodoElement todo : todoList) {
			if(todo.getId() == _changedTodo.getId()){
				return todoList.indexOf(todo);
			}
		}
		return -1;
	}
	
	public void deleteTodo(TodoElement _todo) {
		TodoElement todo = find(_todo.getId());
		delete(todo);
		
		int i = findTodoInList(_todo);
		Platform.runLater(() -> {
			todoList.remove(i);
		});
	}

	private void delete(TodoElement todo) {
		// TODO Auto-generated method stub
		
	}

	private TodoElement find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(TodoElement editTodo) {
		// TODO Auto-generated method stub
		
	}

	public void saveNewTodo(TodoElement editTodo) {
		// TODO Auto-generated method stub
		
	}
}
