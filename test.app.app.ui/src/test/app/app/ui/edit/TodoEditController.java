package test.app.app.ui.edit;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class TodoEditController {
	
	@Inject private TodoEditVM vm;

    @FXML private TextField editTitle;
    @FXML private DatePicker editDate;

	@FXML
	private void initialize() {
		editTitle.textProperty().bindBidirectional(vm.titleProperty());
		editDate.valueProperty().bindBidirectional(vm.dateProperty());
	}

}
