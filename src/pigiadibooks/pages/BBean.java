package pigiadibooks.pages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "b")
@SessionScoped
public class BBean {

	@ManagedProperty(value="#{c}")
	private CBean c;
	private String asd;
	public BBean(){
		this.asd="osd";
	}

	public CBean getC() {
		return c;
	}

	public void setC(CBean c) {
		this.c = c;
	}
	
	public String getMsg(){
		return this.c.getMsg();
	}
	
}
