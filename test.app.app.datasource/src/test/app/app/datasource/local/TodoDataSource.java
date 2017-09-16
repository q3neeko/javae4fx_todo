package test.app.app.datasource.local;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.app.app.model.TodoElement;
import test.app.app.model.TodoList;

public class TodoDataSource {
	
	private ObservableList<TodoElement> todoList = FXCollections.observableArrayList();
	private JAXBContext context;
	private Marshaller m;
	private Unmarshaller um;
	private File f;
	private final String path = System.getenv("APPDATA") + File.separator + "MyApp" +  File.separator + "todolist.xml";
	
	public ObservableList<TodoElement> getModel() {
		return getTodoList();
	}
	
	public TodoDataSource() {
		try {
			getDataFromXML();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void getDataFromXML() throws JAXBException, IOException {
		
		context = JAXBContext.newInstance(TodoList.class);
		um = context.createUnmarshaller();
		f = new File(path);
		if(f.exists()) {
	        TodoList todoListXML = (TodoList) um.unmarshal(new FileReader(f));
	        todoList = FXCollections.observableArrayList(todoListXML.getList());
		}
		else {
//			boolean successful = f.getParentFile().mkdirs();
//			if(successful) {
//				successful = f.createNewFile();
//					if(successful) {
//						System.out.println("todolist.xml created!");
//					}
//					else
//						System.out.println("failed trying to create the file");
//			}
//			else
//				System.out.println("failed trying to create the directory");
			System.err.println("No XML-File at "+f.getAbsolutePath());
		}
	}
	
	public void sendDataToXML(TodoElement todo) throws JAXBException, IOException {
		TodoList list = new TodoList();
		list.setElement(todo);
		m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(list, f);
	}
	
	public ObservableList<TodoElement> getTodoList() {
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
		int i = findTodoInList(editTodo);
		todoList.set(i, editTodo);
	}

	public void saveNewTodo(TodoElement editTodo) {
		// TODO Auto-generated method stub
		todoList.add(editTodo);
		try {
			sendDataToXML(editTodo);
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
