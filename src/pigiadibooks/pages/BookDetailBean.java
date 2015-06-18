package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pigiadibooks.booklistsync.BookBeanStrategy;
import pigiadibooks.dbhandler.MyDriver;
import pigiadibooks.dbhandler.Query;
import pigiadibooks.dbhandler.SQLCodeBuilder;
import pigiadibooks.model.BookModel;
import pigiadibooks.model.DataModel;
import pigiadibooks.model.PublicUserModel;
import pigiadibooks.pagesutil.NearbyFakePublicUserModelStrategy;

//ATTENZIONE DA DISCUTERE SE METTERE MANAGED PROPERTY CON EDITOWNBOOKS E FARLI DOPPIAMENTE LINKATI

@ManagedBean(name = "bookdetail")
@SessionScoped
public class BookDetailBean implements Serializable {
	private String detail;
	private BookModel current;
	private BookBeanStrategy bbs;
	private final String from = "ScrittoDa AS SD JOIN Libro L ON SD.libro_industryid=L.industryid "
			+ "JOIN Autore A ON A.nome=SD.autore_nome ";

	public BookDetailBean() {
		this.current = new BookModel();
		this.detail = "";
	}

	public String getDetail() {
		return detail;
	}

	public void setDetailID(String detail) {
		this.detail = detail;
		// testato in bookbeanstrategytest
		Query select = (Query) SQLCodeBuilder.createSelectAllFromWhere(
				this.from, "L.industryid='" + this.detail + "'");
		this.bbs = new BookBeanStrategy(select);
		try {
			this.current = (BookModel) this.bbs.getSelectedBeans(
					MyDriver.getInstance().getConnection()).get(0);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.err.println("ERRORE: verrà visualizzata pagina vuota");
			this.current = new BookModel();
			this.detail = "";
		}

	}

	public BookModel getBookModel() {
		return this.current;
	}

	public List<PublicUserModel> getNearbyOwners(String user) {
		try {
			NearbyFakePublicUserModelStrategy nearbyUsers = 
					new NearbyFakePublicUserModelStrategy(user, this.detail);
			List<DataModel> toCast=nearbyUsers.getSelectedBeans(MyDriver.getInstance().getConnection());
			List<PublicUserModel> toRet=new ArrayList<PublicUserModel>(toCast.size());
			for (DataModel dataModel : toCast) {
				toRet.add((PublicUserModel)dataModel);
			}
			return toRet;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<PublicUserModel>();
	}

}
