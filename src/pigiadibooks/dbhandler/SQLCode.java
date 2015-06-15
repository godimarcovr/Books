package pigiadibooks.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SQLCode {
	protected String code;
	
	protected SQLCode(String code){
		this.code=code;
	}
	
	public abstract PreparedStatement prepareStatement(Connection c) throws SQLException;
	
	public abstract ResultSet executeQueryOnConnection(Connection c) throws SQLException;
	
	
	
}
