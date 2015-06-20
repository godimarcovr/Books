package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pigiadibooks.model.BookSearchModel;
import pigiadibooks.model.LoginRecordModel;
import pigiadibooks.pagesutil.LoginLoggerStat;
import pigiadibooks.pagesutil.ResearchLoggerStat;

@ManagedBean(name = "stat")
@SessionScoped
public class Statistics implements Serializable{

	private LoginLoggerStat ll;
	private ResearchLoggerStat rl;
	
	public Statistics(){}
	
	@PostConstruct
	public void initialize(){
		this.ll=new LoginLoggerStat();
		this.rl=new ResearchLoggerStat();
	}
	
	public LoginRecordModel getLastLogin(){
		try {
			return this.ll.getLastLogin();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new LoginRecordModel();
		}
	}
	
	public List<BookSearchModel> getTop10Search(){
		try {
			List<BookSearchModel> toRet=this.rl.getTop10Searches();
			return toRet;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			List<BookSearchModel> toRet=new ArrayList<BookSearchModel>();
			toRet.add(new BookSearchModel());
			return toRet;
		}
	}
	
}
