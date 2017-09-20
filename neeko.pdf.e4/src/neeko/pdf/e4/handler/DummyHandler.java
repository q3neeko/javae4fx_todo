package neeko.pdf.e4.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import neeko.pdf.ui.AppConstants;
import test.app.app.model.TodoElement;

public class DummyHandler {
	
	@CanExecute
	public boolean canExecute(@Optional @Named(value = AppConstants.SELECTED_TODO) TodoElement selected) {
		return selected!=null;
	}

	@Execute
	public void execute(MApplication app, EModelService modelService) {
		try {
			MTrimmedWindow newWindow = (MTrimmedWindow) modelService.cloneSnippet(app, AppConstants.DUMMY_WINDOW, null);
			newWindow.getTags().add(EPartService.REMOVE_ON_HIDE_TAG); // prevent memory leak
			newWindow.setLabel("Dummy");
			app.getChildren().add(newWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
