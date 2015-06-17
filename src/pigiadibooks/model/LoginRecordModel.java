package pigiadibooks.model;

import java.sql.Timestamp;

public class LoginRecordModel implements DataModel {
	private String user, provincia;
	private Timestamp logintime;
	
	public LoginRecordModel(String user, String provincia, Timestamp logintime) {
		super();
		this.user = user;
		this.provincia = provincia;
		this.logintime = logintime;
	}
	
	public LoginRecordModel() {
		super();
		this.user = "";
		this.provincia = "";
		this.logintime = Timestamp.valueOf("1970-01-01 00:00:00");
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((logintime == null) ? 0 : logintime.hashCode());
		result = prime * result
				+ ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginRecordModel other = (LoginRecordModel) obj;
		if (logintime == null) {
			if (other.logintime != null)
				return false;
		} else if (!logintime.equals(other.logintime))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginRecordModel [user=" + user + ", provincia=" + provincia
				+ ", logintime=" + logintime + "]";
	}
	
	
	
}
