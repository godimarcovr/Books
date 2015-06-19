package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import pigiadibooks.model.BookModel;
import pigiadibooks.pagesutil.BookLookup;

public class BookLookupTest {

	@Test
	public void test() {
		String search="potter";
		BookLookup bl1 = new BookLookup(search);
		try {
			List<BookModel> res1=bl1.lookupByTitle();
			BookLookup bl2 = new BookLookup(search);
			List<BookModel> res2=bl2.lookupByTitle();
			Set<BookModel> set1=new HashSet<BookModel>();
			Set<BookModel> set2=new HashSet<BookModel>();
			set1.addAll(res1);
			set2.addAll(res2);
			assertEquals(set1, set2);
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
