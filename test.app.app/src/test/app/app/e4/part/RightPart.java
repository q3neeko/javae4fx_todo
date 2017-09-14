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
import test.app.app.ui.AppConstants;
import test.app.app.ui.FXMLUtil;
import test.app.app.ui.right.RightVM;

public class RightPart {
	
	@PostConstruct
	private void postConstruct(BorderPane parent, MApplication app, @LocalInstance FXMLLoader loader) {
		
		IEclipseContext context = app.getContext();
		
		RightVM vm = ContextInjectionFactory.make(RightVM.class, context);
		context.set(RightVM.class, vm);
		
//	    ContextInjectionFactory.inject(RightVM.class, context);
//	    context.set(RightVM.class, new RightVM());
		
		loader.setLocation(FXMLUtil.getPlatformUrl(AppConstants.PLUGIN_ID, "/fxml/right.fxml"));
		try {
			parent.setCenter((Node)loader.load());
		} catch (IOException e) {
			e.printStackTrace();
			// ...
		}	
		
	}

}
