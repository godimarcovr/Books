package pigiadibooks.model;

public class FakePosPrivateUserModel extends PublicUserModel {
	private String password, email, ruolo;
	private float xPos,yPos;
	
	
	
	public FakePosPrivateUserModel(String username, String nome, String cognome
			,String password, String email,
			float xPos, float yPos, String ruolo) {
		super(username, nome, cognome);
		this.password = password;
		this.email = email;
		this.ruolo = ruolo;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public FakePosPrivateUserModel(){
		super();
		this.password = "";
		this.email = "";
		this.ruolo = "";
		this.xPos = 0;
		this.yPos = 0;
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
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public float getxPos() {
		return xPos;
	}
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	public float getyPos() {
		return yPos;
	}
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	@Override
	public String toString() {
		return "FakePosPrivateUserModel [password=" + password + ", email="
				+ email + ", ruolo=" + ruolo + ", xPos=" + xPos + ", yPos="
				+ yPos + ", getUsername()=" + getUsername() + ", getNome()="
				+ getNome() + ", getCognome()=" + getCognome()
				+ ", toString()=" + super.toString() + ", hashCode()="
				+ hashCode() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		result = prime * result + Float.floatToIntBits(xPos);
		result = prime * result + Float.floatToIntBits(yPos);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FakePosPrivateUserModel other = (FakePosPrivateUserModel) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		if (Float.floatToIntBits(xPos) != Float.floatToIntBits(other.xPos))
			return false;
		if (Float.floatToIntBits(yPos) != Float.floatToIntBits(other.yPos))
			return false;
		return true;
	}
	
	
	
	
}
