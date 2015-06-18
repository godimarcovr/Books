package pigiadibooks.model;

public class OwnBookModel implements DataModel {
	
	private String titolo,industryID,recensione;
	private int condizioni,voto;
	
	public OwnBookModel(String titolo, String industryID, String recensione,
			int condizioni, int voto) {
		super();
		this.titolo = titolo;
		this.industryID = industryID;
		this.recensione = recensione;
		this.condizioni = condizioni;
		this.voto = voto;
	}

	public OwnBookModel() {
		super();
		this.titolo = "";
		this.industryID = "";
		this.recensione = "";
		this.condizioni = 0;
		this.voto = 0;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getIndustryID() {
		return industryID;
	}

	public void setIndustryID(String industryID) {
		this.industryID = industryID;
	}

	public String getRecensione() {
		return recensione;
	}

	public void setRecensione(String recensione) {
		this.recensione = recensione;
	}

	public int getCondizioni() {
		return condizioni;
	}

	public void setCondizioni(int condizioni) {
		this.condizioni = condizioni;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "OwnBookModel [titolo=" + titolo + ", industryID=" + industryID
				+ ", recensione=" + recensione + ", condizioni=" + condizioni
				+ ", voto=" + voto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + condizioni;
		result = prime * result
				+ ((industryID == null) ? 0 : industryID.hashCode());
		result = prime * result
				+ ((recensione == null) ? 0 : recensione.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
		result = prime * result + voto;
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
		OwnBookModel other = (OwnBookModel) obj;
		if (condizioni != other.condizioni)
			return false;
		if (industryID == null) {
			if (other.industryID != null)
				return false;
		} else if (!industryID.equals(other.industryID))
			return false;
		if (recensione == null) {
			if (other.recensione != null)
				return false;
		} else if (!recensione.equals(other.recensione))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		if (voto != other.voto)
			return false;
		return true;
	}
	
	
	
	
	
}
