package pigiadibooks.pagesutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.LoginRecordModel;

public class LoginRecordStrategy extends DataBeanGetStrategy {

	private String ref_loginrecordtable;
	private String ref_timestamp;
	private String ref_provincia;
	private String ref_userlogged;

	//cambiare la query e usare il max
	public LoginRecordStrategy() {
		this.setDefaults();
		this.code=(Query) SQLCodeBuilder
				.createSelectAllFromOrderBy(this.ref_loginrecordtable
						, this.ref_timestamp+" DESC");
	}
	
	private void setDefaults() {
		this.ref_loginrecordtable="LoginRecord";
		this.ref_timestamp="logintime";
		this.ref_provincia="provincia";
		this.ref_userlogged="user_username";
	}

	@Override
	protected List<DataModel> lastOpBeforeReturning(List<DataModel> list) {
		List<DataModel> ldm=new ArrayList<DataModel>(1);
		if(list.size()!=0){
			ldm.add(list.get(0));
		}
		return ldm;
	}

	@Override
	protected DataModel buildDataBean(ResultSet rs) throws SQLException {
		LoginRecordModel lrm=new LoginRecordModel();
		lrm.setLogintime(rs.getTimestamp(this.ref_timestamp));
		lrm.setProvincia(rs.getString(this.ref_provincia));
		lrm.setUser(rs.getString(this.ref_userlogged));
		return lrm;
	}

}
