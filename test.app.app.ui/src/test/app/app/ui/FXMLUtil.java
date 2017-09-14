package test.app.app.ui;

import java.net.URL;
import org.eclipse.core.runtime.FileLocator;

public class FXMLUtil {
	public static URL getPlatformUrl(String pluginId, String path){
		if (!path.startsWith("/")) //$NON-NLS-1$
			path = "/" + path; //$NON-NLS-1$
		try {
			URL url = new URL("platform:/plugin/" + pluginId + path);
			return FileLocator.resolve(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
}

