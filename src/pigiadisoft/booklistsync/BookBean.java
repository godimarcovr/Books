package pigiadisoft.booklistsync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pigiadisoft.dbhandler.DataBean;

public class BookBean implements DataBean {

	String titolo,isbn_13;
	Set<String> autori;

	public BookBean() {
		this.titolo = "";
		this.autori = new HashSet<String>();
		this.isbn_13 = "";
	}

	public BookBean(String titolo, List<String> autori, String isbn_13) {
		this.titolo = titolo;
		this.autori = new HashSet<String>();
		this.autori.addAll(autori);
		this.isbn_13 = isbn_13;
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

	public String getIsbn_13() {
		return isbn_13;
	}

	public void setIsbn_13(String isbn_13) {
		//conversione automatica da formato isbn10 a formato isbn13
		if(isbn_13.length()==10){
			this.isbn_13 = ISBN10toISBN13(isbn_13);
		}
		else{
			this.isbn_13=isbn_13;
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
		return "BookBean [titolo=" + titolo + ", isbn_13=" + isbn_13
				+ ", autori=" + autori + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autori == null) ? 0 : autori.hashCode());
		result = prime * result + ((isbn_13 == null) ? 0 : isbn_13.hashCode());
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
		BookBean other = (BookBean) obj;
		if (autori == null) {
			if (other.autori != null)
				return false;
		} else if (!autori.equals(other.autori))
			return false;
		if (isbn_13 == null) {
			if (other.isbn_13 != null)
				return false;
		} else if (!isbn_13.equals(other.isbn_13))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}
	
	
	
	

}
