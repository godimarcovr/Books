package pigiadisoft.booklistsync;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import pigiadisoft.model.BookModel;

public abstract class ExternalBookIndex {
	
	public abstract List<BookModel> getBooksByTitle(String title) throws GeneralSecurityException, IOException;
	
}
