package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.model.DataModel;
import pigiadibooks.pagesutil.NearbyFakePublicUserModelStrategy;

public class NearbyFakePublicUserModelStrategyTest {

	@Test
	public void test() {
		
		try {
			NearbyFakePublicUserModelStrategy nfp=
					new NearbyFakePublicUserModelStrategy("pif", "9788828608936");
			List<DataModel> list=nfp
					.getSelectedBeans(MyDriver.getInstance().getConnection());
			for (DataModel dataModel : list) {
				System.out.println(dataModel);
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
