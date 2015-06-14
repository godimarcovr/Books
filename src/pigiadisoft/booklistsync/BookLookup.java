package pigiadisoft.booklistsync;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pigiadisoft.dbhandler.BookBeanStrategy;
import pigiadisoft.dbhandler.DataBean;
import pigiadisoft.dbhandler.MyDriver;
import pigiadisoft.dbhandler.Query;
import pigiadisoft.dbhandler.SQLCodeBuilder;


public class BookLookup {
	
	private BookBeanStrategy strat;
	private String title;
	
	public BookLookup(String title){
		this.strat=new BookBeanStrategy((Query) SQLCodeBuilder
				.createSelectAllFromWhereWithParams("ScrittoDa AS SD JOIN Libro L ON SD.libro_isbn=L.isbn "
				+ "JOIN Autore A ON A.nome=SD.autore_nome "
						, "L.titolo ILIKE ?"
						,new Object[]{"%"+title+"%"}));
		this.title=title;
	}
	
	//TODO gestire il controllo in parallelo anche su Google Books oltre che sul db
	//magari metterlo nel costruttore che fa una thread che va a cercare.
	public List<BookBean>  lookupByTitle() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, GeneralSecurityException, IOException{
		Set<BookBean> toRet=new HashSet<BookBean>();
		List<DataBean> resFromDB=this.strat.getSelectedBeans(MyDriver.getInstance().getConnection());
		for (DataBean db : resFromDB) {
			toRet.add((BookBean) db);
		}
		ExternalBookIndex ind=new GoogleBooksIndex();
		List<BookBean> fromGoogleBooks=ind.getBooksByTitle(this.title);
		for (BookBean bookBean : fromGoogleBooks) {
			toRet.add(bookBean);
			//inserirli sul DB
		}
		
		return new ArrayList<BookBean>(toRet);
	}
}
