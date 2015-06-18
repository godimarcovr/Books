package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.model.BookSearchModel;
import pigiadibooks.pagesutil.ResearchLogger;

public class ResearchLoggerTest {

	@Test
	public void test() {
		//INSERISCO RICERCHE
		ResearchLogger rl=new ResearchLogger();
		try {
			rl.insertSearch("banana");
			rl.insertSearch("banana");
			rl.insertSearch("banana");
			rl.insertSearch("mela");
			rl.insertSearch("mela");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//in fondo senza errori
		assertTrue(true);
		//PRENDO LE STATISTICHE
		
		try {
			List<BookSearchModel> bsml=rl.getTop10Searches();
			assertTrue(!bsml.isEmpty());
			for (BookSearchModel bsm : rl.getTop10Searches()) {
				System.out.println(bsm);
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//in fondo senza errori
		assertTrue(true);
		
	}

}
