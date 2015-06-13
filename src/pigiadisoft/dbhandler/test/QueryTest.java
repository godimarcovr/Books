package pigiadisoft.dbhandler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pigiadisoft.dbhandler.Query;
import pigiadisoft.dbhandler.QueryBuilder;

public class QueryTest {

	//TODO aggiungere test per le altre funzionalità
	
	@Test
	public void testQuery() {
		Query q=QueryBuilder.createSelectAllOrderBy("Libro", "isbn");
		assertNotNull(q);
	}

}
