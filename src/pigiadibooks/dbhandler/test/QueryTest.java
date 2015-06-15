package pigiadibooks.dbhandler.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.booklistsync.BookBeanStrategy;
import pigiadibooks.dbhandler.DataBeanStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.DataModel;

public class QueryTest {

	//TODO aggiungere test per le altre funzionalità
	
	@Test
	public void testQuery() {
		SQLCode q=SQLCodeBuilder.createSelectAllFromOrderBy("Libro", "industryid");
		assertNotNull(q);
	}
	
	@Test
	public void testInsertAndRead() {
		assert(true);
	}

}
