package pigiadibooks.dbhandler;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pigiadibooks.model.DataModel;


/**
 * Superclasse comune per tutte le Query che devono estrarre dal DB delle tuple e costruirne
 * i relativi DataBean
 * 
 * @author Marco
 *
 */
public abstract class DataBeanGetStrategy implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Query code;
	
	/**
	 * Esegue la query specificata dalla sottoclasse e per ogni riga del result set costruisce il DataBean
	 * e lo aggiunge ad una lista
	 * 
	 * @param c
	 * @return
	 * @throws SQLException
	 */
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
	
	
	/**
	 * Eventuali operazioni in fondo per possibili elaborazioni aggiuntive
	 * 
	 * @param list
	 * @return
	 */
	protected abstract List<DataModel> lastOpBeforeReturning(List<DataModel> list);
	
	
	/**
	 * Data una riga del ResultSet costruisce il DataBean specificato
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected abstract DataModel buildDataBean(ResultSet rs) throws SQLException;
	
}
