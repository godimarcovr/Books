package pigiadisoft.booklistsync;

import java.util.List;

public abstract class ExternalBookIndex {
	
	public abstract List<BookBean> getBooksByTitle(String title);
	
}
