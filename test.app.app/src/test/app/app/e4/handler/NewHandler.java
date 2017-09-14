package test.app.app.e4.handler;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;

import test.app.app.e4.part.TodoEditDialog;
import test.app.app.ui.edit.TodoEditService;
import test.app.app.ui.edit.TodoEditVM;

public class NewHandler {
	
	int counter = 0;
	@CanExecute
	public boolean canExecute() {
		System.err.println("New "+ ++counter);
		return true;
	}
	
	@Execute
	public void execute(MApplication app) {
		IEclipseContext context = app.getContext();
		IEclipseContext child = context.createChild("TodoNewContext");
		try {
			TodoEditService service = ContextInjectionFactory.make(TodoEditService.class, child);
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
