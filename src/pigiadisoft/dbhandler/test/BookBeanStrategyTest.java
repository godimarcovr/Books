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
import pigiadisoft.dbhandler.SQLCode;
import pigiadisoft.dbhandler.SQLCodeBuilder;

public class BookBeanStrategyTest {

	@Test
	public void testDataBeanStrategy() {
		DataBeanStrategy dbs=new BookBeanStrategy();
		assertNotNull(dbs);
	}

	@Test
	public void testGetSelectedBeans() {

		String autore="cicciopasticcio";
		String titolo="asdasdasd";
		String isbn="1234561234561";
		
		SQLCode deleteWrittenBy=SQLCodeBuilder
				.createDeleteFromOnlyEquals("ScrittoDa", new String[]{"libro_isbn","autore_nome"}
						, new Object[][]{{isbn,autore}});
		SQLCode deleteBook=SQLCodeBuilder
				.createDeleteFromOnlyEquals("Libro", new String[]{"isbn"}
				, new String[][]{{isbn}});
		SQLCode deleteAuthor=SQLCodeBuilder
				.createDeleteFromOnlyEquals("Autore", new String[]{"nome"}
				, new String[][]{{autore}});
		
		
		SQLCode insertBook=SQLCodeBuilder
				.createInsertIntoOnAllColumns("Libro"
						, new Object[][]{{isbn,titolo}});
		SQLCode insertAuthor=SQLCodeBuilder
				.createInsertIntoOnAllColumns("Autore"
						, new Object[][]{{autore}});
		SQLCode insertWrittenBy=SQLCodeBuilder
				.createInsertIntoOnAllColumns("ScrittoDa"
						, new Object[][]{{isbn,autore}});
		
		try {
			assertNull(deleteWrittenBy.executeQueryOnConnection(MyDriver.getInstance().getConnection()));
			assertNull(deleteBook.executeQueryOnConnection(MyDriver.getInstance().getConnection()));
			assertNull(deleteAuthor.executeQueryOnConnection(MyDriver.getInstance().getConnection()));
			
			assertNull(insertBook.executeQueryOnConnection(MyDriver.getInstance().getConnection()));
			assertNull(insertAuthor.executeQueryOnConnection(MyDriver.getInstance().getConnection()));
			assertNull(insertWrittenBy.executeQueryOnConnection(MyDriver.getInstance().getConnection()));
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataBeanStrategy dbs=new BookBeanStrategy();
		try {
			Connection c=MyDriver.getInstance().getConnection();
			List<DataBean> ldb=dbs.getSelectedBeans(c);
			boolean found=false;
			for (DataBean dataBean : ldb) {
				BookBean bb=(BookBean) dataBean;
				if(bb.getIsbn_13().equals("1234561234561")){
					found=true;
					System.out.println(bb);
				}
				
			}
			assertTrue(found);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ERROR");
		}
	}

}
