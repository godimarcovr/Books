package pigiadibooks.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookModel implements DataModel {

	String titolo,industryID,descrizione,imgurl;
	Set<String> autori;

	public BookModel() {
		this.titolo = "";
		this.autori = new HashSet<String>();
		this.industryID = "";
		this.descrizione="";
		this.imgurl="";
	}
	
	public BookModel(String titolo, String industryID, String descrizione,	String imgurl) {
		super();
		this.titolo = titolo;
		this.industryID = industryID;
		this.descrizione = descrizione;
		this.imgurl = imgurl;
		this.autori = new HashSet<String>();
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	

	public Set<String> getAutori() {
		return autori;
	}

	public void addAutore(String autore){
		this.autori.add(autore);
	}
	
	public void addAutori(Collection<String> autori){
		this.autori.addAll(autori);
	}

	public String getindustryID() {
		return industryID;
	}

	public void setindustryID(String industryID) {
		//conversione automatica da formato isbn10 a formato isbn13
		if(industryID.length()==10){
			this.industryID = ISBN10toISBN13(industryID);
		}
		else{
			this.industryID=industryID;
		}
		
	}

	private static String ISBN10toISBN13(String ISBN10) {
		String ISBN13 = ISBN10;
		ISBN13 = "978" + ISBN13.substring(0, 9);
		// if (LOG_D) Log.d(TAG, "ISBN13 without sum" + ISBN13);
		int d;

		int sum = 0;
		for (int i = 0; i < ISBN13.length(); i++) {
			d = ((i % 2 == 0) ? 1 : 3);
			sum += ((((int) ISBN13.charAt(i)) - 48) * d);
			// if (LOG_D) Log.d(TAG, "adding " + ISBN13.charAt(i) + "x" + d +
			// "=" + ((((int) ISBN13.charAt(i)) - 48) * d));
		}
		sum = 10 - (sum % 10);
		ISBN13 += sum;

		return ISBN13;
	}

	@Override
	public String toString() {
		return "BookModel [titolo=" + titolo + ", industryID=" + industryID
				+ ", descrizione=" + descrizione + ", imgurl=" + imgurl
				+ ", autori=" + autori + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autori == null) ? 0 : autori.hashCode());
		result = prime * result
				+ ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((imgurl == null) ? 0 : imgurl.hashCode());
		result = prime * result + ((industryID == null) ? 0 : industryID.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
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
		BookModel other = (BookModel) obj;
		if (autori == null) {
			if (other.autori != null)
				return false;
		} else if (!autori.equals(other.autori))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (imgurl == null) {
			if (other.imgurl != null)
				return false;
		} else if (!imgurl.equals(other.imgurl))
			return false;
		if (industryID == null) {
			if (other.industryID != null)
				return false;
		} else if (!industryID.equals(other.industryID))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
}
