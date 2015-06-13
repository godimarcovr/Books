package pigiadisoft.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Query {
	private String query;
	private List<Object> parameters;
	private boolean isResultSetExpected;
	
	public Query(String query){
		this.query=query;
		this.parameters=new LinkedList<Object>();
		this.isResultSetExpected=this.query.toLowerCase().contains("select")?true:false;
	}
	
	public void addParam(Object toAdd){
		this.parameters.add(toAdd);
	}
	
	public void setResultSetExpected(boolean isResultSetExpected) {
		this.isResultSetExpected = isResultSetExpected;
	}

	public PreparedStatement prepareStatement(Connection c) throws SQLException{
		PreparedStatement toRet=c.prepareStatement(this.query);
		for(int i=0;i<this.parameters.size();i++){
			toRet.setObject(i+1, this.parameters.get(i));
		}
		return toRet;
	}
	
	//non chiude lo statement!!
	public ResultSet executeQueryOnConnection(Connection c) throws SQLException{
		PreparedStatement ps=this.prepareStatement(c);
		if(this.isResultSetExpected){
			return ps.executeQuery();
		}
		else{
			ps.executeUpdate();
			return null;
		}
	}
	
	public ResultSet executeQueryOnConnectionWithStatement(Connection c, PreparedStatement ps) 
			throws SQLException{
		if(this.isResultSetExpected){
			return ps.executeQuery();
		}
		else{
			ps.executeUpdate();
			return null;
		}
	}
	
}
