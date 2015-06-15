package pigiadibooks.pages;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pigiadibooks.booklistsync.BookLookup;
import pigiadibooks.model.BookModel;

@ManagedBean(name = "sbt")
@SessionScoped
public class SearchByTitleBean {
	
	private BookLookup lookup=null;
	private List<BookModel> cache;
	private String lastTitleSearched;
	
	public SearchByTitleBean(){
		this.cache=new ArrayList<BookModel>();
		this.lastTitleSearched="";
	}
	
	public void setTitle(String title){
		this.lookup=new BookLookup(title);
	}
	
	public List<BookModel> getResults(){
		List<BookModel> result=null;
		
		try {
			if(!this.lastTitleSearched.equals(this.lookup.getTitle())){
				result=this.lookup.lookupByTitle();
				this.lastTitleSearched=this.lookup.getTitle();
				this.cache=result;
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
}
