package test.app.app.e4.part;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.fx.core.di.LocalInstance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import test.app.app.datasource.local.TodoDataSource;
import test.app.app.ui.AppConstants;
import test.app.app.ui.FXMLUtil;
import test.app.app.ui.left.LeftVM;

public class LeftPart {

	@PostConstruct
	private void postConstruct(BorderPane parent, MApplication app, @LocalInstance FXMLLoader loader) {
	
		IEclipseContext context = app.getContext();
//	    ContextInjectionFactory.inject(TodoDataSource.class, context);
//	    context.set(TodoDataSource.class, new TodoDataSource());
		TodoDataSource ds = ContextInjectionFactory.make(TodoDataSource.class, context);
		context.set(TodoDataSource.class, ds);
		
		LeftVM vm = ContextInjectionFactory.make(LeftVM.class, context);
		context.set(LeftVM.class, vm);
//	    ContextInjectionFactory.inject(LeftVM.class, context);
//	    context.set(LeftVM.class, new LeftVM());
		
		loader.setLocation(FXMLUtil.getPlatformUrl(AppConstants.PLUGIN_ID, "/fxml/left.fxml"));
		try {
			parent.setCenter((Node)loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
}
