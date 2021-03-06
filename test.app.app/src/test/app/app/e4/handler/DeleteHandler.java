package test.app.app.e4.handler;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import test.app.app.model.TodoElement;
import test.app.app.ui.AppConstants;
import test.app.app.ui.edit.TodoEditService;

public class DeleteHandler {

	@CanExecute
	public boolean canExecute(@Optional @Named(value = AppConstants.SELECTED_TODO) TodoElement selected) {
		return selected != null;
	}

	@Execute
	public void execute(MApplication app, @Named(value = AppConstants.SELECTED_TODO) TodoElement selected) {
		IEclipseContext context = app.getContext();
		IEclipseContext child = context.createChild("TodoDeleteContext");
		try {
			TodoEditService service = ContextInjectionFactory.make(TodoEditService.class, child);
			service.setDeleteTodo(selected);
			child.set(TodoEditService.class, service);
//			TodoDeleteVM vm = ContextInjectionFactory.make(TodoDeleteVM.class, child);
//			child.set(TodoDeleteVM.class, vm);
//			TodoDeleteDialog dialog = ContextInjectionFactory.make(TodoDeleteDialog.class, child);
//			dialog.showAndWait();
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + selected.getTitle() + " " + selected.getDueDate() + " ?", ButtonType.YES, ButtonType.CANCEL);
//			alert.initModality(Modality.APPLICATION_MODAL);
//			alert.initOwner(owner); 
			alert.showAndWait();
			
			if (alert.getResult() == ButtonType.YES) {
			    service.start();
			}
		}  finally {
			child.dispose();
		} 
	}
}
