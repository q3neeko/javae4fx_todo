package neeko.pdf.ui;

import javax.annotation.PostConstruct;

public class DummyVM {
	
//	@Inject @ContextValue(value = AppConstants.SELECTED_TODO) TodoElement selected;
	
	private String text;

	@PostConstruct
	void postContruct() throws Exception {
		this.text = "<html><body contentEditable=\"true\"><h1>Hello!</h1></body></html>";
	}
	
	public String getText() {
//		System.err.println("ViewModel");
		return this.text;
	}
	
}
