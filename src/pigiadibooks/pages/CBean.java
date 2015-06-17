package pigiadibooks.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "c")
@SessionScoped
public class CBean {

	public String messaggio;
	
	public CBean(){
		this.messaggio="ciaooo";
	}
	
	public String getMsg(){
		return this.messaggio;
	}
	
}