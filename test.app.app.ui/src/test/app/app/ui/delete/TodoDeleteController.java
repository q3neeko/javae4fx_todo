package test.app.app.ui.delete;

import javax.inject.Inject;

import javafx.fxml.FXML;

public class TodoDeleteController {
	
	@Inject private TodoDeleteVM vm;

//    @FXML private TextField delete_Title;
//    @FXML private DatePicker delete_Date;

	@FXML
	private void initialize() {
//		delete_Title.setDisable(true);
//		delete_Date.setDisable(true);
//		
//		delete_Title.textProperty().bind(vm.titleProperty());
//		delete_Date.valueProperty().bind(vm.dateProperty());
	}
}
