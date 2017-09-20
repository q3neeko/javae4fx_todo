package neeko.pdf.ui;

import javax.annotation.PostConstruct;

import test.app.app.model.TodoElement;

public class DummyVM {
	
	private String text;
	private TodoElement selectedTodoElement;

	@PostConstruct
	void postContruct() throws Exception {
		
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setSelectedTodoElement(TodoElement todo) {
		this.selectedTodoElement = todo.copy();
		if(this.selectedTodoElement!=null)
			this.text = "Title: "+this.selectedTodoElement.getTitle()+"\nDate: "+this.selectedTodoElement.getDueDate();
		else
			this.text = "FAIL...";
	}
}
