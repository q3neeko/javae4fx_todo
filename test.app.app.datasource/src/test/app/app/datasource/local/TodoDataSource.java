package test.app.app.datasource.local;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
			try {
	        TodoList todoListXML = (TodoList) um.unmarshal(new FileReader(f));
	        todoList.addAll(todoListXML.getList());
			} catch (Exception e) {
				System.err.println("Failed retreive todos from XML or file is empty...");
				todoList.clear();
			}
	        
//	        List <TodoElementXML> xmlList = null;
//	        List <String> stringList = null;
//	        /*
//	         * foreach... 
//	         */
//	        stringList.addAll(xmlList.stream().map(e -> e.getTitle()).collect(Collectors.toList()));
//	        Optional<TodoElementXML> findFirst = xmlList.stream().filter(e -> e.getTitle().equals("eee")).findFirst();
//	        if (findFirst.isPresent()) {
//	        	//
//	        }
	        //todoList = FXCollections.observableArrayList(todoListXML.getList());
		}
		else {
//			boolean successful = f.getParentFile().mkdirs();
//			if(successful) {
//				successful = f.createNewFile();
//					if(successful) {
//						System.out.println("todolist.xml created!");
//					}
//					
//						System.out.println("failed trying to create the file");
//			}
//			else
//				System.out.println("failed trying to create the directory");
			System.err.println("No XML-File at "+f.getAbsolutePath());
		}
	}
	
	public void sendDataToXML() throws JAXBException, IOException {
		TodoList list = new TodoList();
		list.setList(new ArrayList<TodoElement>(todoList.stream().collect(Collectors.toList())));
//		list.setElement(new TodoElement("TITLE", LocalDate.now()));
		m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(list, f);
	}
	
	public ObservableList<TodoElement> getTodoList() {
		return todoList;
	}

	private int findTodoInList(TodoElement _changedTodo) {
		for (TodoElement todo : todoList) {
			if(todo.getId().equals(_changedTodo.getId())) {
				return todoList.indexOf(todo);
			}
		}
		return -1;
	}
	
//	public void deleteTodo(TodoElement _todo) {
//		TodoElement todo = find(_todo.getId());
//		delete(todo);
//		
//		int i = findTodoInList(_todo);
//		Platform.runLater(() -> {
//			todoList.remove(i);
//		});
//	}
//
//	private void delete(TodoElement todo) {
//		// TODO Auto-generated method stub
//		
//	}

//	private TodoElement find(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public void deleteTodo(TodoElement todo) {
		int i = findTodoInList(todo);
		todoList.remove(i);
		try {
			sendDataToXML();
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(TodoElement editTodo) {
		// TODO Auto-generated method stub
		int i = findTodoInList(editTodo);
		todoList.set(i, editTodo);
		try {
			sendDataToXML();
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveNewTodo(TodoElement editTodo) {
		// TODO Auto-generated method stub
		todoList.add(editTodo);
		try {
			sendDataToXML();
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
