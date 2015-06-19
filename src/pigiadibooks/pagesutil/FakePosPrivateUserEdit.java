package pigiadibooks.pagesutil;

import java.sql.SQLException;

import pigiadibooks.dbhandler.DMLCode;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.FakePosPrivateUserModel;

public class FakePosPrivateUserEdit {
	
	private final String user;
	private SelfPrivateUserStrategy userStrat;
	private String ref_userstable;
	private String ref_email;
	private String ref_username;
	private String ref_postable;
	private String ref_x;
	private String ref_y;
	private String ref_posuser;
	
	public FakePosPrivateUserEdit(String username){
		this.setDefaults();
		this.user=username;
		this.userStrat=new SelfPrivateUserStrategy(this.user);
	}
	
	private void setDefaults() {
		this.ref_userstable="Users";
		this.ref_postable="PosGeograficaFake";
		this.ref_email="email";
		this.ref_username="username";
		this.ref_x="x";
		this.ref_y="y";
		this.ref_posuser="user_username";
	}
	
	public FakePosPrivateUserModel getUserInfo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return (FakePosPrivateUserModel) this.userStrat.getSelectedBeans(MyDriver.getInstance().getConnection()).get(0);
	}
	
	public void updateInfo(String email,float x, float y) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		DMLCode updateUsertable=(DMLCode) SQLCodeBuilder.createUpdate(this.ref_userstable
				, new String[]{this.ref_email}
				, new Object[]{email}
				, this.ref_username+"='"+this.user+"'");
		DMLCode updateFakepostable=(DMLCode) SQLCodeBuilder.createUpdate(this.ref_postable
				, new String[]{this.ref_x,this.ref_y}
				, new Object[]{x,y}
				, this.ref_posuser+"='"+this.user+"'");
		updateUsertable.executeQueryOnConnection(MyDriver.getInstance().getConnection());
		updateFakepostable.executeQueryOnConnection(MyDriver.getInstance().getConnection());
	}
	
	//considera lo username del costruttore!!
	public void insertUser(FakePosPrivateUserModel fppum) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		DMLCode insertUser=(DMLCode) SQLCodeBuilder
				.createInsertIntoOnAllColumns(this.ref_userstable
						, new Object[][]{{this.user,fppum.getPassword(),fppum.getEmail()
										,fppum.getNome(),fppum.getCognome(),fppum.getRuolo()}});
		DMLCode insertPos=(DMLCode) SQLCodeBuilder
				.createInsertIntoOnAllColumns(this.ref_postable
						, new Object[][]{{this.user,fppum.getxPos(),fppum.getyPos()}});
		insertUser.executeQueryOnConnection(MyDriver.getInstance().getConnection());
		insertPos.executeQueryOnConnection(MyDriver.getInstance().getConnection());
	}
}
