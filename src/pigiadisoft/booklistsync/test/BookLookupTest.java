package pigiadisoft.booklistsync.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadisoft.booklistsync.BookBean;
import pigiadisoft.booklistsync.BookLookup;

public class BookLookupTest {

	@Test
	public void test() {
		BookLookup bl1 = new BookLookup("sole");
		try {
			List<BookBean> res1=bl1.lookupByTitle();
			BookLookup bl2 = new BookLookup("sole");
			List<BookBean> res2=bl2.lookupByTitle();
			assertEquals(res1, res2);
			for (BookBean bookBean : res2) {
				System.out.println(bookBean);
			}
			System.out.println("********************************************");
			for (BookBean bookBean : res1) {
				System.out.println(bookBean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
