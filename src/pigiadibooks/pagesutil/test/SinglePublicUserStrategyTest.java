package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.model.PublicUserModel;
import pigiadibooks.pagesutil.FakePosPrivateUserEdit;
import pigiadibooks.pagesutil.SinglePublicUserStrategy;

public class SinglePublicUserStrategyTest {

	@Test
	public void test() {
		FakePosPrivateUserEdit fppue=new FakePosPrivateUserEdit("prova12345");
		try {
			fppue.insertUser(
					new FakePosPrivateUserModel("prova12345", "a", "b", "c", "dangoo", 0.0f, 0.0f, "user"));
			SinglePublicUserStrategy single=new SinglePublicUserStrategy("prova12345");
			List<DataModel> l=single.getSelectedBeans(MyDriver.getInstance().getConnection());
			if(l.size()!=1) fail();
			assertEquals("prova12345",((PublicUserModel)l.get(0)).getUsername());
			SQLCode code=SQLCodeBuilder.createDeleteFromOnlyEquals("Users U", new String[]{"U.username"}
										, new Object[][]{{"prova12345"}});
			//code.executeQueryOnConnection(MyDriver.getInstance().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof SQLException){
				SQLException ex=(SQLException) e;
				while(ex instanceof SQLException){
					System.out.println(ex);
					ex=ex.getNextException();
				}
			}
		}
	}

}
