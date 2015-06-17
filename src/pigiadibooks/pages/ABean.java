package pigiadibooks.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "a")
@SessionScoped
public class ABean {

	@ManagedProperty(value="#{b}")
	private BBean b;
	
	private String asd;
	public ABean(){
		this.asd="asd";
	}
	
	public BBean getB() {
		return b;
	}

	public void setB(BBean b) {
		this.b = b;
	}

	public String getMsg(){
		return this.b.getMsg();
	}
}
