package pigiadibooks.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Rappresenta una query (una Select)
 *
 */

public class Query extends SQLCode{
	private List<Object> parameters;
	
	public Query(String query){
		super(query);
		this.parameters=new LinkedList<Object>();
	}
	
	/**
	 * Aggiunge un parametro che verrà settato sul PreparedStatement
	 * @param toAdd Parametro da aggiungere
	 */
	public void addParam(Object toAdd){
		this.parameters.add(toAdd);
	}

	/**
	 * Costruisce il PreparedStatement e setta tutti i parametri specificati
	 * 
	 */
	@Override
	public PreparedStatement prepareStatement(Connection c) throws SQLException{
		PreparedStatement toRet=c.prepareStatement(this.code);
		for(int i=0;i<this.parameters.size();i++){
			toRet.setObject(i+1, this.parameters.get(i));
		}
		return toRet;
	}
	
	@Override
	public ResultSet executeQueryOnConnection(Connection c) throws SQLException{
		PreparedStatement ps=this.prepareStatement(c);
		return ps.executeQuery();
	}
	
}
