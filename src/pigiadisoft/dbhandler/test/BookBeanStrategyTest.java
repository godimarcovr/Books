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

public class BookBeanStrategyTest {

	@Test
	public void testDataBeanStrategy() {
		DataBeanStrategy dbs=new BookBeanStrategy();
		assertNotNull(dbs);
	}

	@Test
	public void testGetSelectedBeans() {
		DataBeanStrategy dbs=new BookBeanStrategy();
		try {
			Connection c=MyDriver.getInstance().getConnection();
			List<DataBean> ldb=dbs.getSelectedBeans(c);
			for (DataBean dataBean : ldb) {
				BookBean bb=(BookBean) dataBean;
				System.out.println(bb);
			}
			assertTrue(ldb.isEmpty());
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("ERROR");
		}
	}

}
