package test.app.app.e4.part;

import javax.annotation.PostConstruct;

import org.eclipse.fx.core.di.LocalInstance;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import test.app.app.model.TodoElement;
import test.app.app.ui.AppConstants;
import test.app.app.ui.edit.TodoEditService;
import utility.FXMLUtil;

public class TodoEditDialog extends Dialog<TodoElement> {
	
	@PostConstruct
	public void postConstruct(@LocalInstance FXMLLoader loader, TodoEditService service) {
			
		loader.setLocation(FXMLUtil.getPlatformUrl(AppConstants.PLUGIN_ID, "/fxml/editDialog.fxml"));
		try {
			getDialogPane().setContent((Node) loader.load());
		} catch (Exception e) {
			e.printStackTrace();
		}

		getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		setTitle("Edit Todo");
		getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION,
				event -> service.start());
	}

}
