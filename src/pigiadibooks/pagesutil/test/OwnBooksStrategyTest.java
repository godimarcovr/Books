package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.OwnBookModel;
import pigiadibooks.pagesutil.OwnBooksStrategy;
import pigiadibooks.pagesutil.OwnedBooks;

public class OwnBooksStrategyTest {

	@Test
	public void test() {
		OwnedBooks ob= new OwnedBooks("pif");
		try {
			ob.insertBook("9788865261965");
			ob.insertBook("9788856617429");
			ob.updateBook("9788856617429", 3, 5, "Meh");
			OwnBooksStrategy obs=new OwnBooksStrategy("pif");
			for (DataModel dm : obs.getSelectedBeans(MyDriver.getInstance().getConnection())) {
				OwnBookModel obm=(OwnBookModel) dm;
				System.out.println(obm);
			}
			ob.deleteBook("9788856617429");
			ob.deleteBook("9788865261965");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
