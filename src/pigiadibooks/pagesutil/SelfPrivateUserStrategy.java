package pigiadibooks.pagesutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.model.PublicUserModel;

public class SelfPrivateUserStrategy extends DataBeanGetStrategy {

	private String ref_userstable;
	private String ref_fakepostable;
	private String ref_posuser;
	private String ref_username;
	private String username;
	private String ref_surname;
	private String ref_name;
	private String ref_email;
	private String ref_password;
	private String ref_x;
	private String ref_y;
	private String ref_role;

	public SelfPrivateUserStrategy(String username) {
		this.setDefaults();
		this.username=username;
		this.code=(Query) SQLCodeBuilder
				.createSelectAllFromWhere(this.ref_userstable+" U JOIN "+this.ref_fakepostable
						+" PGF ON PGF."+this.ref_posuser+"=U."+this.ref_username	
						, "U."+this.ref_username+"=?");
		this.code.addParam(this.username);
	}
	
	private void setDefaults(){
		this.ref_fakepostable="PosGeograficaFake";
		this.ref_username="username";
		this.ref_userstable="Users";
		this.ref_posuser="user_username";
		this.ref_name="nome";
		this.ref_surname="cognome";
		this.ref_x="x";
		this.ref_y="y";
		this.ref_email="email";
		this.ref_password="password";
		this.ref_role="ruolo";
	}
	
	@Override
	protected List<DataModel> lastOpBeforeReturning(List<DataModel> list) {
		return list;
	}

	@Override
	protected DataModel buildDataBean(ResultSet rs) throws SQLException {
		FakePosPrivateUserModel pum=new FakePosPrivateUserModel();
		pum.setUsername(rs.getString(this.ref_username));
		pum.setCognome(rs.getString(this.ref_surname));
		pum.setNome(rs.getString(this.ref_name));
		pum.setEmail(rs.getString(this.ref_email));
		pum.setPassword(rs.getString(this.ref_password));
		pum.setxPos(rs.getFloat(this.ref_x));
		pum.setyPos(rs.getFloat(this.ref_y));
		pum.setRuolo(rs.getString(this.ref_role));
		return pum;
	}

}
