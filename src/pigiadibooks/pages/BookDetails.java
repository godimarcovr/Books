package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.OwnBookModel;
import pigiadibooks.model.PublicUserModel;
import pigiadibooks.pagesutil.NearbyFakePublicUserModelStrategy;
import pigiadibooks.pagesutil.OwnedBooks;

@ManagedBean(name = "bookDetails", eager=true)
@SessionScoped
public class BookDetails implements Serializable{
	
	private BookModel selectedBook;
	private OwnedBooks ownedBooks;
	private Boolean isOwned;
	
	private OwnBookModel mioLibro;
	
	@ManagedProperty(value="#{auth}")
	private AuthBean auth;
	private PublicUserModel lastBorrowRequested;
	
	public BookDetails() {}
	
	@PostConstruct
	public void init() {
		this.selectedBook = new BookModel();
		this.ownedBooks=null;
		this.isOwned=null;
		this.mioLibro=new OwnBookModel();
		this.lastBorrowRequested=null;
	}
	
	public BookModel getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(BookModel selectedBook) {
		this.selectedBook = selectedBook;
		this.isOwned=null;
		this.lastBorrowRequested=null;
		
		if(this.getLoggedIn() && this.ownedBooks==null){
			this.ownedBooks=new OwnedBooks(auth.getUsername());
		}
	}
	
	//******************************GESTIONE INTERAZIONE
	
	public AuthBean getAuth() {
		return auth;
	}

	public void setAuth(AuthBean auth) {
		this.auth = auth;
	}

	//true se utente è loggato
	public boolean getLoggedIn(){
		if(auth.isLoggedIn()){
			return true;
		}
		else{
			this.ownedBooks=null;
			this.isOwned=null;
			this.lastBorrowRequested=null;
			return false;
		}
	}
	
	//true se utente possiede libro
	@SuppressWarnings("finally")
	public boolean getOwned(){
		if(this.getLoggedIn()){
			if(this.isOwned==null){
				try {
					this.isOwned=false;
					for(DataModel dm:this.ownedBooks.selectOwnBooks()){
						OwnBookModel obm=(OwnBookModel) dm;
						if (this.selectedBook.getindustryID().equals(obm.getIndustryID())){
							this.isOwned=true;
							this.mioLibro=obm;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					this.isOwned=false;
				}
				finally{
					return this.isOwned;
				}
			}
			else{
				return this.isOwned;
			}
		}
		else{
			return false;
		}
		
	}
	
	//torna utenti che possiedono questo libro ordinati per vicinanza geografica
	public List<PublicUserModel> getNearbyOwners(){
		if(this.getLoggedIn()){
			try {
				NearbyFakePublicUserModelStrategy nearbyUsersStrat=
						new NearbyFakePublicUserModelStrategy(auth.getUsername(),this.selectedBook.getindustryID());
				List<DataModel> ldm=nearbyUsersStrat.getSelectedBeans(MyDriver.getInstance().getConnection());
				List<PublicUserModel> toRet=new ArrayList<PublicUserModel>(ldm.size());
				for (DataModel dm : ldm) {
					toRet.add((PublicUserModel) dm);
				}
				return toRet;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return new ArrayList<PublicUserModel>(0);
	}
	
	public String sendBorrowRequest(){
		return "Email richiesta prestito inviata!";
	}
	
	//da usare per il tasto "Ce l'ho!"
	public String iOwnIt(){
		if(this.getLoggedIn()){
			try {
				this.ownedBooks.insertBook(this.selectedBook.getindustryID());
				for(DataModel dm:this.ownedBooks.selectOwnBooks()){
					OwnBookModel obm=(OwnBookModel) dm;
					if (this.selectedBook.getindustryID().equals(obm.getIndustryID())){
						this.isOwned=true;
						this.mioLibro=obm;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "bookDetails";
	}
	
	public String iDontOwnIt(){
		if(this.getLoggedIn()){
			try {
				this.ownedBooks.deleteBook(this.selectedBook.getindustryID());
				this.isOwned=false;
				this.lastBorrowRequested=null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "bookDetails";
	}

	public OwnBookModel getMioLibro() {
		return mioLibro;
	}

	public void setMioLibro(OwnBookModel mioLibro) {
		this.mioLibro = mioLibro;
	}

	@Override
	public String toString() {
		return "BookDetails [selectedBook=" + selectedBook + ", ownedBooks="
				+ ownedBooks + ", isOwned=" + isOwned + ", mioLibro="
				+ mioLibro + ", auth=" + auth + "]";
	}	
	
	public String submitReview(){
		try {
			this.ownedBooks.updateBook(this.mioLibro.getIndustryID()
					, this.mioLibro.getCondizioni()==0?null:this.mioLibro.getCondizioni()
					, this.mioLibro.getVoto()==0?null:this.mioLibro.getVoto()
					, this.mioLibro.getRecensione());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "bookDetails";
	}
	
	public String isBorrowed(PublicUserModel pum){
		if(pum.equals(this.lastBorrowRequested)){
			return "Richiesta Inviata";
		}
		else{
			return "Chiedi in prestito!";
		}
	}
	
	public String borrow(PublicUserModel pum){
		this.lastBorrowRequested=pum;
		return "bookDetails";
	}
	
}
