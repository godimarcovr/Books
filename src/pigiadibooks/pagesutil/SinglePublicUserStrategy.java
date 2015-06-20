package pigiadibooks.pagesutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.PublicUserModel;

public class SinglePublicUserStrategy extends DataBeanGetStrategy {

	private String ref_username;
	private String ref_cognome;
	private String ref_nome;

	public SinglePublicUserStrategy(String username) {
		this.code=(Query) SQLCodeBuilder.createSelectAllFromWhere("Users U", "U.username=?");
		this.code.addParam(username);
		this.setDefaults();
	}
	
	private void setDefaults(){
		this.ref_cognome="cognome";
		this.ref_nome="nome";
		this.ref_username="username";
	}
	
	@Override
	protected List<DataModel> lastOpBeforeReturning(List<DataModel> list) {
		return list;
	}

	@Override
	protected DataModel buildDataBean(ResultSet rs) throws SQLException {
		PublicUserModel pum=new PublicUserModel();
		pum.setUsername(rs.getString(this.ref_username));
		pum.setCognome(rs.getString(this.ref_cognome));
		pum.setNome(rs.getString(this.ref_nome));
		return pum;
	}

}
