package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pigiadibooks.model.OwnBookModel;
import pigiadibooks.pagesutil.OwnedBooks;

//ATTENZIONE DA DISCUTERE SE METTERE MANAGED PROPERTY CON BOOKDETAIL E FARLI DOPPIAMENTE LINKATI
//PER GESTIRE IL LORO COLLEGAMENTO

//TODO controlli che l'utente che modifica sia lo stesso della lista modificata

@ManagedBean(name = "editbooks")
@SessionScoped
public class EditOwnBooksBean implements Serializable{
	
	
	private String user;
	private OwnedBooks owned;
	
	private String current;
	
	//input con convertitore
	private int condizioni, voto;
	private String recensione;

	public EditOwnBooksBean(){
		this.user="";
		this.current="";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
		this.owned=new OwnedBooks(this.user);
	}
	
	public void setCurrent(String current) {
		this.current = current;
	}
	
	public void setCondizioni(int condizioni) {
		this.condizioni = condizioni;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public void setRecensione(String recensione) {
		this.recensione = recensione;
	}

	public void addBook(){
		try {
			this.owned.insertBook(this.current);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeBook(){
		try {
			this.owned.deleteBook(this.current);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<OwnBookModel> getUserBookList(){
		try {
			return this.owned.selectOwnBooks();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<OwnBookModel>();
	}
	
	
}
