package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.OwnBookModel;
import pigiadibooks.model.PublicUserModel;
import pigiadibooks.pagesutil.BookLookup;
import pigiadibooks.pagesutil.OwnedBooks;
import pigiadibooks.pagesutil.SinglePublicUserStrategy;

@ManagedBean(name = "other", eager=true)
@SessionScoped
public class OtherBean implements Serializable{
	
	private PublicUserModel user;
	private OwnedBooks books_access;
	private String username;
	
	@ManagedProperty(value="#{bookDetails}")
    private BookDetails bookDetails;
	
	public BookDetails getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
	}

	// **************INIT****************
	public OtherBean() {
	}

	@PostConstruct
	public void initialize() {
		this.user=new PublicUserModel();
		this.books_access=null;

		this.bookDetails.setOther(this);
	}
	// ************************************
	
	public PublicUserModel getUser() {
		return user;
	}

	public void setUsername(String username) {
		this.username = username;
		this.books_access=new OwnedBooks(this.username);
		SinglePublicUserStrategy spus=new SinglePublicUserStrategy(username);
		try {
			List<DataModel> l=spus.getSelectedBeans(MyDriver.getInstance().getConnection());
			if(l.size()==1){
				this.user=(PublicUserModel) l.get(0);
			}
			else{
				this.user=new PublicUserModel();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.user=new PublicUserModel();
		}
		
		
	}
	
	public List<OwnBookModel> getBooks(){
		List<OwnBookModel> toRet=null;
		try {
			toRet=this.books_access.selectOwnBooks();
		} catch (Exception e) {
			e.printStackTrace();
			toRet=new ArrayList<OwnBookModel>();
		}
		return toRet;
	}
	
	public String goToDetail(OwnBookModel own){
		BookLookup bl=new BookLookup();
		try {
			BookModel bm=bl.getByID(own.getIndustryID());
			this.bookDetails.setSelectedBook(bm);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "/bookDetails.jsf?faces-redirect=true";
	}
	
	public void poke(){
		
	}
	
}
