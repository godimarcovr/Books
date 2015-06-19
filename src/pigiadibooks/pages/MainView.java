package pigiadibooks.pages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "mainView", eager=true)
@SessionScoped
public class MainView implements Serializable{
	
	@ManagedProperty(value="#{searchResults}")
    private SearchByTitleBean searchResults;
	
	public MainView() {}
	
	public SearchByTitleBean getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(SearchByTitleBean searchResults) {
		this.searchResults = searchResults;
	}

	public String search() {
		if (this.searchResults.getTitle() != null 
				&& !this.searchResults.getTitle().equals(""))
			return "/searchResults.jsf?faces-redirect=true";
		return null;
	}
}
