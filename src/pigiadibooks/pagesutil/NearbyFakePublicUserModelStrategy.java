package pigiadibooks.pagesutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import pigiadibooks.dbhandler.DataBeanGetStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCode;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.PublicUserModel;

public class NearbyFakePublicUserModelStrategy extends DataBeanGetStrategy {

	private String ref_posuser;
	private String ref_username;
	private String ref_x;
	private String ref_y;
	private String ref_surname;
	private String ref_name;
	private String ref_owneruser;
	private String ref_ownedbook;
	
	private final float posX,posY;
	

	public NearbyFakePublicUserModelStrategy(String username,String bookID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.setDefaults();
		Query getFakeCoords=(Query) SQLCodeBuilder
				.createSelectAllFromWhere("PosGeograficaFake PGF"
						, "PGF."+this.ref_posuser+"='"+username+"' ");
		Connection c=MyDriver.getInstance().getConnection();
		ResultSet rs=getFakeCoords.executeQueryOnConnection(c);
		if(rs.next()){
			this.posX=rs.getFloat(this.ref_x);
			this.posY=rs.getFloat(this.ref_y);
		}else{
			this.posX=0.0f;
			this.posY=0.0f;
		}
		rs.getStatement().close();
		rs.close();
		c.close();
		this.buildQuery(bookID);
	}

	public NearbyFakePublicUserModelStrategy(float x, float y,String bookID) {
		this.posX=x;
		this.posY=y;
		this.setDefaults();
		this.buildQuery(bookID);
	}
	
	private void setDefaults() {
		this.ref_posuser="user_username";
		this.ref_owneruser="user_username";
		this.ref_username="username";
		this.ref_x="x";
		this.ref_y="y";
		this.ref_surname="cognome";
		this.ref_name="nome";
		this.ref_ownedbook="libro_industryid";
	}
	
	private void buildQuery(String bookID) {
		//select columns con distinct e values
		Query getNearby=(Query)SQLCodeBuilder
				.createSelectAllFromWhereOrderByWithParams("Users U JOIN LibroPosseduto LP "
						+ "ON U."+this.ref_username+"=LP."+this.ref_owneruser+" JOIN "
						+ "PosGeograficaFake PGF ON PGF."+this.ref_posuser+"=U."+this.ref_username
						, " LP."+this.ref_ownedbook+"='"+bookID+"'"
						, " sqrt(pow(PGF."+this.ref_x+"-?,2)"
								+ "+pow(PGF."+this.ref_y+"-?,2))"
						,new Object[]{this.posX,this.posY});
		this.code=getNearby;
	}
	
	@Override
	protected List<DataModel> lastOpBeforeReturning(List<DataModel> list) {
		return list;
	}

	@Override
	protected DataModel buildDataBean(ResultSet rs) throws SQLException {
		PublicUserModel pum=new PublicUserModel();
		pum.setUsername(rs.getString(this.ref_username));
		pum.setCognome(rs.getString(this.ref_surname));
		pum.setNome(rs.getString(this.ref_name));
		return pum;
	}

}
