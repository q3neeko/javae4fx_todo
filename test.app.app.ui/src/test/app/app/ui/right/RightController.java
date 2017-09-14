package test.app.app.ui.right;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RightController {

	@FXML private TextField title_value;
	@FXML private DatePicker date_value;

	@Inject private RightVM vm;

	@FXML
	public void initialize() {
		title_value.setDisable(true);
		date_value.setDisable(true);
		
		title_value.textProperty().bind(vm.titleProperty());
		date_value.valueProperty().bind(vm.dateProperty());
	}
}
