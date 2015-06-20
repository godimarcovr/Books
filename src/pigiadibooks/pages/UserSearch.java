package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.model.PublicUserModel;
import pigiadibooks.pagesutil.NearbyFakePublicUserModelStrategy;


@ManagedBean(name = "userSearch")
@SessionScoped
public class UserSearch implements Serializable{
	
	private List<PublicUserModel> results;
	
	private String status;
	
	public UserSearch(){}
	
	@PostConstruct
	public void initialize(){
		this.results=new ArrayList<PublicUserModel>(0);
		this.status="";
	}

	public List<PublicUserModel> getResults() {
		return results;
	}

	public void setResults(List<PublicUserModel> results) {
		this.results = results;
	}
	
	public String setSearchKey(String username, String toSearch, float range){
		try {
			NearbyFakePublicUserModelStrategy nearby=
					new NearbyFakePublicUserModelStrategy(username, toSearch, range);
			List<DataModel> l=nearby.getSelectedBeans(MyDriver.getInstance().getConnection());
			this.results=new ArrayList<PublicUserModel>(l.size());
			for (DataModel dataModel : l) {
				this.results.add((PublicUserModel)dataModel);
			}
			this.status="Utenti trovati entro il raggio di "+range+":";
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.results = new ArrayList<PublicUserModel>(0);
			this.status="Nessun risultato trovato!";
		}
		return "/user/userResults.jsf?faces-redirect=true";
	}

	public String getStatus() {
		return status;
	}
	
	
	
}
