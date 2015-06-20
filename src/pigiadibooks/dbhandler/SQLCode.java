package pigiadibooks.dbhandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 
 * Classe astratta che rappresenta una query da eseguire
 * Si tratta in sostanza di un Command in cui setto tutte le istruzioni all'interno e posso eseguirla
 * quando voglio
 *
 */
public abstract class SQLCode {
	protected String code;
	
	/**
	 * setta la query desiderata
	 * 
	 * @param code SQL della query
	 */
	protected SQLCode(String code){
		this.code=code;
	}
	
	/**
	 * Costruisce uno statement sulla connessione specificata
	 * 
	 * @param c Connessione al DB specificata
	 * @return PreparedStatement relativo
	 * @throws SQLException
	 */
	
	public abstract PreparedStatement prepareStatement(Connection c) throws SQLException;
	
	/**
	 * Esegue query sulla connessione specificata
	 * 
	 * @param c Connessione al DB specificata
	 * @return ResultSet dell'eventuale risultato
	 * @throws SQLException
	 */
	public abstract ResultSet executeQueryOnConnection(Connection c) throws SQLException;

	
	/**
	 * Metodo Get
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	
	
	
}
