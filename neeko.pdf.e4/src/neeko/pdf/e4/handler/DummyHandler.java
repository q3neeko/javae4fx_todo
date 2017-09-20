package neeko.pdf.e4.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class DummyHandler {
	
	@CanExecute
	public boolean canExecute() {
		return true;
	}

	@Execute
	public void execute(MApplication app, EModelService modelService) {
		try {
			
			MTrimmedWindow newWindow;
			newWindow = (MTrimmedWindow) modelService.cloneSnippet(app, "neeko.pdf.e4.trimmedwindow.dummywindow", null);
			newWindow.getTags().add(EPartService.REMOVE_ON_HIDE_TAG); // prevent memleak
			newWindow.setLabel("Dummy");
			app.getChildren().add(newWindow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
