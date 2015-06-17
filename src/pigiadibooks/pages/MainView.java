package pigiadibooks.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "mainView", eager=true)
@SessionScoped
public class MainView {
	
	@ManagedProperty(value="#{searchResults}")
    private SearchByTitleBean searchResults;
	
	public MainView() {}

	public String getSearchByTitle() {
		return this.searchResults.getTitle();
	}

	public void setSearchByTitle(String searchByTitle) {
		this.searchResults.setTitle(searchByTitle);
	}
	
	public SearchByTitleBean getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(SearchByTitleBean searchResults) {
		this.searchResults = searchResults;
	}

	public String search() {
		if (this.searchResults.getTitle() != null)
			return "searchResults";
		return null;
	}
}
