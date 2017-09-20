package neeko.pdf.ui.dummy;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class DummyController {

    @FXML 	private TextArea textArea;
    @Inject private DummyVM vm;
    
    // method name must be set to "initialize()"!!!!
    @FXML
    public void initialize() { 
    	textArea.setText(vm.getText());
    }
}
