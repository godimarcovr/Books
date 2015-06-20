package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import pigiadibooks.model.BookModel;
import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.model.OwnBookModel;
import pigiadibooks.pagesutil.BookLookup;
import pigiadibooks.pagesutil.FakePosPrivateUserEdit;
import pigiadibooks.pagesutil.OwnedBooks;

@ManagedBean(name = "personal")
@SessionScoped
public class PersonalProfile implements Serializable {

	@ManagedProperty(value="#{auth}")
	private AuthBean auth;
	
	
	@ManagedProperty(value="#{bookDetails}")
    private BookDetails bookDetails;
	
	@ManagedProperty(value="#{other}")
    private OtherBean other;
	
	private FakePosPrivateUserModel myUser;
	private String username;
	
	private String statusMessage;
	
	private OwnedBooks myBooks;
	
	// **************INIT****************
	public PersonalProfile() {	}

	@PostConstruct
	public void initialize() {
		this.setUsername();
		this.statusMessage="";
	}
	// ************************************

	public String getUsername() {
		return username;
	}

	public void setUsername() {
		if(auth.isLoggedIn()){
			this.username=auth.getUsername();
			FakePosPrivateUserEdit fppu=new FakePosPrivateUserEdit(this.username);
			this.statusMessage="";
			try {
				this.myUser=fppu.getUserInfo();
			} catch (Exception e) {
				this.myUser=new FakePosPrivateUserModel();
				e.printStackTrace();
			}
			this.myBooks=new OwnedBooks(this.username);
		}
		else{
			username=null;
		}
	}
	
	public AuthBean getAuth() {
		return auth;
	}

	public void setAuth(AuthBean auth) {
		this.auth = auth;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public FakePosPrivateUserModel getMyUser() {
		return myUser;
	}

	public void setMyUser(FakePosPrivateUserModel myUser) {
		this.myUser = myUser;
	}

	public String edit(){
		if(this.myUser.getUsername()==null || this.myUser.getUsername().equals("") ||
				this.myUser.getPassword()==null || this.myUser.getPassword().equals("") ||
				this.myUser.getEmail()==null || this.myUser.getEmail().equals("")){
			this.statusMessage="Cambio dati personali fallito!";
			this.resetFields();
			return "/user/profile.jsf?faces-redirect=true";
		}
		else{
			FakePosPrivateUserEdit refg=new FakePosPrivateUserEdit(this.username);
			try {
				refg.updateInfo(this.myUser.getEmail()
						, this.myUser.getxPos(), this.myUser.getyPos());
				this.statusMessage="Dati personali cambiati!";
				return "";
			} catch (Exception e) {
				e.printStackTrace();
				this.statusMessage="Cambio dati personali fallito!";
				return "";
			}
		}
	}
	
	public List<OwnBookModel> getMyBooks(){
		if(this.auth.isLoggedIn()){
			try {
				return this.myBooks.selectOwnBooks();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ArrayList<OwnBookModel>();
	}
	
	

	public BookDetails getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
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

	public OtherBean getOther() {
		return other;
	}

	public void setOther(OtherBean other) {
		this.other = other;
	}
	
	public String resetFields(){
		this.setUsername();
		return "/user/profile.jsf?faces-redirect=true";
	}

}
