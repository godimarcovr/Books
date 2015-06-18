package pigiadibooks.pages;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pigiadibooks.model.BookModel;

@ManagedBean(name = "bookDetails", eager=true)
@SessionScoped
public class BookDetails {
	
	private BookModel selectedBook;

	public BookDetails() {}
	
	@PostConstruct
	public void init() {
		this.selectedBook = null;
		/*
		this.selectedBook = new BookModel("I, Robot", 
				"9780007532278", 
				"Voyager Classics - timeless masterworks of science fiction and fantasy. A beautiful clothbound edition of I, Robot, the classic collection of robot stories from the master of the genre.", 
				"http://books.google.it/books/content?id=vwb5mgEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api");
		*/
	}
	
	public BookModel getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(BookModel selectedBook) {
		this.selectedBook = selectedBook;
	}
	
}
