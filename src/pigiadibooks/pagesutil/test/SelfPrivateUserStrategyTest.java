package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.model.DataModel;
import pigiadibooks.pagesutil.SelfPrivateUserStrategy;

public class SelfPrivateUserStrategyTest {

	@Test
	public void test() {
		//suppongo che esista user "god"
		SelfPrivateUserStrategy spus=new SelfPrivateUserStrategy("god");
		try {
			List<DataModel> userList= spus.getSelectedBeans(MyDriver.getInstance().getConnection());
			assertTrue(!userList.isEmpty());
			System.out.println(userList.get(0));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
