package pigiadibooks.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Query extends SQLCode{
	private List<Object> parameters;
	
	public Query(String query){
		super(query);
		this.parameters=new LinkedList<Object>();
	}
	
	public void addParam(Object toAdd){
		this.parameters.add(toAdd);
	}

	@Override
	public PreparedStatement prepareStatement(Connection c) throws SQLException{
		PreparedStatement toRet=c.prepareStatement(this.code);
		for(int i=0;i<this.parameters.size();i++){
			toRet.setObject(i+1, this.parameters.get(i));
		}
		return toRet;
	}
	
	//non chiude lo statement!!
	@Override
	public ResultSet executeQueryOnConnection(Connection c) throws SQLException{
		PreparedStatement ps=this.prepareStatement(c);
		return ps.executeQuery();
	}
	
}
