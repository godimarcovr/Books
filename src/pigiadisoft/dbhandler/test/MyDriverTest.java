package pigiadisoft.dbhandler.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import pigiadisoft.dbhandler.MyDriver;

public class MyDriverTest {

	@Test
	public void testGetInstance() {
		try {
			assertNotNull(MyDriver.getInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetConnection() {
		MyDriver md=null;
		try {
			md = MyDriver.getInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(md);
		Connection c=null;
		try {
			c = md.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(c);
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
