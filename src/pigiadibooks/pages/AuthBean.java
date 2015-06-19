package pigiadibooks.pages;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "auth", eager=true)
@SessionScoped
public class AuthBean implements Serializable{
	
	public AuthBean(){}
	
	@PostConstruct
	public void initialize(){
		
	}
	
	public String getUsername() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		return request.getUserPrincipal().getName();
	}
	
	public boolean isLoggedIn() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		return request.getUserPrincipal()!=null;
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		request.getSession().invalidate();
		return "/index.jsf?faces-redirect=true";
	}
	
	public String invalidateSession() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "/index.jsf?faces-redirect=true";
	}
	
	public boolean isAdmin(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		return false;
	}
}
