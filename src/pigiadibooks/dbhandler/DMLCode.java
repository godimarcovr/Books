package pigiadibooks.dbhandler;

import java.beans.Statement;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DMLCode extends SQLCode {

	private Object[][] parametersByLine;
	private boolean ignoreWarnings = false;

	public DMLCode(String code, int lines, int values) {
		super(code);
		parametersByLine = new Object[lines][values];
	}

	public void setParam(int riga, int colonna, Object value) {
		this.parametersByLine[riga][colonna] = value;
	}

	public void setIgnoreWarnings(boolean b) {
		this.ignoreWarnings = b;
	}

	@Override
	public PreparedStatement prepareStatement(Connection c) throws SQLException {
		// migliora performance per batch
		c.setAutoCommit(false);
		PreparedStatement toRet = c.prepareStatement(this.code);
		for (int i = 0; i < this.parametersByLine.length; i++) {
			for (int j = 0; j < this.parametersByLine[0].length; j++) {
				toRet.setObject(j + 1, this.parametersByLine[i][j]);
			}
			toRet.addBatch();
		}
		return toRet;
	}

	
	//DA USARE SOLO SE CI SI ASPETTANO ERRORI DI VINCOLI
	private void executeSkipWarnings(Connection c) {
		// migliora performance per batch
		try {
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		PreparedStatement toRet=null;
		try {
			toRet = c.prepareStatement(this.code);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < this.parametersByLine.length; i++) {
			for (int j = 0; j < this.parametersByLine[0].length; j++) {
				try {
					toRet.setObject(j + 1, this.parametersByLine[i][j]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				toRet.execute();
			} catch (SQLException e) {
				//assorbo
			}
			try {
				c.commit();
			} catch (SQLException e) {
				//assorbo
			}
		}
	}

	@Override
	public ResultSet executeQueryOnConnection(Connection c) throws SQLException {
		if (!this.ignoreWarnings) {
			PreparedStatement ps = this.prepareStatement(c);
			ps.executeBatch();
			c.commit();
		} else {
			this.executeSkipWarnings(c);
		}
		return null;
	}

}
