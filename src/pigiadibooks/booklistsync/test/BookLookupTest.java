package pigiadibooks.booklistsync.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.booklistsync.BookLookup;
import pigiadibooks.model.BookModel;

public class BookLookupTest {

	@Test
	public void test() {
		BookLookup bl1 = new BookLookup("Narnia");
		try {
			//List<BookModel> res1=bl1.lookupByTitle();
			BookLookup bl2 = new BookLookup("cronache narnia");
			List<BookModel> res2=bl2.lookupByTitle();
			//assertEquals(res1, res2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(e instanceof SQLException){
				SQLException ex=(SQLException) e;
				while(ex instanceof SQLException){
					System.out.println(ex);
					ex=ex.getNextException();
				}
			}
			e.printStackTrace();
		}
	}

}
