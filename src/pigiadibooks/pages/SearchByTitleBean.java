package pigiadibooks.pages;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pigiadibooks.model.BookModel;
import pigiadibooks.pagesutil.BookLookup;

@ManagedBean(name = "searchResults")
@SessionScoped
public class SearchByTitleBean {
	
	private BookLookup lookup=null;
	private List<BookModel> cache;
	private String lastTitleSearched;
	
	public SearchByTitleBean(){
		this.setCache(new ArrayList<BookModel>());
		this.lastTitleSearched="";
	}
	
	public void setTitle(String title){
		this.lookup=new BookLookup(title);
		getResults();
	}
	
	private List<BookModel> getResults(){
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
	}
}
