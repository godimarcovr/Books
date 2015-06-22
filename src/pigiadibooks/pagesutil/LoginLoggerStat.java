package pigiadibooks.pagesutil;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import pigiadibooks.dbhandler.DMLCode;
import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.LoginRecordModel;

public class LoginLoggerStat implements Observer{
	
	private DataBeanGetStrategy lrstrat;
	private String ref_logintable;

	public LoginLoggerStat(){
		this.setDefaults();
		this.lrstrat=new LoginRecordStrategy();
	}

	private void setDefaults() {
		this.ref_logintable="LoginRecord";
	}
	
	static String[] province=new String[]{"VR","MI","RM","PD","TO","FI","RN","VE","VI","PA"};
	
	public void insertLoginWithRandomProvincia(String user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		this.insertLogin(user, province[Math.abs((new Random()).nextInt()%10)]);
	}
	
	public void insertLogin(String user, String prov) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		DMLCode insert=new DMLCode("INSERT INTO "+this.ref_logintable
										+" VALUES (?,localtimestamp,?);"
								, 1
								, 2);
		insert.setParam(0, 0, user);
		insert.setParam(0, 1, prov);
		insert.executeQueryOnConnection(MyDriver.getInstance().getConnection());
	}
	
	public LoginRecordModel getLastLogin() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		List<DataModel> ldm=this.lrstrat.getSelectedBeans(MyDriver.getInstance().getConnection());
		return ldm.isEmpty() ? null : (LoginRecordModel)ldm.get(0) ;
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			this.insertLoginWithRandomProvincia((String) arg1);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("ERRORE NON HO AGGIUNTO IL LOGIN AL LOG DEI LOGIN");
		}
		
	}
}
