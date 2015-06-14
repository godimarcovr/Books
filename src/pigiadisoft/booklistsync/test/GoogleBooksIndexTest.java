package pigiadisoft.booklistsync.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import pigiadisoft.booklistsync.BookBean;
import pigiadisoft.booklistsync.ExternalBookIndex;
import pigiadisoft.booklistsync.GoogleBooksIndex;

public class GoogleBooksIndexTest {

	@Test
	public void testGetBooksByTitle() {
		ExternalBookIndex ind=new GoogleBooksIndex();
		try {
			List<BookBean> bblist=ind.getBooksByTitle("follia sette clan");
			assertFalse(bblist.isEmpty());
			for (BookBean bookBean : bblist) {
				System.out.println(bookBean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
