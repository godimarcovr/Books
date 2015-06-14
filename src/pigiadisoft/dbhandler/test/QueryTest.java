package pigiadisoft.dbhandler.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadisoft.booklistsync.BookBean;
import pigiadisoft.dbhandler.BookBeanStrategy;
import pigiadisoft.dbhandler.DataBean;
import pigiadisoft.dbhandler.DataBeanStrategy;
import pigiadisoft.dbhandler.MyDriver;
import pigiadisoft.dbhandler.Query;
import pigiadisoft.dbhandler.SQLCode;
import pigiadisoft.dbhandler.SQLCodeBuilder;

public class QueryTest {

	//TODO aggiungere test per le altre funzionalità
	
	@Test
	public void testQuery() {
		SQLCode q=SQLCodeBuilder.createSelectAllFromOrderBy("Libro", "isbn");
		assertNotNull(q);
	}
	
	@Test
	public void testInsertAndRead() {
		assert(true);
	}

}
