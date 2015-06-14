package pigiadisoft.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DMLCode extends SQLCode {

	Object[][] parametersByLine;
	
	public DMLCode(String code, int lines, int values){
		super(code);
		parametersByLine=new Object[lines][values];
	}
	
	public void setParam(int riga, int colonna, Object value){
		this.parametersByLine[riga][colonna]=value;
	}
	
	@Override
	public PreparedStatement prepareStatement(Connection c) throws SQLException {
		//migliora performance per batch
		c.setAutoCommit(false);
		PreparedStatement toRet=c.prepareStatement(this.code);
		for(int i=0;i<this.parametersByLine.length;i++){
			for(int j=0;j<this.parametersByLine[0].length;j++){
				toRet.setObject(j+1, this.parametersByLine[i][j]);
			}
			toRet.addBatch();
		}
		return toRet;
	}

	@Override
	public ResultSet executeQueryOnConnection(Connection c) throws SQLException {
		PreparedStatement ps=this.prepareStatement(c);
		ps.executeBatch();
		c.commit();
		return null;
	}

}
