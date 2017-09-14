package test.app.app.ui.left;

import java.time.LocalDate;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import test.app.app.model.TodoElement;

public class LeftController {
	@FXML private TableView<TodoElement> table;
	@FXML private TableColumn<TodoElement, String> col1;
	@FXML private TableColumn<TodoElement, LocalDate> col2;
	
	@Inject LeftVM vm;
	
	@FXML
	public void initialize() {
		table.setItems(vm.getTodoList());
		col1.setCellValueFactory(e -> e.getValue().titleProperty());
		col2.setCellValueFactory(e-> e.getValue().dueDateProperty());
//		col2.setCellFactory(col-> );
		vm.selectedItemProperty().bind(table.getSelectionModel().selectedItemProperty());

		table.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				vm.editTodo();
			}
		});
	}
	
}
