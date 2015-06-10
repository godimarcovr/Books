package database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import user.*;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DataSource implements Serializable {

	// === Properties
	// ============================================================

	// dati di identificazione dell'utente (da personalizzare)

	/*
	 * private String user = "userlab28"; private String passwd = "ventottoKJ";
	 * 
	 * // URL per la connessione alla base di dati e' formato dai seguenti //
	 * componenti: <protocollo>://<host del server>/<nome base di dati>. private
	 * String url = "jdbc:postgresql://dbserver.scienze.univr.it/dblab28";
	 * 
	 * // Driver da utilizzare per la connessione e l'esecuzione delle query.
	 * private String driver = "org.postgresql.Driver";
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user = "cgrmcjkiernsld";
	private String passwd = "2kuEVzBdkZOKYiWg-p4p7wZNfR";

	// URL per la connessione alla base di dati e' formato dai seguenti
	// componenti: <protocollo>://<host del server>/<nome base di dati>.
	private String url = "jdbc:postgresql://ec2-54-217-202-108.eu-west-1.compute.amazonaws.com:5432/d50obhvuof5kka?&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

	// Driver da utilizzare per la connessione e l'esecuzione delle query.
	private String driver = "org.postgresql.Driver";

	/**
	 * Costruttore della classe. Carica i driver da utilizzare per la
	 * connessione alla base di dati.
	 *
	 * @throws ClassNotFoundException
	 *             Eccezione generata nel caso in cui i driver per la
	 *             connessione non siano trovati nel CLASSPATH.
	 */
	public DataSource() throws ClassNotFoundException {
		Class.forName(driver);
	}

	// -- definizione delle query
	// ------------------------------------------------

	// recupera tutte le informazioni di un particolare corso di studi
	private String listaVendita = "SELECT * "
			+ "FROM Immobile I join Tentata_vendita V on I.codice=V.immobile "
			+ "WHERE V.data_fine>?";
	
	/**
	 * Metodo per il recupero delle principali informazioni di tutti i corsi di
	 * studi
	 *
	 * @return
	 */
	public List<Vendita> getListaVendita() {
		
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Vendita> result = new ArrayList<Vendita>();

		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("connesso");
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(listaVendita);
			pstmt.clearParameters();
			// imposto i parametri della query
			pstmt.setDate(1, Date.valueOf(LocalDate.now()));
			// eseguo la query
			rs = pstmt.executeQuery();
			// eseguo l'interrogazione desiderata
			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				result.add(makeVenditaBean(rs));
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

	private Vendita makeVenditaBean(ResultSet rs) throws SQLException {
		Vendita bean = new Vendita(rs.getInt("id_tentata_vendita"),
				rs.getString("classtype"), rs.getString("indirizzo"),
				rs.getString("citta"),
				rs.getInt("superficie"), rs.getInt("numero_vani"),
				rs.getString("descrizione"), rs.getInt("superficie_giardino"),
				rs.getInt("piano"), rs.getDate("data_inizio"),
				rs.getDate("data_fine"), rs.getInt("prezzo_minimo"));
		return bean;
	}

	private String login = "SELECT cod_fisc " + "FROM Cliente C "
			+ "WHERE login LIKE ? AND password LIKE ?";

	public boolean login(String user1, String psw1) { // dichiarazione delle
														// variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean logged = false;

		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("connesso");
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(login);
			pstmt.clearParameters();
			// imposto i parametri della query
			pstmt.setString(1, user1);
			pstmt.setString(2, psw1);
			// eseguo la query
			rs = pstmt.executeQuery();
			// eseguo l'interrogazione desiderata
			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				System.out.println("esiste un utente");
				logged = true;
				StateTracker.getStateTracker().getUser().setCodFisc(rs.getString(1));				
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();
		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return logged;
	}
	
	// recupero il numero di offerte registrate fino a questo momento per questa vendita
	private String countOffers = "SELECT count(*) FROM contatto WHERE tentata_vendita = ?";
	
	public int offerteRegistrate(int codice_tentata_vendita){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			pstmt = con.prepareStatement(countOffers);
			pstmt.setInt(1, codice_tentata_vendita);
			rs = pstmt.executeQuery();
			
			if (rs.next()) result = rs.getInt(1);
		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();
		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		
		return result;
	}

	private String insertAcquirente = "INSERT INTO Acquirente (cod_fisc, nome, cognome, citta, n_tel) "
				+ "VALUES (?, ?, ?, ?, ?)";
	private String existsAcquirente = "SELECT 1 FROM Acquirente WHERE cod_fisc = ?";
	private String insertContatto = "INSERT INTO Contatto (tentata_vendita, acquirente, data, prezzo_offerta) VALUES (?, ?, ?, ?)";
	
	public boolean newContattoAcquirente(
			int codice_tentata_vendita
			, String codFisc
			, String cognome
			, String nome
			, String numTelefono
			, String citta
			, double prezzoOfferta) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		try {
			con = DriverManager.getConnection(url, user, passwd);
			
			pstmt = con.prepareStatement(existsAcquirente);
			pstmt.setString(1, codFisc);
			rs = pstmt.executeQuery();
			if (! rs.next()) {
				pstmt = con.prepareStatement(insertAcquirente);
				pstmt.setString(1, codFisc);
				pstmt.setString(2, nome);
				pstmt.setString(3, cognome);
				pstmt.setString(4, citta);
				pstmt.setString(5, numTelefono);
				pstmt.execute();
			}
			pstmt = con.prepareStatement(insertContatto);
			pstmt.setInt(1, codice_tentata_vendita);
			pstmt.setString(2, codFisc);
			pstmt.setDate(3, Date.valueOf(LocalDate.now()));
			pstmt.setDouble(4, prezzoOfferta);
			pstmt.execute();
			
			result = true;
		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();
		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	
//	private String listaVenditaUser = "SELECT * "
//			+ "FROM Immobile I join Tentata_vendita V on I.codice=V.immobile join numofferte N on V.id_tentata_vendita=N.noffert "
//			+ "WHERE V.data_fine>? and V.cliente LIKE ?";
	
	private String listaVenditaUser ="SELECT * FROM Immobile I join Tentata_vendita V on I.codice=V.immobile left join numofferte N on V.id_tentata_vendita=N.tentata_vendita WHERE V.data_fine>? and V.cliente LIKE ?";
	
	
	public List<Vendita> getListaVenditaUser() {
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Vendita> result = new ArrayList<Vendita>();
		int c = 1;

		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("connesso");
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(listaVenditaUser);
			pstmt.clearParameters();
			// imposto i parametri della query
			pstmt.setDate(1, Date.valueOf(LocalDate.now()));
			pstmt.setString(2, StateTracker.getStateTracker().getUser().getCodFisc());
			// eseguo la query
			rs = pstmt.executeQuery();
			// eseguo l'interrogazione desiderata
			// memorizzo il risultato dell'interrogazione nel Vector
			System.out.println("query user "+StateTracker.getStateTracker().getUser());
			while (rs.next()) {
				result.add(makeVenditaUserBean(rs));
				System.out.println(c+++"case in vendita");
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	private Vendita makeVenditaUserBean(ResultSet rs) throws SQLException {
		Vendita bean = new Vendita(rs.getInt("id_tentata_vendita"),
				rs.getString("classtype"), rs.getString("indirizzo"),
				rs.getString("citta"),
				rs.getInt("superficie"), rs.getInt("numero_vani"),
				rs.getString("descrizione"), rs.getInt("superficie_giardino"),
				rs.getInt("piano"), rs.getDate("data_inizio"),
				rs.getDate("data_fine"), rs.getInt("prezzo_minimo"),rs.getInt("noffert"));
		return bean;
	}
	
	
	private String listaOfferte ="SELECT * FROM contatto c join acquirente a on c.acquirente=a.cod_fisc WHERE c.tentata_vendita=? order by data desc";


	public List<Offerta> getListaOfferte(int codice) {
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Offerta> result = new ArrayList<Offerta>();

		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("connesso");
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(listaOfferte);
			pstmt.clearParameters();
			// imposto i parametri della query
			pstmt.setInt(1,codice);
			// eseguo la query
			rs = pstmt.executeQuery();
			// eseguo l'interrogazione desiderata
			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				result.add(makeOffertaBean(rs));
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
		}
	
	
	
	private Offerta makeOffertaBean(ResultSet rs) throws SQLException {
		Offerta bean = new Offerta(rs.getInt("tentata_vendita"),rs.getString("nome"),rs.getString("cognome"),rs.getString("n_tel"),rs.getString("citta"),rs.getDouble("prezzo_offerta"),rs.getDate("data"));
		return bean;
	}
	
	// === Methods
	// ===============================================================

}
