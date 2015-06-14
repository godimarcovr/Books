package pigiadisoft.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBeanStrategy {
	
	private SQLCode select;
	
	public DataBeanStrategy(SQLCode select){
		this.select=select;
	}
	
	public List<DataBean> getSelectedBeans(Connection c) throws SQLException{
		List<DataBean> toRet=new ArrayList<DataBean>();
		ResultSet rs=this.select.executeQueryOnConnection(c);
		while(rs.next()){
			toRet.add(this.buildDataBean(rs));
		}
		rs.getStatement().close();
		rs.close();
		c.close();
		return this.lastOpBeforeReturning(toRet);
	}
	
	//TODO aggiungere metodo per scrivere bean?
	
	protected abstract List<DataBean> lastOpBeforeReturning(List<DataBean> list);
	
	protected abstract DataBean buildDataBean(ResultSet rs) throws SQLException;
	
}
