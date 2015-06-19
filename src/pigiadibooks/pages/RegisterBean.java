package pigiadibooks.pages;

import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import pigiadibooks.model.FakePosPrivateUserModel;
import pigiadibooks.pagesutil.FakePosPrivateUserEdit;


@ManagedBean(name = "register", eager=true)
@SessionScoped
public class RegisterBean implements Serializable{
	
	private String username, password, email, nome, cognome;
	private float x,y;
	private boolean regFailed;
	
	//**************INIT****************
	public RegisterBean(){}
	
	@PostConstruct
	public void initialize(){
		this.username="";
		this.password="";
		this.email="";
		this.nome="";
		this.cognome="";
		this.x=0.0f;
		this.y=0.0f;
		this.regFailed=false;
	}
	//************************************

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public boolean isRegFailed() {
		return regFailed;
	}
	
	public String register(){
		FakePosPrivateUserModel fpum=new FakePosPrivateUserModel();
		if(this.username==null || this.username.equals("") ||
				this.password==null || this.password.equals("") ||
				this.email==null || this.email.equals("")){
			this.regFailed=true;
			return "";
		}
		else{
			fpum.setCognome(this.cognome);
			fpum.setNome(this.nome);
			fpum.setUsername(this.username);
			fpum.setPassword(this.password);
			fpum.setEmail(this.email);
			fpum.setxPos(this.x);
			fpum.setyPos(this.y);
			fpum.setRuolo("user");
			FakePosPrivateUserEdit refg=new FakePosPrivateUserEdit(this.username);
			try {
				refg.insertUser(fpum);
				this.regFailed=false;
				return "/index.jsf?faces-redirect=true";
			} catch (Exception e) {
				e.printStackTrace();
				this.regFailed=true;
				return "";
			}
		}
		
	}
	
	
}
