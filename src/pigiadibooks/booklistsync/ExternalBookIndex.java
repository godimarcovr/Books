package pigiadibooks.booklistsync;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import pigiadibooks.model.BookModel;

/**
 * 
 * Classe che si occupa di recuperare dati sui libri da una sorgente remota
 *
 */
public abstract class ExternalBookIndex {
	
	/**
	 * Dato un titolo del libro, ritorna i risultati relativi recuperati da remoto
	 * @param title titolo del libro da cercare o una parte di esso
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public abstract List<BookModel> getBooksByTitle(String title) throws GeneralSecurityException, IOException;
	
}
