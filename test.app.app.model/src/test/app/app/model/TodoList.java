package test.app.app.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "todolist")
public class TodoList {
	
	
    private ArrayList<TodoElement> list;// = new ArrayList<>();
    private int id;
    
    @XmlElement(name = "todoelement")
    public ArrayList<TodoElement> getList() {
        return list;
    }
    
    public void setList(ArrayList<TodoElement> todoList) {
        this.list = todoList;
    }

    public int getSize() {
    	return this.list.size();
    }
    
    @XmlAttribute
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public TodoElement getElement(int index) {
    	return this.list.get(index);
    }
    
    public void setElement(TodoElement todo) {
    	this.list.add(todo);
    }
}



