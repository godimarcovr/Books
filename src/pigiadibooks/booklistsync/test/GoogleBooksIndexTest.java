package pigiadibooks.booklistsync.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import pigiadibooks.booklistsync.ExternalBookIndex;
import pigiadibooks.booklistsync.GoogleBooksIndex;
import pigiadibooks.model.BookModel;

public class GoogleBooksIndexTest {

	@Test
	public void testGetBooksByTitle() {
		ExternalBookIndex ind=new GoogleBooksIndex();
		try {
			List<BookModel> bblist=ind.getBooksByTitle("sawyer");
			assertFalse(bblist.isEmpty());
			for (BookModel bookBean : bblist) {
				System.out.println(bookBean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
