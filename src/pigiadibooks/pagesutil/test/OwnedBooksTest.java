package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.OwnBookModel;
import pigiadibooks.pagesutil.BookLookup;
import pigiadibooks.pagesutil.OwnedBooks;

public class OwnedBooksTest {

	@Test
	public void test() {
		// TEST
		// creo un utente
		// scelgo un libroesistente da db
		// gli faccio possedere quel libro
		// aggiungo una recensione e un voto
		// cancello il possedimento
		try {
			// creo un utente
			double random = Math.random();
			String username = Double.toString(random);
			username = username.substring(2, username.length());
			SQLCode createuser = SQLCodeBuilder.createInsertInto("users",
					new String[] { "username", "password", "email" },
					new Object[][] { { username, "apensa1212",
							"bot" + username + "@pgbooks.it" } });


			// scelgo un libroesistente da db
			createuser.executeQueryOnConnection(MyDriver.getInstance()
					.getConnection());
			BookLookup bl = new BookLookup("narnia");
			BookModel toOwn = bl.lookupByTitle().get(0);
			

			// gli faccio possedere quel libro
			OwnedBooks own=new OwnedBooks(username);
			own.insertBook(toOwn.getindustryID());
			
			//controllo di possedere questo libro
			List<OwnBookModel> beforeUpdate=own.selectOwnBooks();
			boolean found = false;
			for (OwnBookModel obm : beforeUpdate) {
				if(obm.getIndustryID().equals(toOwn.getindustryID())) found=true;
			}
			assertTrue(found);
			

			// aggiungo una recensione e un voto
			own.updateBook(toOwn.getindustryID(), 4, 8, "Molto Bello!");
			
			//controllo di possederlo e che abbia una recensione
			List<OwnBookModel> afterUpdate=own.selectOwnBooks();
			found = false;
			for (OwnBookModel obm : afterUpdate) {
				if(obm.getIndustryID().equals(toOwn.getindustryID())){
					found=true;
					assertNotNull(obm.getRecensione());
				}
			}
			assertTrue(found);
			
			// cancello il possedimento
			own.deleteBook(toOwn.getindustryID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

}
