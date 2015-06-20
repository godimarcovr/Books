package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.model.PublicUserModel;
import pigiadibooks.pagesutil.NearbyFakePublicUserModelStrategy;

public class NearbyFakePublicUserModelStrategyTest {

	//@Test
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
	@Test
	public void test2(){
		List<PublicUserModel> results;
		try {
			NearbyFakePublicUserModelStrategy nearby=
					new NearbyFakePublicUserModelStrategy("god", "prova", 10.0f);
			List<DataModel> l=nearby.getSelectedBeans(MyDriver.getInstance().getConnection());
			results=new ArrayList<PublicUserModel>(l.size());
			for (DataModel dataModel : l) {
				results.add((PublicUserModel)dataModel);
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			results = new ArrayList<PublicUserModel>(0);
		}
		//System.out.println(results);
	}

}
