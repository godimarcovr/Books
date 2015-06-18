package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.pagesutil.LoginRecordStrategy;

public class LoginRecordStrategyTest {

	@Test
	public void test() {
		DataBeanGetStrategy login=new LoginRecordStrategy();
		try {
			System.out.println(login.getSelectedBeans(MyDriver.getInstance().getConnection()));
			assertTrue(true);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}
