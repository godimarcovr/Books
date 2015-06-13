package pigiadisoft.dbhandler;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriver {
	
	private String user, password, url, driver;
	
	private static MyDriver instance=null;

	private MyDriver(String user, String password, String url, String driver) 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.user = user;
		this.password = password;
		this.url = url;
		this.driver = driver;
		Driver myDriver=(Driver)Class.forName(driver).newInstance();
		DriverManager.registerDriver(myDriver);
	}
	
	public static MyDriver getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		if(instance==null){
			//TODO read from cfg
			instance=new MyDriver("application","apensa1212"
					,"jdbc:postgresql://127.0.0.1/pgbooks","org.postgresql.Driver");
		}
		return instance;
	}
	
	public synchronized Connection getConnection() throws SQLException{
		return DriverManager.getConnection(this.url, this.user, this.password);
	}
	
	
}
