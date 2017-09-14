package test.app.app.ui.right;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.fx.core.Subscription;
import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.core.observable.FXObservableUtil;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import test.app.app.model.TodoElement;
import test.app.app.ui.AppConstants;

public class RightVM {

	
	//@Inject @ContextValue(AppConstants.SELECTED_TODO) private Property<TodoElement> selected;
	@Inject @ContextValue(value = AppConstants.SELECTED_TODO) Property<TodoElement> selected;
	
	private StringProperty title = new SimpleStringProperty();
	private Property<LocalDate> date = new SimpleObjectProperty<>();
	private List<Subscription> subscription = new ArrayList<>();
	
	@PostConstruct
	void postContruct() throws Exception {
		subscription.add(FXObservableUtil.onChange(selected, nV -> {
			if (nV != null) {
				title.set(nV.getTitle());
				date.setValue(nV.getDueDate());
			}
		}));
		
//		ChangeListener<? super TodoElement> listener = (o,ov,nv)-> {
//			TodoElement value = selected.getValue();
//			if (value != null) {
//				title.set(value.getTitle());
//				date.setValue(value.getDueDate());
//			}
//		};
//		selected.addListener(listener);
//		subscription.add(() -> selected.removeListener(listener));
	}	
	
	@PreDestroy
	public void dispose() {
		subscription.forEach(e -> e.dispose());
		subscription.clear();
	}
//	private StringBinding bindTitle() {
//		return Bindings.createStringBinding(()-> {
//			if(selected.getValue() == null) {
//				return null;
//			}
//			return selected.getValue().getTitle();
//		},  selected);
//	}

//	public ObjectBinding<LocalDate> bindDate() {
//		return Bindings.createObjectBinding(()-> {
//			if(selected.getValue() == null) {
//				return null;
//			}
//			return selected.getValue().getDueDate();
//		},  selected);
//	}
	
	public final Property<TodoElement> selectedProperty() {
		return this.selected;
	}

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final Property<LocalDate> dateProperty() {
		return this.date;
	}
	
}
