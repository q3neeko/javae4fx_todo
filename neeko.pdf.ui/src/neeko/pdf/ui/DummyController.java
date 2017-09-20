package neeko.pdf.ui;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.scene.web.HTMLEditor;

public class DummyController {

    @FXML 	private HTMLEditor htmlEditor;
    @Inject private DummyVM vm;
    
    // method name must be set to "initialize()"!!!!
    @FXML
    public void initialize() { 
//    	System.err.println("Controller");
    	htmlEditor.setHtmlText(vm.getText());
    }
}
