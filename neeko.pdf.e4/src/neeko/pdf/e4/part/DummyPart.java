package neeko.pdf.e4.part;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.fx.core.di.LocalInstance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import neeko.pdf.ui.AppConstants;
import neeko.pdf.ui.DummyVM;
import test.app.app.model.TodoElement;
import utility.FXMLUtil;

public class DummyPart {
	@PostConstruct
	public void postConstruct(BorderPane parent, MApplication app, @LocalInstance FXMLLoader loader, @Named(value = AppConstants.SELECTED_TODO) TodoElement selected) {
		IEclipseContext context = app.getContext();
		DummyVM vm = ContextInjectionFactory.make(DummyVM.class, context);
		vm.setSelectedTodoElement(selected);
		context.set(DummyVM.class, vm);
		loader.setLocation(FXMLUtil.getPlatformUrl(AppConstants.PLUGIN_ID, "/fxml/dummy.fxml"));
		try {
			parent.setCenter((Node)loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
