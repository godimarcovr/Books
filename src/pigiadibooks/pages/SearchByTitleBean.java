package pigiadibooks.pages;

import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pigiadibooks.model.BookModel;
import pigiadibooks.pagesutil.BookLookup;

@ManagedBean(name = "searchResults", eager=true)
@SessionScoped
public class SearchByTitleBean implements Serializable{
	
	private BookLookup lookup=null;
	private List<BookModel> cache;
	private String lastTitleSearched;
	
	@ManagedProperty(value="#{bookDetails}")
    private BookDetails bookDetails;
	
	public SearchByTitleBean(){}
	
	@PostConstruct
	public void init() {
		this.setCache(new ArrayList<BookModel>());
		this.lastTitleSearched="";
	}
	
	public void setTitle(String title){
		this.lookup=new BookLookup(title);
		getResults();
	}
	
	public String getTitle() {
		return this.lastTitleSearched;
	}
	
	public List<BookModel> getResults(){
		List<BookModel> result=null;
		
		try {
			if(!this.lastTitleSearched.equals(this.lookup.getTitle())){
				result=this.lookup.lookupByTitle();
				this.lastTitleSearched=this.lookup.getTitle();
				this.setCache(result);
			}			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException
				| GeneralSecurityException | IOException e) {
			if(e instanceof SQLException){
				SQLException ex=(SQLException) e;
				while(ex instanceof SQLException){
					System.out.println(ex);
					ex=ex.getNextException();
				}
			}
			e.printStackTrace();
		}
		finally{
			result=new ArrayList<BookModel>();
		}
		return result; 
	}

	public List<BookModel> getCache() {
		return cache;
	}

	public void setCache(List<BookModel> cache) {
		this.cache = cache;
		for (BookModel bookModel : cache) {
			if(bookModel.getImgurl()== null || bookModel.getImgurl().isEmpty()){
				bookModel.setImgurl("http://www.kitabyzer.com/img/default_cover.jpg");
			}
		}
	}
	
	public BookDetails getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
	}

	public String selectBook(BookModel book) {
		if (this.bookDetails != null && book != null) {
			this.bookDetails.setSelectedBook(book);
			return "bookDetails";
		}
		return null;
	}
}
