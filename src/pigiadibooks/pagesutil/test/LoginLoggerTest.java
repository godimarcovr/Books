package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import pigiadibooks.model.LoginRecordModel;
import pigiadibooks.pagesutil.LoginLogger;

public class LoginLoggerTest {

	@Test
	public void test() {
		LoginLogger ll=new LoginLogger();
		try {
			ll.insertLogin("god", "VR");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		try {
			LoginRecordModel lrm=ll.getLastLogin();
			assertTrue(lrm.getUser().equals("god") && lrm.getProvincia().equals("VR"));
			System.out.println(lrm);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}

}
