package pigiadibooks.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pigiadibooks.model.DataModel;

public abstract class DataBeanStrategy {
	
	private SQLCode code;
	
	public DataBeanStrategy(SQLCode select){
		this.code=select;
	}
	
	public List<DataModel> getSelectedBeans(Connection c) throws SQLException{
		List<DataModel> toRet=new ArrayList<DataModel>();
		ResultSet rs=this.code.executeQueryOnConnection(c);
		while(rs.next()){
			toRet.add(this.buildDataBean(rs));
		}
		rs.getStatement().close();
		rs.close();
		c.close();
		return this.lastOpBeforeReturning(toRet);
	}
	
	//TODO aggiungere metodo per scrivere bean?
	
	protected abstract List<DataModel> lastOpBeforeReturning(List<DataModel> list);
	
	protected abstract DataModel buildDataBean(ResultSet rs) throws SQLException;
	
}
