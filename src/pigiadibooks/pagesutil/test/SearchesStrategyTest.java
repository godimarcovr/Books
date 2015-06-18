package pigiadibooks.pagesutil.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookSearchModel;
import pigiadibooks.model.DataModel;
import pigiadibooks.pagesutil.SearchesStrategy;

public class SearchesStrategyTest {

	@Test
	public void test() {
		//AGGIUNGO RICERCHE A CASO
		SQLCode insert=SQLCodeBuilder.createInsertInto("ricerche", new String[]{"ricercachiave"}
										, new Object[][]{{"libro"},{"libro"}
														,{"ciao"},{"ciao"}
														,{"ciao"},{"ciao"}
														,{"ciao"},{"pif"}
														,{"pif"},{"pif"}
														,{"spagna"},{"francia"}
														,{"italia"}});
		try {
			insert.executeQueryOnConnection(MyDriver.getInstance().getConnection());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//uso la strategy
		DataBeanGetStrategy ss=new SearchesStrategy();
		List<DataModel> ldm;
		try {
			ldm = ss.getSelectedBeans(MyDriver.getInstance().getConnection());
			for (DataModel dm : ldm) {
				System.out.println(dm);
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
