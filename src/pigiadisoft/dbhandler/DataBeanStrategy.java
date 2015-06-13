package pigiadisoft.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBeanStrategy {
	
	private Query select;
	
	public DataBeanStrategy(Query select){
		this.select=select;
	}
	
	public List<DataBean> getSelectedBeans(Connection c) throws SQLException{
		List<DataBean> toRet=new ArrayList<DataBean>();
		PreparedStatement ps=this.select.prepareStatement(c);
		ResultSet rs=this.select.executeQueryOnConnectionWithStatement(c, ps);
		while(rs.next()){
			toRet.add(this.buildDataBean(rs));
		}
		rs.close();
		ps.close();
		c.close();
		return this.lastOpBeforeReturning(toRet);
	}
	
	protected abstract List<DataBean> lastOpBeforeReturning(List<DataBean> list);
	
	protected abstract DataBean buildDataBean(ResultSet rs) throws SQLException;
	
}
