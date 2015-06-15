package pigiadibooks.booklistsync.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.booklistsync.BookBeanStrategy;
import pigiadibooks.dbhandler.DataBeanStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.DataModel;

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
		String industryid="1234561234561";
		
		SQLCode deleteWrittenBy=SQLCodeBuilder
				.createDeleteFromOnlyEquals("ScrittoDa", new String[]{"libro_industryid","autore_nome"}
						, new Object[][]{{industryid,autore}});
		SQLCode deleteBook=SQLCodeBuilder
				.createDeleteFromOnlyEquals("Libro", new String[]{"industryid"}
				, new String[][]{{industryid}});
		SQLCode deleteAuthor=SQLCodeBuilder
				.createDeleteFromOnlyEquals("Autore", new String[]{"nome"}
				, new String[][]{{autore}});
		
		
		SQLCode insertBook=SQLCodeBuilder
				.createInsertIntoOnAllColumns("Libro"
						, new Object[][]{{industryid,titolo}});
		SQLCode insertAuthor=SQLCodeBuilder
				.createInsertIntoOnAllColumns("Autore"
						, new Object[][]{{autore}});
		SQLCode insertWrittenBy=SQLCodeBuilder
				.createInsertIntoOnAllColumns("ScrittoDa"
						, new Object[][]{{industryid,autore}});
		
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
			List<DataModel> ldb=dbs.getSelectedBeans(c);
			boolean found=false;
			for (DataModel dataBean : ldb) {
				BookModel bb=(BookModel) dataBean;
				if(bb.getindustryID().equals("1234561234561")){
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
