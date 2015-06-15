package pigiadibooks.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "mainView")
@ViewScoped
public class MainView {
	
	@ManagedProperty(value="#{searchResults}")
    private SearchByTitleBean searchResults;
	
	private String searchByTitle;
	
	public MainView() {
		this.searchByTitle = null;
	}

	public String getSearchByTitle() {
		return searchByTitle;
	}

	public void setSearchByTitle(String searchByTitle) {
		this.searchByTitle = searchByTitle;
	}
	
	public SearchByTitleBean getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(SearchByTitleBean searchResults) {
		this.searchResults = searchResults;
	}

	public String search() {
		if (this.searchByTitle != null){
			this.searchResults.setTitle(this.searchByTitle);
			return "searchResults";
		}
		return null;
	}
}
