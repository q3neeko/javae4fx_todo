package test.app.app.e4.handler;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;

import test.app.app.e4.part.TodoEditDialog;
import test.app.app.model.TodoElement;
import test.app.app.ui.AppConstants;
import test.app.app.ui.edit.TodoEditService;
import test.app.app.ui.edit.TodoEditVM;

public class EditHandler {

	int counter = 0;
	@CanExecute
	public boolean canExecute(@Optional @Named(value = AppConstants.SELECTED_TODO) TodoElement selected) {
		System.err.println("Edit "+ ++counter);
		return selected != null;
	}
	
	@Execute
	public void execute(MApplication app, @Named(value = AppConstants.SELECTED_TODO) TodoElement selected) {
		IEclipseContext context = app.getContext();
		IEclipseContext child = context.createChild("TodoEditContext");
		try {
			TodoEditService service = ContextInjectionFactory.make(TodoEditService.class, child);
			service.setUpdateTodo(selected);
			child.set(TodoEditService.class, service);
			TodoEditVM vm = ContextInjectionFactory.make(TodoEditVM.class, child);
			child.set(TodoEditVM.class, vm);
			TodoEditDialog dialog = ContextInjectionFactory.make(TodoEditDialog.class, child);
			dialog.showAndWait();
		} finally {
			child.dispose();
		}
	}
}
