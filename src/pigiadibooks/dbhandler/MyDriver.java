package pigiadibooks.dbhandler;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che gestisce l'accesso al DB
 * 
 * Si tratta di un Singleton per far si che vi sia un unico punto di accesso
 */

public class MyDriver {
	
	private String user, password, url, driver;
	
	private static MyDriver instance=null;

	
	/**
	 * Crea un Driver con i parametri specificati
	 * 
	 * @param user nome utente
	 * @param password password
	 * @param url url del database
	 * @param driver JDBC da usare
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private MyDriver(String user, String password, String url, String driver) 
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.user = user;
		this.password = password;
		this.url = url;
		this.driver = driver;
		Driver myDriver=(Driver)Class.forName(driver).newInstance();
		DriverManager.registerDriver(myDriver);
	}
	/**
	 * Inizializza in maniera lazy l'istanza di questa classe e ritorna sempre la stessa
	 * 
	 * @return Istanza unica di questa classe
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static MyDriver getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		if(instance==null){
			//TODO read from cfg
			instance=new MyDriver("application","apensa1212"
					,"jdbc:postgresql://127.0.0.1/pgbooks","org.postgresql.Driver");
			/*instance=new MyDriver("nwutkaybrbduxx","pHCTbBsZvM7r6G6B5Es-213ZLP"
					,"jdbc:postgresql://ec2-54-83-43-118.compute-1.amazonaws.com:5432"
							+ "/d7jq362bl68cii?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"
					,"org.postgresql.Driver");*/
		}
		return instance;
	}
	
	/**
	 * Ritorna una nuova connessione al DB. La chiusura sarà gestita dall'utente.
	 * È sincronizzato per evitare problemi di multithreading
	 * 
	 * @return una Connection
	 * @throws SQLException
	 */
	
	public synchronized Connection getConnection() throws SQLException{
		return DriverManager.getConnection(this.url, this.user, this.password);
	}
	
	
}
