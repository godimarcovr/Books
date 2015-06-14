package pigiadisoft.booklistsync;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public abstract class ExternalBookIndex {
	
	public abstract List<BookBean> getBooksByTitle(String title) throws GeneralSecurityException, IOException;
	
}
